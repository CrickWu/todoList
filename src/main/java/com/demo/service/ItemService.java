package com.demo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.servlet.ServletException;

import com.demo.dao.ItemEntry;

public class ItemService {
	Connection con;

	public ItemService() throws ServletException {
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

	public ArrayList<ItemEntry> getIdList() {
		ArrayList<ItemEntry> result = getList();
		Comparator<ItemEntry> comparator = new Comparator<ItemEntry>() {
			public int compare(ItemEntry s1, ItemEntry s2) {
				return s1.getId() - s2.getId();
			}
		};
		Collections.sort(result, comparator);
		return result;
	}

	public ArrayList<ItemEntry> getDateList() {
		ArrayList<ItemEntry> result = getList();
		Comparator<ItemEntry> comparator = new Comparator<ItemEntry>() {
			public int compare(ItemEntry s1, ItemEntry s2) {
				// 先排date
				if (!s1.getDate().equals(s2.getDate())) {
					return s1.getDate().compareTo(s2.getDate());
				}
				// rank
				else if (s1.getRank() != s2.getRank()) {
					return s1.getRank() - s2.getRank();
				} else {
					// id
					return s1.getId() - s2.getId();
				}
			}
		};
		Collections.sort(result, comparator);
		return result;
	}

	public ArrayList<ItemEntry> getRankList() {
		ArrayList<ItemEntry> result = getList();
		Comparator<ItemEntry> comparator = new Comparator<ItemEntry>() {
			public int compare(ItemEntry s1, ItemEntry s2) {
				// 先排rank
				if (s1.getRank() != s2.getRank()) {
					return s1.getRank() - s2.getRank();
				} else {
					// date
					if (!s1.getDate().equals(s2.getDate())) {
						return s1.getDate().compareTo(s2.getDate());
					} else {
						// id
						return s1.getId() - s2.getId();
					}
				}
			}
		};
		Collections.sort(result, comparator);
		return result;
	}

	public ArrayList<ItemEntry> getList() {
		ArrayList<ItemEntry> result = new ArrayList<ItemEntry>();
		try {
			PreparedStatement pst = con
					.prepareStatement("SELECT * FROM itemlist");
			pst.clearParameters();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				result.add(new ItemEntry(rs.getInt("id"), rs.getString(1), rs
						.getString(2), rs.getString(3), rs.getInt(4), rs
						.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
		return result;
	}

	public ArrayList<ItemEntry> search(String keywordString) {
		return search(keywordString, null);
	}

	public ArrayList<ItemEntry> search(String keywordString, String typeString) {
		ArrayList<ItemEntry> result = new ArrayList<ItemEntry>();
		ArrayList<ItemEntry> arr = new ArrayList<ItemEntry>();
		if (typeString == null)
			arr = getList();
		else if (typeString.equals("rank"))
			arr = getRankList();
		else if (typeString.equals("date"))
			arr = getDateList();
		else if (typeString.equals("id"))
			arr = getIdList();

		for (ItemEntry itemEntry : arr) {
			if (keywordString == null)
				result.add(itemEntry);
			else {
				if (itemEntry.getDate() != null) {
					if (itemEntry.getDate().contains(keywordString)) {
						result.add(itemEntry);
						continue;
					}
				}
				if (itemEntry.getTitleString() != null) {
					if (itemEntry.getTitleString().contains(keywordString)) {
						result.add(itemEntry);
						continue;
					}
				}
				if (itemEntry.getContentString() != null) {
					if (itemEntry.getContentString().contains(keywordString)) {
						result.add(itemEntry);
						continue;
					}
				}
				if (Integer.toString(itemEntry.getRank()).contains(
						keywordString)) {
					result.add(itemEntry);
					continue;
				}
			}
		}
		return result;
	}

	public int modify(ItemEntry item) {
		boolean flag = false;
		String deadline = item.getDate();
		Date d = new Date();
		try {
			d = new SimpleDateFormat("yyyy-MM-dd").parse(deadline);

		} catch (ParseException e) {
			flag = true;
		}
		java.sql.Date sqlD = new java.sql.Date(d.getTime());

		try {
			PreparedStatement pst = con
					.prepareStatement("UPDATE itemlist SET title=?, content=?, date=?, rank=? WHERE id=?");
			pst.clearParameters();
			pst.setString(1, item.getTitleString());
			pst.setString(2, item.getContentString());

			if ((deadline != null) && (!flag))
				pst.setDate(3, sqlD);
			else
				pst.setNull(3, java.sql.Types.DATE);

			pst.setInt(4, item.getRank());
			pst.setInt(5, item.getId());
			// String titleString, String contentString, String date, int rank,
			// String username
			int i = pst.executeUpdate();
			return i;
			// i = 1 success, 0 unsuccess
		} catch (SQLException e) {
			e.printStackTrace(System.out);
			return -1;
		}
	}

	public int remove(int id) {
		try {
			PreparedStatement pst = con
					.prepareStatement("DELETE FROM itemlist WHERE id=?");
			pst.clearParameters();
			pst.setInt(1, id);
			int i = pst.executeUpdate();
			return i;
			// i = 1 success, 0 unsuccess
		} catch (SQLException e) {
			e.printStackTrace(System.out);
			return -1;
		}
	}

	public int create(ItemEntry item) {
		boolean flag = false;
		String deadline = item.getDate();
		Date d = new Date();
		try {
			d = new SimpleDateFormat("yyyy-MM-dd").parse(deadline);

		} catch (ParseException e) {
			flag = true;
		}
		java.sql.Date sqlD = new java.sql.Date(d.getTime());
		try {
			PreparedStatement pst = con
					.prepareStatement("INSERT INTO itemlist (title, content, date, rank, username) VALUES(?,?,?,?,?)");
			pst.clearParameters();
			pst.setString(1, item.getTitleString());
			pst.setString(2, item.getContentString());
			pst.setInt(4, item.getRank());
			pst.setString(5, item.getUsername());

			if ((deadline != null) && (!flag))
				pst.setDate(3, sqlD);
			else
				pst.setNull(3, java.sql.Types.DATE);

			int i = pst.executeUpdate();
			return i;
			// i = 1 success, 0 unsuccess
		} catch (SQLException e) {
			e.printStackTrace(System.out);
			return -1;
		}
	}
}
