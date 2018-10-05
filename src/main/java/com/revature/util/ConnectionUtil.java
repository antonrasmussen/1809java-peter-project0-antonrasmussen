package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionUtil {

	private static Logger LOGGER = Logger.getLogger(ConnectionUtil.class);

	public static Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		//RDS: "jdbc:oracle:thin:@RDS_LINK:1521:ORCL" 
		//-> Replace RDS_LINK with instance address

		/*
		 * BOTH USER AND PASSWORD SHOULD BE ENVIRONMENT VARIABLES.
		 * 
		 * YOU CAN GET THEM WITH (for example): System.getenv("BAND_DB_PASSWORD")
		 */
		String user = "BAND_DB";
		String password = "p4ssw0rd";

		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * Test the connection
	 *
	 */
	public static void main(String[] args) {
		try(Connection connection = ConnectionUtil.getConnection()){
			LOGGER.info("Connection successful");

		} catch (SQLException e) {

			LOGGER.error("Couldn't establish connection", e);
		}
	}

}
