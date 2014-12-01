package com.demo.dao;

public class ItemEntry {
	private String username;
	private String date;
	private String titleString;
	private String contentString;
	private int rank;
	private int id;
	public ItemEntry(int id, String titleString, String contentString, String date, int rank, String username) {
		// TODO 自动生成的构造函数存根
		this.date = date;
		this.titleString = titleString;
		this.contentString = contentString;
		this.rank = rank;
		this.username = username;
		this.id = id;
	}
	public ItemEntry(String titleString, String contentString, String date, int rank, String username) {
		// TODO 自动生成的构造函数存根
		this.date = date;
		this.titleString = titleString;
		this.contentString = contentString;
		this.rank = rank;
		this.username = username;
	}
	public ItemEntry() {
		// TODO 自动生成的构造函数存根
		this.date = "nodate";
		this.titleString = "no titleString";
		this.contentString = "no contentString";
		this.rank = -1;
		this.username = "no username";
	}
	public int getRank() {
		return rank;
	}
	public String getContentString() {
		return contentString;
	}
	public String getTitleString() {
		return titleString;
	}
	public String getDate() {
		return date;
	}
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	/*
	public int compareTo(Object o) {
		ItemEntry itemEntry = (ItemEntry) o;
		if (this.rank > itemEntry.rank)
			return 1;
		else if (this.rank < itemEntry.rank)
			return -1;
		else return 0;
	}*/

}
