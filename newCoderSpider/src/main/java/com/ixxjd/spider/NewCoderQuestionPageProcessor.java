package com.ixxjd.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @author hetaoo
 * @Description  解析牛客网问题结构和答案分析
 * @Date 2017/9/22
 */
public class NewCoderQuestionPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setCycleRetryTimes(5).setTimeOut(60 * 1000);

    @Override
    public void process(Page page) {
        Document root = Jsoup.parse(page.getHtml().toString());

        // 1.获取题目
        Elements select = root.getElementsByClass("question-main");
        String question = select.text();

        // 2.获取答案

        // 3.获取正确结果

        // 4.获取解析

        System.out.println(select.text());

    }

    @Override
    public Site getSite() {
        return this.site;
    }
}
