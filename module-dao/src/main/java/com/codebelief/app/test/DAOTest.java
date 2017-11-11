package com.codebelief.app.test;

import com.codebelief.app.DAO.*;
import com.codebelief.app.DAOFactory.*;
import com.codebelief.app.DatabaseConnection.DatabaseConnectionFactory;
import com.codebelief.app.DatabaseConnection.MySQLDatabaseConnection;
import com.codebelief.app.VO.*;
import com.codebelief.app.DAOFactory.*;

import java.sql.Time;
/**
 * @author ����
 * @version 1st   on 2017��10��14��
 */
public class DAOTest {
	public static void main(String[] args) throws Exception{
		MySQLDatabaseConnection.initialDatabaseDeploy();
		//User user = new User("����","12345","12345678@163.com",Time.valueOf("18:00:00"));
		//Url url = new Url(0,"����","www.baidu.com","�ٶ�",true,false);
		//IUrlDAO urlDAO = UrlDAOFactory.getUrlDAOInstance();
		//urlDAO.doUpdate(url);
		//urlDAO.doUpdateTitle(0, "http://google.com");
		//urlDAO.doUpdateUrl(0, "�ȸ�"); 
		//urlDAO.doUpdateEnable(0, false);
		//urlDAO.doUpdateRealTimePush(0, true);
		//urlDAO.free();
		Content content = new Content(0,0,"aa","aa");
		IContentDAO contentDAO = ContentDAOFactory.getContentDAOInstance();
		//contentDAO.doInsert(content);
		contentDAO.doUpdateDelta(0, "bb");
		contentDAO.doUpdateHtml(0, "bb");
		contentDAO.free();
		
		System.out.println("Success!");
	}
}
