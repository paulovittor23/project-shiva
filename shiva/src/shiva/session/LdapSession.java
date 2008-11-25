package shiva.session;

import java.util.List;

import org.apache.log4j.Logger;

import shiva.cfg.Configuration;
import shiva.domain.exception.validation.ValidationException;
import shiva.session.persister.EntityPersister;
import shiva.session.persister.EntityPersisterImpl;
import shiva.session.validator.EntityValidator;
import shiva.session.validator.EntityValidatorImpl;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@SuppressWarnings("unchecked")
public class LdapSession {

	private Logger logger = Logger.getLogger( this.getClass() );

	private Configuration config;
	private EntityValidator entityValidator = null;
	private EntityPersister entityPersister = null;

	// singleton instance
	private static LdapSession instance;

	// private constructor
	private LdapSession() {}

	/**
	 * 
	 * @return
	 */
	public static synchronized LdapSession getInstance() {
		if ( instance == null ) {
			instance = new LdapSession();
		}
		return instance;
	}

	/**
	 * 
	 * 
	 * @param config
	 * @return
	 */
	public static synchronized LdapSession getInstance( Configuration config ) {
		if ( instance == null ) {
			instance = new LdapSession();
		}
		
		instance.config = config;
		instance.entityValidator = new EntityValidatorImpl();
		instance.entityPersister = new EntityPersisterImpl( instance.config );
		
		return instance;
	}

	/**
	 * 
	 * @param ldapEntity
	 * @throws ValidationException 
	 */
	public void persist( Object ldapEntity ) throws ValidationException {
		try {
			
			boolean exists = this.exists( ldapEntity );
			
			if( ! exists ){
			
				//
				this.entityPersister.persist(ldapEntity);
				
			}else{
				
				throw new ValidationException("insert operation canceled... entity already exists!");
			}
			
			
		} catch ( ValidationException e ) {
			logger.error( "persist is not ok :(", e );
			throw e;
		}
	}

	/**
	 * 
	 * @param ldapEntity
	 * @throws ValidationException 
	 */
	public void delete( Object ldapEntity ) throws ValidationException {
		try {
			
			boolean exists = this.exists( ldapEntity );
			
			if( exists ){

				//	
				this.entityPersister.delete(ldapEntity);
				
			}else{
				
				throw new ValidationException("delete operation canceled... entity doesn't exists!");
			}
			
		} catch ( ValidationException e ) {
			logger.error( "update is not ok :(", e );
			throw e;
		}
	}

	/**
	 * 
	 * @param ldapEntity
	 * @throws ValidationException 
	 */
	public void update( Object ldapEntity ) throws ValidationException {
		try {
		
			boolean exists = this.exists( ldapEntity );
			
			if( exists ){
				
				//
				this.entityPersister.update(ldapEntity);
				
			}else{
				
				throw new ValidationException("update operation canceled... entity doesn't exists!");
			}
		
		} catch ( ValidationException e ) {
			logger.error( "update is not ok :(", e );
			throw e;
		}
	}
	
	/**
	 * 
	 * @param objEntity
	 * @return
	 * @throws ValidationException 
	 */
	public boolean exists( Object objEntity ) throws ValidationException {
		try {
			
			this.entityValidator.validateEntityInstance( objEntity );
			this.entityValidator.validateEntityType( config, objEntity );
			this.entityValidator.validateEntityState( config, objEntity );
			
			return this.entityPersister.exists( objEntity );
			
		} catch ( ValidationException e ) {
			logger.error( "exists is not ok :(", e );
			throw e;
		}
	}
	
	/**
	 * 
	 * @param clazz
	 * @return
	 * @throws ValidationException 
	 */
	
	public List getAll( Class clazz ) throws ValidationException{
		try {
		
			this.entityValidator.validateEntityType( config, clazz );
			return this.entityPersister.getAll( clazz );

		} catch ( ValidationException e ) {
			logger.error( "getAll is not ok :(", e );
			throw e;
		}
	}

}
