package shiva.cfg;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import shiva.cfg.component.mapper.EntityMapper;
import shiva.cfg.component.mapper.Mapper;
import shiva.cfg.component.mapper.model.exception.MappingException;
import shiva.cfg.component.mapper.model.metadata.EntityMapping;
import shiva.cfg.component.reflector.ValidatorReflector;
import shiva.cfg.component.reflector.ValidatorReflectorImpl;
import shiva.cfg.component.registrator.Registrator;
import shiva.cfg.model.exception.InvalidConfigurationException;
import shiva.cfg.model.exception.LdapSessionException;
import shiva.session.LdapSession;
import shiva.util.Utils;
import shiva.util.model.exception.PropertyValueNotFound;
import static shiva.util.model.PropertiesType.*;

/**
 * 
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@SuppressWarnings({"unchecked"})
public class Configuration {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	// singleton instance
	private static Configuration instance = null;
	
	// 
	private static final String VALIDATION_CLASSES_PACKAGE = "validation.class.package";
	private static final String LDAP_SERVER = "ldap.server";
	private static final String LDAP_SERVER_PORT = "ldap.server.port";
	private static final String LDAP_SERVER_BASE_DN = "ldap.server.base.dn";
	private static final String LDAP_SERVER_USERNAME = "ldap.server.username";
	private static final String LDAP_SERVER_PASSWORD = "ldap.server.password";
	
	//
	private Mapper entityMapper;
	private Registrator<Class> entityRegistrator;
	private Registrator<Class> validationRegistrator;
	private ValidatorReflector validatorReflector;

	// 
	private Map<Class, EntityMapping> entityMappings;
	
	//
	private String ldapServerHost;
	private String ldapServerPort;
	private String ldapServerBaseDn;
	private String ldapServerUsername;
	private String ldapServerPassword;
	
	private LdapSession ldapSession;
	
	/**
	 * 
	 * 
	 */
	private Configuration() {
		this.init();		
	}
	
	/**
	 * 
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
	 * 
	 * 
	 */
	private void init(){
		//
		this.entityMapper = new EntityMapper();
		this.entityRegistrator = new Registrator<Class>();
		this.validationRegistrator = new Registrator<Class>();
		this.validatorReflector = new ValidatorReflectorImpl();
	
		//
		this.registerValidatorClasses();
	}
	
	/**
	 * 
	 * 
	 * 
	 */
	private void registerValidatorClasses(){
		
		Utils utils = Utils.getInstance();
		
		String validationPackage = null;
		try {
			
			validationPackage = utils.getPropertyValueByKey(UTIL_PROPERTIES_FILE, VALIDATION_CLASSES_PACKAGE);
			
		} catch (FileNotFoundException e) {
			logger.log(Level.WARN, e.getMessage());
		} catch (PropertyValueNotFound e) {
			logger.log(Level.WARN, e.getMessage());
		}
		
		Class[] classes = this.validatorReflector.getClasses(validationPackage);
		
		for (int i = 0; i < classes.length; i++) {

			Class clazz = classes[i];
			this.validationRegistrator.register( clazz );
		}
		
		if( this.validationRegistrator.registeredObjectsCount() == 0 ){
			logger.log(Level.WARN, "Nenhuma classe de validação foi devidamente registrada.");
		}
	}
	
	/**
	 * 
	 * 
	 * @throws InvalidConfigurationException
	 */
	public void configure() throws InvalidConfigurationException{

		// tenta carregar as configurações da aplicação cliente
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
		
		// tenta realizar o mapeamento das classes de entidade registradas
		try {
			
			mapEntityClasses();
			
		} catch (MappingException e) {
			// caso não consiga lança um erro de configuração
			InvalidConfigurationException ex = new InvalidConfigurationException();
			ex.addImplicitExceptions(e);
			throw ex;
		}
		
		// ...
		
		
		// por fim... prepara o atributo ldapSession
		try {
			
			initLdapSession();
			
		} catch (LdapSessionException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * 
	 * @throws MappingException
	 */
	private void mapEntityClasses() throws MappingException{
		Integer registeredObjectsCount = this.entityRegistrator.registeredObjectsCount();
		
		if( registeredObjectsCount == 0 ){
			throw new MappingException("Não existem classes de entidade a serem mapeadas.");
		}
		
		Set<Class> registeredObjects = this.entityRegistrator.getRegisteredObjects();
		Map<Class, EntityMapping> generatedMapping = (Map<Class, EntityMapping>) this.entityMapper.generateMapping(registeredObjects);
		
		this.entityMappings = generatedMapping;
	}
	
	/**
	 * 
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
	 * 
	 * @throws LdapSessionException
	 */
	private void initLdapSession() throws LdapSessionException{
		
		this.ldapSession = LdapSession.getInstance(this);
	}	
	
	/**
	 * 
	 * 
	 * @return
	 */
	public Map<Class, EntityMapping> getEntityMappings() {
		return entityMappings;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public Registrator<Class> getEntityRegistrator() {
		return entityRegistrator;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public Registrator<Class> getValidationRegistrator() {
		return validationRegistrator;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public ValidatorReflector getValidatorReflector() {
	    return validatorReflector;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public LdapSession getLdapSession() {
		return ldapSession;
	}

	/**
	 * 
	 * 
	 * @return the ldapServerHost
	 */
	public String getLdapServerHost() {
		return ldapServerHost;
	}

	/**
	 * 
	 * 
	 * @param ldapServerHost the ldapServerHost to set
	 */
	public void setLdapServerHost(String ldapServerHost) {
		this.ldapServerHost = ldapServerHost;
	}

	/**
	 * 
	 * 
	 * @return the ldapServerPort
	 */
	public String getLdapServerPort() {
		return ldapServerPort;
	}

	/**
	 * 
	 * 
	 * @param ldapServerPort the ldapServerPort to set
	 */
	public void setLdapServerPort(String ldapServerPort) {
		this.ldapServerPort = ldapServerPort;
	}

	/**
	 * 
	 * 
	 * @return the ldapServerBaseDn
	 */
	public String getLdapServerBaseDn() {
		return ldapServerBaseDn;
	}

	/**
	 * 
	 * 
	 * @param ldapServerBaseDn the ldapServerBaseDn to set
	 */
	public void setLdapServerBaseDn(String ldapServerBaseDn) {
		this.ldapServerBaseDn = ldapServerBaseDn;
	}

	/**
	 * 
	 * 
	 * @return the ldapServerUsername
	 */
	public String getLdapServerUsername() {
		return ldapServerUsername;
	}

	/**
	 * 
	 * 
	 * @param ldapServerUsername the ldapServerUsername to set
	 */
	public void setLdapServerUsername(String ldapServerUsername) {
		this.ldapServerUsername = ldapServerUsername;
	}

	/**
	 * 
	 * 
	 * @return the ldapServerPassword
	 */
	public String getLdapServerPassword() {
		return ldapServerPassword;
	}

	/**
	 * 
	 * 
	 * @param ldapServerPassword the ldapServerPassword to set
	 */
	public void setLdapServerPassword(String ldapServerPassword) {
		this.ldapServerPassword = ldapServerPassword;
	}
	
}
