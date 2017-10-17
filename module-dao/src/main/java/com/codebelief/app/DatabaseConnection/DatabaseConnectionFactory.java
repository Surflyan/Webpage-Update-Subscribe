/*
 * @(#)SeeUTomorrow --- DatabaseConnectionFactory.java 
 */
package com.codebelief.app.DatabaseConnection;

import java.io.IOException;

/**
 * 
 * @ClassName: DatabaseConnectionFactory
 * @Description: Return an instantiated MySQLDatabaseConnection Object
 * @author ����
 * @date 2017��10��13��
 *
 */
public class DatabaseConnectionFactory {
	public static MySQLDatabaseConnection getMySQLDatabaseConnection() throws IOException{
		return new MySQLDatabaseConnection();
	}
}
