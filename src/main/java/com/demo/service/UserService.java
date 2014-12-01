package com.demo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import com.demo.dao.UserEntry;

public class UserService {
	Connection con;

	public UserService() throws ServletException {
		// TODO 自动生成的构造函数存根
		init();
	}

	public void init() throws ServletException {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.out);
		}
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:mydatabase", "SA",
					"");
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	public int isInTable(UserEntry user) {
			ArrayList<UserEntry> result = getList();
			for (UserEntry userEntry : result) {
				if (user.getUser().equals(userEntry.getUser()))
					return 1;
			}
			return -1;
		
//		try {
//			PreparedStatement pst = con
//					.prepareStatement("SELECT user FROM userlist WHERE user= '"
//							+ user.getUser() + "'");
//			System.out.println("select * from userlist where user='"
//					+ user.getUser() + "'");
//			pst.clearParameters();
//			ResultSet rs = pst.executeQuery();
////			System.out.println("con == null " + (con == null));
//			if (rs.next()) {
//				return 1;
//			} else
//				return -1;
//		} catch (SQLException e) {
//			e.printStackTrace(System.out);
//		}
//		return 0;
	}
	
	public boolean isCorrect(UserEntry user) {
		ArrayList<UserEntry> result = getList();
		for (UserEntry userEntry : result) {
			if (userEntry.equals(user))
				return true;
		}
		return false;
}
	
	public ArrayList<UserEntry> getList() {
		ArrayList<UserEntry> result = new ArrayList<UserEntry>();
		try {
			PreparedStatement pst=con.prepareStatement("SELECT * FROM userlist");
			pst.clearParameters();
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				//?
				
				result.add(new UserEntry(rs.getInt("id"), rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
		return result;
	}

	public void create(UserEntry user) {
		try {
			PreparedStatement pst = con
					.prepareStatement("INSERT INTO userlist (user, password) VALUES(?,?)");
			pst.clearParameters();
			pst.setString(1, user.getUser());
			pst.setString(2, user.getPassword());
			int i = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}
}
