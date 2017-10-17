/*
 * @(#)SeeUTomorrow --- DAOTest.java 
 */
package com.codebelief.app.test;

import com.codebelief.app.DAO.*;
import com.codebelief.app.DAOFactory.*;
import com.codebelief.app.VO.*;

import java.sql.Time;
/**
 * @author 何涛
 * @version 1st   on 2017年10月14日
 */
public class DAOTest {
	public static void main(String[] args) throws Exception{
		User user=new User("何涛","hetao","15705657659@163.com",Time.valueOf("20:00:00"));
		IUserDAO userDAO=UserDAOFactory.getUserDAOInstance();
		//userDAO.doInsert(user);
		//userDAO.doUpdatePushTime("何涛", Time.valueOf("21:00:00"));
		//userDAO.doUpdatePasswordAndEmail("何涛", "hetao", "15705657659@163.com");
		/*User newUser=userDAO.doFindAll("何涛");
		System.out.println("UserName:"+newUser.getUserName());
		System.out.println("Password:"+newUser.getPassword());
		System.out.println("Email:"+newUser.getEmail());
		System.out.println("PushTime:"+newUser.getPushTime());*/
		/*String Password=userDAO.doFindPassword("何涛");
		System.out.println("Passowrd:"+Password);
		String Email=userDAO.doFindEmail("何涛");
		System.out.println("Email:"+Email);
		Time PushTime=userDAO.doFindPushTime("何涛");
		System.out.println("PushTime:"+PushTime);*/
		//userDAO.Free();
		
		Url url=new Url(10010,"何涛","www.Github.com");
		IUrlDAO urlDAO=UrlDAOFactory.getUrlDAOInstance();
		//urlDAO.doInsert(url);
		/*Url newUrl=urlDAO.doFindAll(10010);
		System.out.println(newUrl.getUrl());
		System.out.println(newUrl.getUserName());
		System.out.println(newUrl.getUrlID());
		if(newUrl.isEnable())	System.out.println("true");
		if(newUrl.isRealTimePush())	System.out.println("true");*/
		/*System.out.println(urlDAO.doFindUrl(10010));
		System.out.println(urlDAO.doFindUserName(10010));
		System.out.println(""+urlDAO.doFindEnable(10010));
		System.out.println(""+urlDAO.doFindRealTimePush(10010));*/
		//urlDAO.doUpdate(10010, "www.stackOverflow.com", false, false);
		//urlDAO.doDelete(10010);
		//urlDAO.Free();
		
		Content content = new Content(1001,10010,"question","<html><head></head></html>","<head></head>");
		IContentDAO contentDAO=ContentDAOFactory.getContentDAOInstance();
		//contentDAO.doInsert(content);
		/*Content newContent=contentDAO.doFindAll(1001);
		System.out.println(newContent.getUrlID());
		System.out.println(newContent.getTitle());
		System.out.println(newContent.getHtml());
		System.out.println(newContent.getDelta());*/
		/*System.out.println(contentDAO.doFindUrlID(1001));
		System.out.println(contentDAO.doFindTitle(1001));
		System.out.println(contentDAO.doFindHtml(1001));
		System.out.println(contentDAO.doFindDelta(1001));*/
		//contentDAO.doUpdateHtmlAndDelta(1001, "<html><body></body></html>", "<body></body>");
		//contentDAO.doDelete(1001);
		//contentDAO.Free();
		System.out.println("Success!");
	}
}
