package shiva.util.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import shiva.cfg.Configuration;

public class ConnectionFactory {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private static final String DRIVER_NAME = "com.octetstring.jdbcLdap.sql.JdbcLdapDriver";
	private static final String SEARCH_SCOPE = "subTreeScope";

	/**
	 * 
	 * 
	 * @param config
	 * @return
	 */
	public Connection getConnection( Configuration config ) {
		
		StringBuffer connString = new StringBuffer(); 
		
		connString.append("jdbc:ldap://");
		connString.append(config.getLdapServerHost());
		connString.append(":");
		connString.append(config.getLdapServerPort());
		connString.append("/");
		connString.append(config.getLdapServerBaseDn());
		connString.append("?");
		connString.append("SEARCH_SCOPE:=");
		connString.append(SEARCH_SCOPE);

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
