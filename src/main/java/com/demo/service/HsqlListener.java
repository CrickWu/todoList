package com.demo.service;

import javax.servlet.*;
import java.sql.*;

public class HsqlListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent sce) {
		Connection c = null;
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			c = DriverManager.getConnection("jdbc:hsqldb:mydatabase", "SA", "");
			Statement stmt = c.createStatement();
			// stmt.execute("DROP TABLE IF EXISTS userlist");
			// stmt.execute("DROP TABLE IF EXISTS itemlist");
			// stmt.execute("CREATE TABLE IF NOT EXISTS userlist(user VARCHAR(30), password VARCHAR(30))");
			// stmt.execute("CREATE TABLE IF NOT EXISTS userlist(id BIGINT NOT NULL PRIMARY KEY, user VARCHAR(30), password VARCHAR(30))");
			stmt.execute("CREATE TABLE IF NOT EXISTS userlist(user VARCHAR(30), password VARCHAR(30), id IDENTITY NOT NULL PRIMARY KEY)");
			// (title, content, date, rank, username)
			stmt.execute("CREATE TABLE IF NOT EXISTS itemlist(title VARCHAR(40), content VARCHAR(300), date DATE, rank INTEGER, username VARCHAR(30), id IDENTITY NOT NULL PRIMARY KEY)");
			// stmt.execute("CREATE TABLE IF NOT EXISTS itemlist(id BIGINT NOT NULL PRIMARY KEY, title VARCHAR(40), content VARCHAR(300), date DATE, rank INTEGER)");
			// stmt.execute("create table if not exists itemlist(id INTEGER NOT NULL IDENTITY PRIMARY KEY, name varchar(30), itemName varchar(30), itemDue varchar(30))");
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		Connection conn = null;
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			conn = DriverManager.getConnection("jdbc:hsqldb:mydatabase", "SA",
					"");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("SHUTDOWN;");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}