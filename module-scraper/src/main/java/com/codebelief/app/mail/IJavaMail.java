/*
 * @(#)module-scraper --- IJavaMail.java 
 */
package com.codebelief.app.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 * @author ����
 * @version 1st   on 2017��11��10��
 */
public interface IJavaMail {
	/**
	 * 
	 * @Title: setDeploy
	 * @Description: �����������ã����������ʼ��������Ĳ�������
	 * @return: Session
	 */
	public Session connectDeploy();
	
	/**
	 * 
	 * @Title: generateMail
	 * @Description: generate a new Email
	 * @param session
	 * @param content
	 * @return MimeMessage
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	public MimeMessage generateMail(Session session, String content) 
			throws UnsupportedEncodingException, MessagingException;
	
	/**
	 * 
	 * @Title: sendMail
	 * @Description: send the Email to recipient
	 * @param session
	 * @param message
	 * @throws MessagingException
	 */
	public void sendMail(Session session, MimeMessage message) 
			throws MessagingException;
}

