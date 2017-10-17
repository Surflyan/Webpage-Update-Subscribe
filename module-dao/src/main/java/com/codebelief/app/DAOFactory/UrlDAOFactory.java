/*
 * @(#)SeeUTomorrow --- UrlDAOFactory.java 
 */
package com.codebelief.app.DAOFactory;

import com.codebelief.app.DAO.IUrlDAO;
import com.codebelief.app.DAO.UrlDAOProxy;
/**
 * 
 * @ClassName: UrlDAOFactory
 * @Description: Return an instantiated UrlDAO 
 * @author ����
 * @date 2017��10��14��
 *
 */
public class UrlDAOFactory {
	public static IUrlDAO getUrlDAOInstance() throws Exception{
		return new UrlDAOProxy();
	}
}
