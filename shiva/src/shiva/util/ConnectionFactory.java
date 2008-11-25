package shiva.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import shiva.cfg.Configuration;

public class ConnectionFactory {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private static final String DRIVER_NAME = "com.octetstring.jdbcLdap.sql.JdbcLdapDriver";
	private static final String SEARCH_SCOPE = "subTreeScope";
	//private static final String TIME_LIMIT = "1000";
	
	/*
		Property 				Description
		
		cache_statements 		set to true to cache SQL statements
		secure 					set to true for TLS/SSL connections over LDAP
		concat_atts 			set to true if attributes with multiple values should be concatenated into a single value surrounded by []. E.g. [val1][val2][val2]
		search_scope 			may be set to objectScope, oneLevelScope or subTreeScope as a default search scope.
		exp_rows 				set to true if multi-value attributes should be handled by creating more rows. This will create rows for every entry for every extra value for each attribute.
		pre_fetch 				set to false (default is true) to process each entry as it is required by the client application. This gives much better performance, but all metadata is not available until after all entries are processed. Setting this value to false will give a big performance boost in applications where all fields are known before the search is executed.
		size_limit 				set to the maximum number of entries that may be retrieved. Default is 1000.
		time_limit 				set to the maximum amount of time an operation may take. Default is unlimited.
		ignore_transactions 	set to true if calls to setAutoCommit(), commit() and rollback() should be ignored. Default is false.
		table_def 				The path to the properties file that contains the table map
		no_soap 				Set to true when using DSMLv2 if you don't want to encase the message in a SOAP envelope
		dsml_base_dn 			The base DN for using DSMLv2 connections
		spml_base_dn 			The base DN for using SPMLv2 connections, should be 'ou=Users,dc=spml,dc=com'
		spml_impl 				The authentication implementation class
	*/

	/**
	 * 
	 * 
	 * @param config
	 * @return
	 */
	public Connection getConnection( Configuration config ) {
		
		StringBuffer connString = new StringBuffer(); 
		
		connString.append("jdbc:ldap://");
		connString.append(config.getLdapServerHost()).append(":");;
		connString.append(config.getLdapServerPort()).append("/");
		connString.append(config.getLdapServerBaseDn()).append("?");
		connString.append("SEARCH_SCOPE:=").append(SEARCH_SCOPE);
		//connString.append("TIME_LIMIT:=").append(TIME_LIMIT);

		Connection conn = null;
		try {

			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(
				connString.toString(), 
				config.getLdapServerUsername(), 
				config.getLdapServerPassword()
			);

			logger.info("getConnection is ok :D");
			
		} catch (ClassNotFoundException e) {
			logger.error("getConnection is not ok :(", e);
		} catch (SQLException e) {
			logger.error("getConnection is not ok :(", e);
		}
		
		return conn;
	}

}
