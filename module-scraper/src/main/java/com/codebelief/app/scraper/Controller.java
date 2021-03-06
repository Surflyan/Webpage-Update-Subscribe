package com.codebelief.app.scraper;

import java.util.LinkedList;
import java.util.Map;

import com.codebelief.app.DatabaseConnection.MySQLDatabaseConnection;
import com.codebelief.app.pushupdate.PushUpdateMessageRealtime;
import com.codebelief.app.rwDatabase.GetURL;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.URLCanonicalizer;

/**
 * @author Surflyan
 */

public class Controller {


	//存储从数据库url 表获取的待爬取url map，addSeed使用
    //同时在MyCrawler 通过调用 get 方法获取，保证读取和写入时数据库中的数据是一致的，避免在爬取时，有新增加url

	public static Map<String, LinkedList<Integer>> urlMap;

	static {
		try {
			MySQLDatabaseConnection.initialDatabaseDeploy();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

    public static void main(String[] args) throws Exception { execute(); }

	public static void execute() throws Exception {
        urlMap = GetURL.getAllUrl();

        String crawlStorageFolder = "/data/crawl/root";

        //设置并行爬虫个数
        int numberOfCrawlers = 2;

        CrawlConfig config = new CrawlConfig();
        //设置存放爬虫中间信息的文件目录
        config.setCrawlStorageFolder(crawlStorageFolder);

        //设置爬取深度
        config.setMaxDepthOfCrawling(0);

        //设置是否爬取二进制数据的页面
        config.setIncludeBinaryContentInCrawling(false);

        //避免极快访问服务器，带来负荷，阻断请求，设置请求前等待200毫秒（默认）
        config.setPolitenessDelay(200);

        //重新开启爬虫
        //config.setResumableCrawling(true);

        //初始化爬虫配置信息
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         *  为每个爬虫添加初始爬取页面，后面以每个页面发现的链接作为爬取对象
         *  将数据库中要爬取的url添加到爬取列表中
         */


        //note: map.values and map.keySet 顺序是否一致（检查）

        for (String url : urlMap.keySet()) {
        	controller.addSeed(url);
        }

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.startNonBlocking(MyCrawler.class, numberOfCrawlers);

        //等待 1 秒钟，防止过快爬取而被网站禁止
        Thread.sleep(1000);

        controller.waitUntilFinish();
    }

}
