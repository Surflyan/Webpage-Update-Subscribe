/*
 * @(#)SeeUTomorrow --- DatabaseConnection.java 
 */
package com.codebelief.app.DatabaseConnection;

import java.sql.Connection;
/**
 * @author ����
 * @version 1st   on 2017��10��13��
 */
public interface DatabaseConnection {
	public Connection getConnection();
	
	public void free();
}
