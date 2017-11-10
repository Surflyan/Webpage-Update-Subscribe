package com.codebelief.app.scraper;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * @author Surflyan
 */

public class Controller {
    //public static void main(String[] args) throws Exception {
	public static void execute() throws Exception {
        String crawlStorageFolder = "/data/crawl/root";
        //���ò����������
        int numberOfCrawlers = 2;
 
        CrawlConfig config = new CrawlConfig();
        //���ô�������м���Ϣ���ļ�Ŀ¼
        config.setCrawlStorageFolder(crawlStorageFolder);
        
        //������ȡ���
        config.setMaxDepthOfCrawling(0);
        
        //�����Ƿ���ȡ���������ݵ�ҳ��
        config.setIncludeBinaryContentInCrawling(false);
        
        //���⼫����ʷ��������������ɣ����������������ǰ�ȴ�200���루Ĭ�ϣ�
        config.setPolitenessDelay(200);
        
        //���¿�������
        //config.setResumableCrawling(true);
        
        //��ʼ������������Ϣ 
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
 
      
        /*
         *  Ϊÿ���������ӳ�ʼ��ȡҳ�棬������ÿ��ҳ�淢�ֵ�������Ϊ��ȡ����
         */
        //controller.addSeed("http://today.hit.edu.cn/css2010/style.css?123");
        controller.addSeed("http://today.hit.edu.cn/");
        controller.addSeed("http://today.hit.edu.cn/phb/1.htm");
        controller.addSeed("http://www.sina.com.cn/");
        controller.addSeed("http://www.tsinghua.edu.cn/publish/newthu/index.html");
 
        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.startNonBlocking(MyCrawler.class, numberOfCrawlers);
        
        Thread.sleep(1*1000);
        
        //controller.shutdown();
        controller.waitUntilFinish();
        
    }
}