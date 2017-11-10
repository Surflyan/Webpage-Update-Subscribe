package com.codebelief.app.DAO;

import java.sql.*;

import com.codebelief.app.VO.Url;
//import com.mysql.jdbc.Statement;

/**
 * 
 * @ClassName: UrlDAO
 * @Description: Define Some Concrete Operations For Url Table
 * @author ����
 * @date 2017��10��14��
 *
 */
public class UrlDAO implements IUrlDAO{
	private Connection conn = null;
	private PreparedStatement ps = null;
	
	public UrlDAO(Connection conn){
		this.conn = conn;
	}
	
	@Override
	/**
	 * @Title: doInsert
	 * @Description: Insert a piece of Url Info
	 * @param url
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doInsert(Url url) throws Exception {
		String query = "insert into Url values(?,?,?,?,?,?)";
		ps = conn.prepareStatement(query);
		ps.setInt(1, url.getUrlID());
		ps.setString(2, url.getUserName());
		ps.setString(3, url.getTitle());
		ps.setString(4, url.getUrl());
		ps.setBoolean(5, url.isEnable());
		ps.setBoolean(6, url.isRealTimePush());
		if(ps.executeUpdate() == 0){
			ps.close();
			return false;
		}
		ps.close();
		return true;
	}

	@Override
	/**
	 * @Title: doDelete
	 * @Description: Delete a piece of Url Info
	 * @param UrlID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doDelete(int UrlID) throws Exception {
		String query = "delete from Url where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, UrlID);
		if(ps.executeUpdate() == 0){
			ps.close();
			return false;
		}
		ps.close();
		return true;
	}

	@Override
	/**
	 * @Title: doUpdate
	 * @Description: Update the Url,Enable,RealTimePush of a piece of Url Info
	 * @param UrlID
	 * @param url
	 * @param Enable
	 * @param RealTimePush
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doUpdate(int UrlID, String title, String url, boolean Enable, boolean RealTimePush) throws Exception {
		String query = "update Url set Title=?,Url=?,Enable=?,RealTimePush=? where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, title);
		ps.setString(2, url);
		ps.setBoolean(3, Enable);
		ps.setBoolean(4, RealTimePush);
		ps.setInt(5, UrlID);
		if(ps.executeUpdate() == 0){
			ps.close();
			return false;
		}
		ps.close();
		return false;
	}

	@Override
	/**
	 * @Title: doFindUrl
	 * @Description: Find the Url By UrlID
	 * @param UrlID
	 * @return String
	 * @throws Exception
	 */
	public String doFindUserName(int UrlID) throws Exception {
		String query = "select UserName from Url where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, UrlID);
		ResultSet rs = ps.executeQuery();
		String UserName = null;
		while(rs.next()){
			UserName = rs.getString(1);
		}
		rs.close();ps.close();
		return UserName;
	}

	@Override
	/**
	 * @Title: doFindUrl
	 * @Description: Find the Url By UrlID
	 * @param UrlID
	 * @return String
	 * @throws Exception
	 */
	public String doFindUrl(int UrlID) throws Exception {
		String query = "select Url from Url where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, UrlID);
		ResultSet rs = ps.executeQuery();
		String url = null;
		while(rs.next()){
			url = rs.getString(1);
		}
		rs.close();ps.close();
		return url;
	}

	@Override
	/**
	 * @Title: doFindEnable
	 * @Description: Find the Enable By UrlID
	 * @param UrlID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doFindEnable(int UrlID) throws Exception {
		String query = "select Enable from Url where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, UrlID);
		ResultSet rs = ps.executeQuery();
		boolean Enable = false;
		while(rs.next()){
			Enable = rs.getBoolean(1);
		}
		rs.close();ps.close();
		return Enable;
	}

	@Override
	/**
	 * @Title: doFindRealTimePush
	 * @Description: Find the RealTimePush
	 * @param UrlID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doFindRealTimePush(int UrlID) throws Exception {
		String query = "select RealTimePush from Url where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, UrlID);
		ResultSet rs = ps.executeQuery();
		boolean RealTimePush = false;
		while(rs.next()){
			RealTimePush = rs.getBoolean(1);
		}
		rs.close();ps.close();
		return RealTimePush;
	}

	@Override
	/**
	 * @Title: doFindAll
	 * @Description: Find the UserName,Url,RealTimePush By UrlID
	 * @param UrlID
	 * @return Url
	 * @throws Exception
	 */
	public Url doFindAll(int UrlID) throws Exception {
		String query = "select * from Url where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, UrlID);
		ResultSet rs = ps.executeQuery();
		Url newUrl = null;//new Url(UrlID);
		while(rs.next()){
			newUrl = new Url(UrlID);
			newUrl.setUserName(rs.getString(2));
			newUrl.setUrl(rs.getString(3));
			newUrl.setEnable(rs.getBoolean(4));
			newUrl.setRealTimePush(rs.getBoolean(5));
		}
		rs.close();ps.close();
		return newUrl;
	}

	@Override
	/**
	 * @Title: IsExist
	 * @Description: Judge Whether the User Info in Which the UserName is Given Has Existed
	 * @param UrlUrl
	 * @return boolean: true is exist and false is not exist
	 * @throws Exception
	 */
	public boolean isExist(int UrlID) throws Exception {
		String query = "select count(*) from Url where UserName=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, UrlID);
		ResultSet rs = ps.executeQuery();
		boolean isexist = false;
		while(rs.next()){
			if(rs.getInt(1)!=0)	isexist = true;
		}
		rs.close();ps.close();
		return isexist;
	}

	@Override
	public void free() throws Exception {}
	
	@Override
	/**
	 * 
	 * @Title: doFindTitle
	 * @Description: Find the Title By UrlID
	 * @param UrlID
	 * @return String
	 * @throws Exception
	 */
	public String doFindTitle(int UrlID) throws Exception {
		String query = "select title from Url where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, UrlID);
		ResultSet rs = ps.executeQuery();
		String newTitle = null;
		while(rs.next()){
			newTitle = rs.getString(1);
		}
		rs.close();ps.close();
		return newTitle;
	}

}
