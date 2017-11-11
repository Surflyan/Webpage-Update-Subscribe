package com.codebelief.app.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 
 * @ClassName: DatabaseConnectionFactory
 * @Description: Return an instantiated MySQLDatabaseConnection Object
 * @author ����
 * @date 2017��10��13��
 *
 */
public class DatabaseConnectionFactory {
	public static MySQLDatabaseConnection getMySQLDatabaseConnection() throws ClassNotFoundException, SQLException{
		return new MySQLDatabaseConnection();
	}
}
