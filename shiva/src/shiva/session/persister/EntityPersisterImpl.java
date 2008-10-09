package shiva.session.persister;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import shiva.cfg.Configuration;
import shiva.domain.metadata.EntityMapping;
import shiva.session.persister.sql.CrudSqlGen;
import shiva.session.persister.sql.SqlGen;
import shiva.util.ConnectionFactory;


/**
 * 
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@SuppressWarnings({"unused","unchecked"})
public class EntityPersisterImpl implements EntityPersister {
			
	private Logger logger = Logger.getLogger(this.getClass());
	
	private SqlGen sqlGen = null;
	private Configuration config = null;
	
	/**
	 * 
	 * @param config
	 */
	public EntityPersisterImpl( Configuration config ) {
		this.sqlGen = new CrudSqlGen();
		this.config = config;
	}
	
	/*
	 * (non-Javadoc)
	 * @see shiva.session.persister.EntityPersister#persist(java.lang.Object)
	 */
	@Override
	public void persist(Object ldapEntity ) {
		// validate ldap entity object

		//
		Map<Class, EntityMapping> ems = config.getEntityMappings();
		EntityMapping em = ems.get( ldapEntity.getClass() );

		String insertSql = this.sqlGen.generateInsertString( em, ldapEntity );

		logger.info( "generating insert string: " + insertSql );
		this.executeUpdateSql( insertSql );
	}

	/*
	 * (non-Javadoc)
	 * @see shiva.session.persister.EntityPersister#delete(java.lang.Object)
	 */
	@Override
	public void delete(Object ldapEntity ) {
		//
		Map<Class, EntityMapping> ems = config.getEntityMappings();
		EntityMapping em = ems.get( ldapEntity.getClass() );

		String deleteSql = this.sqlGen.generateDeleteString( em, ldapEntity );

		logger.info( "generating delete string: " + deleteSql );
		this.executeUpdateSql( deleteSql );
	}

	/*
	 * (non-Javadoc)
	 * @see shiva.session.persister.EntityPersister#update(java.lang.Object)
	 */
	@Override
	public void update(Object ldapEntity ) {
		// validate ldap entity object

		//
		Map<Class, EntityMapping> ems = config.getEntityMappings();
		EntityMapping em = ems.get( ldapEntity.getClass() );

		String updateSql = this.sqlGen.generateUpdateString( em, ldapEntity );

		logger.info( "generating update string: " + updateSql );
		this.executeUpdateSql( updateSql );
	}
	
	/**
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
	 * @param sql
	 * @return
	 */
	private ResultSet executeQuerySql( String sql ) {
		ResultSet rs = null;

		try {
			ConnectionFactory factory = new ConnectionFactory();
			Connection conn = factory.getConnection( config );

			PreparedStatement ps = conn.prepareStatement( sql );
			rs = ps.executeQuery();

		} catch ( SQLException e ) {
			logger.error( "executeQuerySql is not ok :(", e );
		}

		return rs;
	}
		
}
