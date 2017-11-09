package com.codebelief.app.DatabaseConnection;

import java.sql.Connection;
/**
 * @author ����
 * @version 1st   on 2017��10��13��
 */
public interface DatabaseConnection {
	
	
	/**
	 * 
	 * @Title: getConnection
	 * @Description: return the Connection to the database
	 * @return Connection
	 */
	public Connection getConnection();
	
	/**
	 * 
	 * @Title: free
	 * @Description: Close the connection to the database
	 */
	public void free();
}
