package com.codebelief.app.DAO;

import java.sql.*;
import java.util.LinkedList;

import com.codebelief.app.VO.Content;
import com.mysql.jdbc.Statement;

/**
 * @ClassName: ContentDAO
 * @Description: Define Some Concrete Functions For Content Table
 * @author ����
 * @date 2017��10��17��
 *
 */
public class ContentDAO implements IContentDAO{
	private Connection conn = null;
	private PreparedStatement ps = null;
	
	public ContentDAO(Connection conn){
		this.conn = conn;
	}
	
	@Override
	/**
	 * @Title: doInsert
	 * @Description: Insert a piece of new Content Info
	 * @param content
	 * @return boolean
	 * @throws Exception
	 */
	public int doInsert(int UrlID, String Html, String Delta) throws Exception {
		String query = "insert into Content (UrlID,Html,Delta) values(?,?,?)";
		ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, UrlID);
		ps.setString(2, Html);
		ps.setString(3, Delta);
		if(ps.executeUpdate() == 0){
			ps.close();
			return -1;
		}
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		int ContentID = rs.getInt(1);
		rs.close();ps.close();
		return ContentID;
	}

	@Override
	/**
	 * @Title: doDelete
//	 * @Description: Delete a piece of Content Info
	 * @param ContentID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doDelete(int ContentID) throws Exception {
		String query = "delete from Content where ContentID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, ContentID);
		if(ps.executeUpdate() == 0){
			ps.close();
			return false;
		}
		ps.close();
		return true;
	}
	
	/**
	 * 
	 * @Title: doDeleteByUrlID
	 * @Description: Delete a piece of Content Info by UrlID
	 * @param UrlID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doDeleteByUrlID(int UrlID) throws Exception{
		String query = "delete from Content where UrlID=?";
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
	 * @Title: doUpdateHtmlAndDelta
	 * @Description: Update Html and Delta of a content
	 * @param ContentID
	 * @param Html
	 * @param Delta
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doUpdate(Content content) throws Exception {
		String Html = content.getHtml();
		String Delta = content.getDelta();
		int ContentID = content.getContentID();
		String query = "update Content set Html=?,Delta=? where ContentID=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, Html);
		ps.setString(2, Delta);
		ps.setInt(3, ContentID);
		if(ps.executeUpdate() == 0){
			ps.close();
			return false;
		}
		ps.close();
		return true;
	}
	
	@Override
	/**
	 * 
	 * @Title: doUpdateHtml
	 * @Description: update the Html singly
	 * @param ContentID
	 * @param Html
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean doUpdateHtml(int ContentID, String Html) throws SQLException {
		String query = "update Content set Html=? where ContentID=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, Html);
		ps.setInt(2, ContentID);
		if(ps.executeUpdate() == 0){
			ps.close();
			return false;
		}
		ps.close();
		return true;
	}

	@Override
	/**
	 * 
	 * @Title: doUpdateDelta
	 * @Description: update the Delta singly
	 * @param ContentID
	 * @param Delta
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean doUpdateDelta(int ContentID, String Delta) throws SQLException {
		String query = "update Content set Delta=? where ContentID=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, Delta);
		ps.setInt(2, ContentID);
		if(ps.executeUpdate() == 0){
			ps.close();
			return false;
		}
		ps.close();
		return true;
	}
	
	@Override
	/**
	 * @Title: doFindUriID
	 * @Description: Find the ContentID By a special ContentID
	 * 				 return -1 if finding UrlID failing
	 * @param ContentID
	 * @return int
	 * @throws Exception
	 */
	public int doFindUrlID(int ContentID) throws Exception {
		String query = "select UrlID from Content where ContentID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, ContentID);
		ResultSet rs = ps.executeQuery();
		int UrlID = -1;		//-1 stands for finding UrlID failing
		while(rs.next()){
			UrlID = rs.getInt(1);
		}
		rs.close();ps.close();
		return UrlID;
	}

	@Override
	/**
	 * @Title: doFindHtml
	 * @Description: Find Html code By ContentID
	 * @param ContentID
	 * @return String
	 * @throws Exception
	 */
	public String doFindHtml(int ContentID) throws Exception {
		String query = "select Html from Content where ContentID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, ContentID);
		ResultSet rs = ps.executeQuery();
		String Html = null;
		while(rs.next()){
			Html = rs.getString(1);
		}
		rs.close();ps.close();
		return Html;
	}

	@Override
	/**
	 * @Title: doFindDelta
	 * @Description: Find Delta by ContentID 
	 * @param ContentID
	 * @return String
	 * @throws Exception
	 */
	public String doFindDelta(int ContentID) throws Exception {
		String query = "select Delta from Content where ContrneID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, ContentID);
		ResultSet rs = ps.executeQuery();
		String Delta = null;
		while(rs.next()){
			Delta = rs.getString(1);
		}
		rs.close();ps.close();
		return Delta;
	}

	@Override
	/**
	 * @Title: doFindAll
	 * @Description: Find All Messages By ContentID
	 * @param ContentID
	 * @return Content
	 * @throws Exception
	 */
	public Content doFindAll(int ContentID) throws Exception {
		String query = "select * from Content where ContentID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, ContentID);
		ResultSet rs = ps.executeQuery();
		Content newContent = null;//new Content(ContentID);
		while(rs.next()){
			newContent = new Content(ContentID);
			newContent.setUrlID(rs.getInt(2));
			newContent.setHtml(rs.getString(3));
			newContent.setDelta(rs.getString(4));
		}
		rs.close();ps.close();
		return newContent;
	}

	@Override
	/**
	 * 
	 * @Title: doFindAllByUrlID
	 * @Description: ͨ��UrlID��Content���в��Ҷ�Ӧ��Content����������
	 * @param UrlID
	 * @return Content
	 * @throws Exception
	 */
	public Content doFindAllByUrlID(int UrlID) throws Exception {
		String query = "select * from Content where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, UrlID);
		ResultSet rs = ps.executeQuery();
		Content newContent = null;
		while(rs.next()){
			newContent = new Content(rs.getInt(1));
			newContent.setUrlID(UrlID);
			newContent.setHtml(rs.getString(3));
			newContent.setDelta(rs.getString(4));
		}
		rs.close();ps.close();
		return newContent;
	}
	
	@Override
	/**
	 * 
	 * @Title: doFindAllChanged
	 * @Description: ͨ查找所有更新了Delta的Content，返回Content的LinkedList
	 * @return LinkedList<Content>
	 * @throws Exception
	 */
	public LinkedList<Content> doFindAllChanged() throws Exception{
		String query = "select * from Content where Delta!=\"\"";
		Statement stat = (Statement) conn.createStatement();
		ResultSet rs = stat.executeQuery(query);
		LinkedList<Content> result = new LinkedList<Content>();
		while(rs.next()){
			Content newContent = new Content();
			newContent.setContentID(rs.getInt(1));
			newContent.setUrlID(rs.getInt(2));
			newContent.setHtml(rs.getString(3));
			newContent.setDelta(rs.getString(4));
			result.add(newContent);
		}
		rs.close();stat.close();		
		return result;
	}
	
	@Override
	/**
	 * @Title: IsExist
	 * @Description: Judge Whether the Url Info in Which the UrlID is Given Has Existed
	 * @param ContentID
	 * @return boolean: true is exist and false is not exist
	 * @throws Exception
	 */
	public boolean isExist(int ContentID) throws Exception {
		String query = "select count(*) from Content where ContentID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, ContentID);
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

}
