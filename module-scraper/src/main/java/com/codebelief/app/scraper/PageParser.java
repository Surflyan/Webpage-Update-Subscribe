package com.codebelief.app.scraper;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

/**
 * @author Surflyan
 */

public class PageParser {
    
    /*
     * ���������
     * ��ֹ��վ�ű�����
     * ���Ʊ�ǩ��Χ������Ӧ��ȡHTML��������
     */
	private final static Whitelist myWhiteList  = Whitelist.relaxed();
	static {
		myWhiteList.removeTags("img");
		
	}
	
	/* 
	 * ���� Html ҳ��, ���հ������涨Ԫ����ȡ HTML Ԫ��
	 */
	private static String htmlFilter(String html,String baseUri) {
		if(StringUtil.isBlank(html)) return "";
		return Jsoup.clean(html, baseUri,myWhiteList);
	}
	
	
    /* 
     * �������ӣ���ȡ��Ч��������
	 * ���˵������ı���������5���ֽڵ�����
	 */
    private static Elements linkFilter(Document doc) {  	
    	// ��ȡ����a[href]
    	Elements links = doc.select("a[href]");  	
    	// ����Text �ַ�С��5�����ӣ�һ��Ϊ�������ӣ���Ŀ������
    	int linkNum = 0;

    	System.out.println("linkNumBefore: " + links.size());
    	for (linkNum = 0; linkNum < links.size(); linkNum++) {
    		   	 
    		 String linkHref = links.get(linkNum).attr("href");
    		 String linkText = links.get(linkNum).text();
    		
    		 if(linkText.length() <= 5) {
    			 links.remove(linkNum);
    			 linkNum--;	 
    			 continue;
    		 }  		 
    		 //System.out.println("Href:" + linkHref);
    		 //System.out.println("Text: " + linkText);
    	}
        System.out.println("linkNumAfter = " + links.size());
   
        return links;
    }
    
    /* 
     *  HTML ��ȡ��Ч�������Ӵ���ܵ�����
     *  ���ذ�����Ч���ӵ� Elements �б�
     */
    public static Elements getLinks(String html, String baseUri) {
    	String filtHtml = htmlFilter(html,baseUri);
    	Document doc = Jsoup.parse(filtHtml);
   
        return linkFilter(doc);
    }
    
}
