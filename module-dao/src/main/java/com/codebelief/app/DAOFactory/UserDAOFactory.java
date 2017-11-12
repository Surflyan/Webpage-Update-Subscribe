package com.codebelief.app.DAOFactory;

import com.codebelief.app.DAO.IUserDAO;
import com.codebelief.app.DAO.UserDAOProxy;
/**
 * 
 * @ClassName: UserDAOFactory
 * @Description: Return an instantiated UserDAO
 * @author ����
 * @date 2017��10��14��
 *
 */
public class UserDAOFactory {
	public static IUserDAO getUserDAOInstance() throws Exception{
		return new UserDAOProxy();
	}
}
