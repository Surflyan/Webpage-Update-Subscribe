package com.codebelief.app.scraper;

import java.util.regex.Pattern;
import com.codebelief.app.scraper.PageParser;

import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author Surflyan
 */

public class MyCrawler extends WebCrawler {
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
                                                           + "|png|mp3|mp3|zip|gz))$");
     
     /*
      * �������濪ʼ��ҳ���Լ���������ȡ��ҳ�Ĺ���
      * ������ϵͳ��ֻ�����ض�ҳ�棬������ȡ������ҳ��
      * ʼ�շ��أ�false��,����������չʹ��
      */
     @Override
     public boolean shouldVisit(Page referringPage, WebURL url) {
         String href = url.getURL().toLowerCase();
         return !FILTERS.matcher(href).matches()
                && href.startsWith("http://www.ics.uci.edu/");
     }
 
     /*
      * ������ȡ���ҳ�棬��ȡ��Ч���ӣ�ָ�����µ����ӣ�
      */
     @Override
     public void visit(Page page) {
         String url = page.getWebURL().getURL();
         System.out.println("URL: " + url);
 
         if (page.getParseData() instanceof HtmlParseData) {
             HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
             String html = htmlParseData.getHtml();
             String title = htmlParseData.getTitle();
             
             System.out.println("Title: "+ title);           
             String baseUri = url;
             Elements validLinks = PageParser.getLinks(html, baseUri);
             writeFile(html);
             
         }
     }
    
     /*
      * ����ȡ�� html ҳ��д���ļ�����ʱ���ԣ�
      */
     public void writeFile(String html) {
    	 try {
	    	 FileWriter fw = new FileWriter("html.txt");
	    	 fw.write(html);
	    	 fw.flush();
	    	 fw.close();
    	 }catch (IOException e) {
    		 e.printStackTrace();
    	}
     }
}