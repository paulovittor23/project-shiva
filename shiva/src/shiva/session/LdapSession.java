package shiva.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import shiva.cfg.Configuration;
import shiva.domain.metadata.EntityMapping;
import shiva.session.persister.EntityPersister;
import shiva.session.persister.EntityPersisterImpl;
import shiva.session.validator.EntityValidator;
import shiva.session.validator.EntityValidatorImpl;
import shiva.util.ConnectionFactory;

@SuppressWarnings({"unused"})
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
	 */
	public void persist( Object ldapEntity ) {
		this.entityPersister.persist(ldapEntity);
	}

	/**
	 * 
	 * @param ldapEntity
	 */
	public void delete( Object ldapEntity ) {
		this.entityPersister.delete(ldapEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see shiva.session.Persistable#update(java.lang.Object)
	 */
	public void update( Object ldapEntity ) {
		this.entityPersister.update(ldapEntity);
	}

}
