/*
 * @(#)SeeUTomorrow --- ContentDAOFactory.java 
 */
package com.codebelief.app.DAOFactory;

import com.codebelief.app.DAO.ContentDAOProxy;
import com.codebelief.app.DAO.IContentDAO;
/**
 * 
 * @ClassName: ContentDAOFactory
 * @Description: Return an instantiated UrlDAO
 * @author ����
 * @date 2017��10��14��
 *
 */
public class ContentDAOFactory {
	public static IContentDAO getContentDAOInstance() throws Exception{
		return new ContentDAOProxy();
	}
}
