package com.codebelief.app.scraper;

import java.util.LinkedList;
import java.util.Map;
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
 * @author Surflyan <yanjiliang0128@outlook.com>
 */

public class MyCrawler extends WebCrawler {
	//����ƥ��ָ���ĺ�׺�ļ�
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
                                                           + "|png|mp3|mp3|zip|gz))$");
     
     /*
      * ������Щurl��Ҫץȡ������true����Ҫ��
      * ��һ��������װ�˵�ǰ��ȡ ��ҳ����Ϣ���ڶ���������װ�˵�ǰ��ȡҳ���url��Ϣ
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
             
             writeLinkToDB(url,validLinks);   //д�����ݿ�
            
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
      * ͨ���Ƚϴ˴���ȡҳ�����url �� �����ȡ����ʱurlMap ���url �Աȣ��ҵ�urlID���Ӷ�д�����ݱ�����ݱ�����ΪurlID��
      * ����ʹ����Crawler4j ��Ŀ������ȡʱ�޷�������������urlID����ʹ֮����ȡurl �󶨣� ����ֻ��ͨ�����±ȶ��ҵ�urlID��
      */
     //TO-DO WriteLinkToDB(��δʵ��,��ǰ����)
     private void writeLinkToDB(String url, Elements validLinks) {
    	 
    	 LinkedList<String> HrefList;    //�������
    	 LinkedList<String> TextList;    //������ӱ���
    	 Map<Integer, String> urlMap = Controller.urlMap;  //��controller ��ȡurlMap ��ȷ�������crawler seed ʱ����һ�¡�
    	 
    	 for (int linkNum = 0; linkNum < validLinks.size(); linkNum++) { 	 
    		 String linkHref = validLinks.get(linkNum).attr("href");
    		 String linkText = validLinks.get(linkNum).text();
    		 HrefList.add(linkHref);
    		 TextList.add(linkText);
    		
    	 }  		 
         
    	 // ����urlMap �е� url��ͨ������ȡ���ҳ����� url �Ƚϣ����ҵ���ӦurlID���Ӷ�д���Ӧ��content ���
         for (Map.Entry<Integer, String> entry : urlMap.entrySet()) {
        	 if (entry.getValue().equals(url)) {
        		 WriteLinkToDB(entry.getKey(),HrefList,TextList);
        	 }
         }
       }
     
}