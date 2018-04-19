package com.revature.util;

import java.io.*;
import java.sql.*;
import java.util.*;

public class ConnectionFactory {
	private static ConnectionFactory cf = null;
	private static boolean build = true;
	
	public ConnectionFactory() {
		build = false;
	}
	
	public static synchronized ConnectionFactory getInstance() {
		return (build) ? cf = new ConnectionFactory() : cf;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("C:\\Users\\Myles\\Documents\\"
					+ "connection.properties"));
			//Class.forName(prop.getProperty("driver"));
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(prop.getProperty("url"), 
												prop.getProperty("usr"),
												prop.getProperty("pwd"));
		}catch (ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
