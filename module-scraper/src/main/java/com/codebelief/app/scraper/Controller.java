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
    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "/data/crawl/root";
        //设置并行爬虫个数
        int numberOfCrawlers = 1;
 
        CrawlConfig config = new CrawlConfig();
        //设置存放爬虫中间信息的文件目录
        config.setCrawlStorageFolder(crawlStorageFolder);
        
        //设置爬取深度
        config.setMaxDepthOfCrawling(0);
        
        //设置是否爬取二进制数据的页面
        config.setIncludeBinaryContentInCrawling(false);
        
        //初始化爬虫配置信息 
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
 
      
        /*
         *  为每个爬虫添加初始爬取页面，后面以每个页面发现的链接作为爬取对象
         */
        //controller.addSeed("http://today.hit.edu.cn/phb/0.htm");
        controller.addSeed("http://today.hit.edu.cn/");
        //controller.addSeed("http://today.hit.edu.cn/phb/1.htm");
        //controller.addSeed("http://www.sina.com.cn/");
 
        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.startNonBlocking(MyCrawler.class, numberOfCrawlers);
        
        Thread.sleep(1*1000);
        
        controller.shutdown();
        controller.waitUntilFinish();
        
    }
}