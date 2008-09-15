package shiva.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import shiva.cfg.Configuration;
import shiva.domain.metadata.EntityMapping;
import shiva.session.component.persister.EntityPersister;
import shiva.session.component.persister.EntityPersisterImpl;
import shiva.session.component.validator.EntityValidator;
import shiva.session.component.validator.EntityValidatorImpl;
import shiva.util.ConnectionFactory;

@SuppressWarnings( {
		"unchecked", "unused"
} )
public class LdapSession implements Queryable, Persistable {

	private Logger logger = Logger.getLogger( this.getClass() );

	private Configuration config;
	private EntityValidator entityValidator = null;
	private EntityPersister entityPersister = null;

	// singleton instance
	private static LdapSession instance;

	/**
	 * 
	 * 
	 */
	private LdapSession() {
		this.entityValidator = new EntityValidatorImpl();
		this.entityPersister = new EntityPersisterImpl();
	}

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
		return instance;
	}

	/**
	 * 
	 * 
	 * @param sql
	 */
	private void executeUpdateSql( String sql ) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			Connection conn = factory.getConnection( this.config );

			PreparedStatement ps = conn.prepareStatement( sql );
			ps.executeUpdate();

		} catch ( SQLException e ) {
			logger.error( "executeUpdateSql is not ok :(", e );
		}
	}

	/**
	 * 
	 * 
	 * @param sql
	 * @return
	 */
	private ResultSet executeQuerySql( String sql ) {
		ResultSet rs = null;

		try {
			ConnectionFactory factory = new ConnectionFactory();
			Connection conn = factory.getConnection( this.config );

			PreparedStatement ps = conn.prepareStatement( sql );
			rs = ps.executeQuery();

		} catch ( SQLException e ) {
			logger.error( "executeQuerySql is not ok :(", e );
		}

		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see shiva.session.Persistable#persist(java.lang.Object)
	 */
	public void persist( Object ldapEntity ) {

		// validate ldap entity object

		//
		Map<Class, EntityMapping> ems = config.getEntityMappings();
		EntityMapping em = ems.get( ldapEntity.getClass() );

		String insertSql = this.entityPersister.generateInsertString( em, ldapEntity );

		logger.info( "generating insert string: " + insertSql );
		this.executeUpdateSql( insertSql );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see shiva.session.Persistable#delete(java.lang.Object)
	 */
	public void delete( Object ldapEntity ) {

		//
		Map<Class, EntityMapping> ems = config.getEntityMappings();
		EntityMapping em = ems.get( ldapEntity.getClass() );

		String deleteSql = this.entityPersister.generateDeleteString( em, ldapEntity );

		logger.info( "generating delete string: " + deleteSql );
		this.executeUpdateSql( deleteSql );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see shiva.session.Persistable#update(java.lang.Object)
	 */
	public void update( Object ldapEntity ) {

		// validate ldap entity object

		//
		Map<Class, EntityMapping> ems = config.getEntityMappings();
		EntityMapping em = ems.get( ldapEntity.getClass() );

		String updateSql = this.entityPersister.generateUpdateString( em, ldapEntity );

		logger.info( "generating update string: " + updateSql );
		this.executeUpdateSql( updateSql );

	}

}
