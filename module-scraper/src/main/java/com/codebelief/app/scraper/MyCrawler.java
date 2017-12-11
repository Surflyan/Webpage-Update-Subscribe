package com.codebelief.app.scraper;

import java.util.LinkedList;

import com.codebelief.app.compare.ContentHandler;
import com.codebelief.app.compare.SingleUpdateRecord;
import com.codebelief.app.scraper.PageParser;


import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author Surflyan <yanjiliang0128@outlook.com>
 */

public class MyCrawler extends WebCrawler {
     /*
      * ����ҳ������Щurl��Ҫץȡ������true����Ҫ��
      * ��һ��������װ�˵�ǰ��ȡ��ҳ����Ϣ���ڶ���������װ�˵�ǰ��ȡҳ���url��Ϣ
      * ������ϵͳ��ֻ�����ض�ҳ�棬������ȡ������ҳ��
      * ʼ�շ��أ�false��,����������չʹ��
      */
     @Override
     public boolean shouldVisit(Page referringPage, WebURL url) {
         return false;
     }
 
     /*
      * ������ȡ���ҳ��
      * ��ȡ��Ч���ӣ�ָ�����µ����ӣ�������writeLinkToDBд�����ݿ�content��
      * 
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
             
             writeContentToDB(url,validLinks);   //д�����ݿ�
         	 System.out.println("Saved updates to database.");
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
     
     /*
      * ����ȡ��ҳ�����֮���õ���Ч����д�����ݿ�
      * ͨ��ʹ�ô˴���ȡҳ�����url����������controller ��urlMap�ҵ���Ӧ��urlID��ֵ��
      * ����ʹ����Crawler4j ��Ŀ������ȡʱ�޷�������������urlID����ʹ֮����ȡurl �󶨣� ����ֻ�����»�ȡurlID
      */
     private void writeContentToDB(String url, Elements validLinks) {
    	 //������ӱ��������<singleUpdateRecord> �Զ�����������
    	 LinkedList<SingleUpdateRecord> updateRecords = new LinkedList<SingleUpdateRecord>();

    	 for (int linkNum = 0; linkNum < validLinks.size(); linkNum++) { 	 
    		 String linkHref = validLinks.get(linkNum).attr("href");
    		 String linkText = validLinks.get(linkNum).text();
//    		 System.out.println(linkText);
//    		 System.out.println(linkHref);
    		 updateRecords.add(new SingleUpdateRecord(linkText, linkHref));
    	 }  		 
    	
  	 //��controller ��ȡurlMap, ȷ�������crawler seed ʱ����һ�¡�
  	 LinkedList<Integer> urlIDList = Controller.urlMap.get(url);
  	 
  	 
     for(int urlID: urlIDList) {
        //System.out.println("urlID"+ urlID);
        ContentHandler.updateProcess(urlID, updateRecords);
        }
     }
     
}
