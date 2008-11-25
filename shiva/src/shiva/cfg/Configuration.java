package shiva.cfg;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import shiva.cfg.mapper.EntityMapper;
import shiva.cfg.mapper.Mapper;
import shiva.cfg.reflector.ValidatorReflector;
import shiva.cfg.reflector.ValidatorReflectorImpl;
import shiva.cfg.registrator.Registrator;
import shiva.domain.exception.InvalidConfigurationException;
import shiva.domain.exception.LdapSessionException;
import shiva.domain.exception.MappingException;
import shiva.domain.exception.PropertyValueNotFound;
import shiva.domain.metadata.EntityMapping;
import shiva.domain.validation.annotation.*;
import shiva.domain.validation.logic.ValidationClass;
import shiva.domain.validation.logic.impl.*;
import shiva.session.LdapSession;
import shiva.util.ConnectionFactory;
import shiva.util.Utils;

import static shiva.util.PropertiesType.*;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@SuppressWarnings({"unchecked"})
public class Configuration {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	// 
	//private static final String VALIDATION_CLASSES_PACKAGE = "validation.class.package";
	private static final String LDAP_SERVER = "ldap.server";
	private static final String LDAP_SERVER_PORT = "ldap.server.port";
	private static final String LDAP_SERVER_BASE_DN = "ldap.server.base.dn";
	private static final String LDAP_SERVER_USERNAME = "ldap.server.username";
	private static final String LDAP_SERVER_PASSWORD = "ldap.server.password";
	
	// singleton instance
	private static Configuration instance = null;
	
	//
	private LdapSession ldapSession;
	
	private Mapper entityMapper;
	private ValidatorReflector validatorReflector;
	
	private Registrator<Class> entityRegistrator;
	private Registrator<Class> validationRegistrator;

	// 
	private Map<Class, EntityMapping> entitiesMapping;
	private Map<Class, ValidationClass> validationsMapping;
	
	//
	private String ldapServerHost;
	private String ldapServerPort;
	private String ldapServerBaseDn;
	private String ldapServerUsername;
	private String ldapServerPassword;
	
	private Connection connection;
	
	/**
	 * 
	 */
	private Configuration() {
		//
		this.entityMapper = new EntityMapper();
		this.validatorReflector = new ValidatorReflectorImpl();
		
		//
		this.entityRegistrator = new Registrator<Class>();
		this.validationRegistrator = new Registrator<Class>();
		
		//
		this.validationsMapping = new HashMap<Class, ValidationClass>();
	}
	
	/**
	 * 
	 * @return
	 */
	public static synchronized Configuration getInstance(){
		if( instance == null ){
			instance = new Configuration();
		}
		return instance;
	}
	
	/**
	 * 
	 * @throws InvalidConfigurationException
	 */
	public void configure() throws InvalidConfigurationException{

		//try load the client application data 
		try {
			loadApplicationConfig();
		} catch (FileNotFoundException e) {
			InvalidConfigurationException ex = new InvalidConfigurationException();
			ex.addImplicitExceptions(e);
			throw ex;
		} catch (PropertyValueNotFound e) {
			InvalidConfigurationException ex = new InvalidConfigurationException();
			ex.addImplicitExceptions(e);
			throw ex;
		}
		
		//register the default validation classes
		this.registerValidationClasses();
		
		//map validation classes
		this.mapValidationClasses();
		
		//map entity classes
		try {
			mapEntityClasses();
			System.out.println( this.entitiesMapping );
		} catch (MappingException e) {
			InvalidConfigurationException ex = new InvalidConfigurationException();
			ex.addImplicitExceptions(e);
			throw ex;
		}
		
		// prepare LdapSession singleton
		try {
			initLdapSession();
			
			//
			this.connection = new ConnectionFactory().getConnection( this );
			
		} catch (LdapSessionException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 */
	private void registerValidationClasses(){
		logger.info( "starting register of validation classes" );
		
		//default validation classes
		Collection<Class> vClasses = new ArrayList<Class>();
		vClasses.add( Cnpj.class );
		vClasses.add( Cpf.class );
		vClasses.add( Email.class );
		vClasses.add( Length.class );
		vClasses.add( Max.class );
		vClasses.add( Min.class );
		vClasses.add( NotEmpty.class );
		vClasses.add( Pattern.class );
		vClasses.add( Range.class );
		
		//register all validation classes
		this.validationRegistrator.registerAll( vClasses );
		
		logger.info( "finishing register of validation classes" );
	}

	/**
	 * Associate validation class with Validation Algorithm.
	 */
	private void mapValidationClasses(){
		logger.info( "starting mapping of validation classes" );
		
		this.validationsMapping.put( Cnpj.class, new CnpjValidation() );
		logger.info( "added map [" + Cnpj.class.getCanonicalName() + " -> " + CnpjValidation.class.getCanonicalName() + "]" );
		
		this.validationsMapping.put( Cpf.class, new CpfValidation() );
		logger.info( "added map [" + Cpf.class.getCanonicalName() + " -> " + CpfValidation.class.getCanonicalName() + "]" );
		
		this.validationsMapping.put( Email.class, new EmailValidation() );
		logger.info( "added map [" + Email.class.getCanonicalName() + " -> " + EmailValidation.class.getCanonicalName() + "]" );
		
		this.validationsMapping.put( Length.class, new LengthValidation() );
		logger.info( "added map [" + Length.class.getCanonicalName() + " -> " + LengthValidation.class.getCanonicalName() + "]" );
		
		this.validationsMapping.put( Max.class, new MaxValidation() );
		logger.info( "added map [" + Max.class.getCanonicalName() + " -> " + MaxValidation.class.getCanonicalName() + "]" );
		
		this.validationsMapping.put( Min.class, new MinValidation() );
		logger.info( "added map [" + Min.class.getCanonicalName() + " -> " + MinValidation.class.getCanonicalName() + "]" );
		
		this.validationsMapping.put( NotEmpty.class, new NotEmptyValidation() );
		logger.info( "added map [" + NotEmpty.class.getCanonicalName() + " -> " + NotEmptyValidation.class.getCanonicalName() + "]" );
		
		this.validationsMapping.put( Pattern.class, new PatternValidation() );
		logger.info( "added map [" + Pattern.class.getCanonicalName() + " -> " + PatternValidation.class.getCanonicalName() + "]" );
		
		this.validationsMapping.put( Range.class, new RangeValidation() );
		logger.info( "added map [" + Range.class.getCanonicalName() + " -> " + RangeValidation.class.getCanonicalName() + "]" );
		
		logger.info( "finishing mapping of validation classes" );
	}
	
	/**
	 * 
	 * @throws MappingException
	 */
	private void mapEntityClasses() throws MappingException{
		logger.info( "starting mapping of entity classes" );
		
		Integer registeredObjectsCount = this.entityRegistrator.registeredObjectsCount();
		
		if( registeredObjectsCount == 0 ){
			throw new MappingException("There's no entity classes to be mapped.");
		}
		
		Set<Class> registeredObjects = this.entityRegistrator.getRegisteredObjects();
		Map<Class, EntityMapping> generatedMapping = (Map<Class, EntityMapping>) this.entityMapper.generateMapping(registeredObjects);
		
		this.entitiesMapping = generatedMapping;
		
		logger.info( "finishing mapping of entity classes" );
	}
	
	/**
	 * 
	 * @throws FileNotFoundException
	 * @throws PropertyValueNotFound
	 */
	private void loadApplicationConfig() throws FileNotFoundException, PropertyValueNotFound{
		Utils utils = Utils.getInstance();

		Properties configProps = utils.getPropertyFileByType(CONFIGURATION_PROPERTIES_FILE);
		
		this.ldapServerHost = utils.getPropertyValueByKey(configProps, LDAP_SERVER);
		this.ldapServerPort = utils.getPropertyValueByKey(configProps, LDAP_SERVER_PORT);
		this.ldapServerBaseDn = utils.getPropertyValueByKey(configProps, LDAP_SERVER_BASE_DN);
		this.ldapServerUsername = utils.getPropertyValueByKey(configProps, LDAP_SERVER_USERNAME);
		this.ldapServerPassword = utils.getPropertyValueByKey(configProps, LDAP_SERVER_PASSWORD);
	}
	
	/**
	 * 
	 * @throws LdapSessionException
	 */
	private void initLdapSession() throws LdapSessionException{
		this.ldapSession = LdapSession.getInstance(this);
	}	
	
	/**
	 * 
	 * @return
	 */
	public Map<Class, EntityMapping> getEntityMappings() {
		return entitiesMapping;
	}

	/**
	 * 
	 * @return
	 */
	public Registrator<Class> getEntityRegistrator() {
		return entityRegistrator;
	}

	/**
	 * 
	 * @return
	 */
	public Registrator<Class> getValidationRegistrator() {
		return validationRegistrator;
	}

	/**
	 * 
	 * @return
	 */
	public ValidatorReflector getValidatorReflector() {
	    return validatorReflector;
	}
	
	/**
	 * @return the validationsMapping
	 */
	public Map<Class, ValidationClass> getValidationsMapping() {
		return this.validationsMapping;
	}

	/**
	 * 
	 * @return
	 */
	public LdapSession getLdapSession() {
		return ldapSession;
	}

	/**
	 * 
	 * @return the ldapServerHost
	 */
	public String getLdapServerHost() {
		return ldapServerHost;
	}

	/**
	 * 
	 * @param ldapServerHost the ldapServerHost to set
	 */
	public void setLdapServerHost(String ldapServerHost) {
		this.ldapServerHost = ldapServerHost;
	}

	/**
	 * 
	 * @return the ldapServerPort
	 */
	public String getLdapServerPort() {
		return ldapServerPort;
	}

	/**
	 * 
	 * @param ldapServerPort the ldapServerPort to set
	 */
	public void setLdapServerPort(String ldapServerPort) {
		this.ldapServerPort = ldapServerPort;
	}

	/**
	 * 
	 * @return the ldapServerBaseDn
	 */
	public String getLdapServerBaseDn() {
		return ldapServerBaseDn;
	}

	/**
	 * 
	 * @param ldapServerBaseDn the ldapServerBaseDn to set
	 */
	public void setLdapServerBaseDn(String ldapServerBaseDn) {
		this.ldapServerBaseDn = ldapServerBaseDn;
	}

	/**
	 * 
	 * @return the ldapServerUsername
	 */
	public String getLdapServerUsername() {
		return ldapServerUsername;
	}

	/**
	 * 
	 * @param ldapServerUsername the ldapServerUsername to set
	 */
	public void setLdapServerUsername(String ldapServerUsername) {
		this.ldapServerUsername = ldapServerUsername;
	}

	/**
	 * 
	 * @return the ldapServerPassword
	 */
	public String getLdapServerPassword() {
		return ldapServerPassword;
	}

	/**
	 * 
	 * @param ldapServerPassword the ldapServerPassword to set
	 */
	public void setLdapServerPassword(String ldapServerPassword) {
		this.ldapServerPassword = ldapServerPassword;
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return this.connection;
	}

}
