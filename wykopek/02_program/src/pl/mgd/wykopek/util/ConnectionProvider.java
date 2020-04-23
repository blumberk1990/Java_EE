package pl.mgd.wykopek.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ConnectionProvider {

	private static volatile DataSource dataSource;
	
	public static Connection getConnection() throws SQLException {
		return getDsInstance().getConnection();
	}
	
	private static DataSource getDsInstance() {
		if(dataSource == null) {
			synchronized (ConnectionProvider.class) {
				if (dataSource == null) {
					try {
						Context initContext = new InitialContext();
						Context envContext = (Context) initContext.lookup("java:comp/env");
						dataSource = (DataSource) envContext.lookup("jdbc/wykopek");
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return dataSource;
	}
}
