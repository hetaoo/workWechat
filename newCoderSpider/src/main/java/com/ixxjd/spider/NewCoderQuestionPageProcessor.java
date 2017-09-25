package com.ixxjd.spider;

import com.ixxjd.domain.Question;
import com.ixxjd.utils.UrlMapConvertUtil;
import com.ixxjd.utils.http.HttpClientUtil;
import com.ixxjd.utils.http.exception.MethodNotSupportException;
import com.ixxjd.utils.http.request.Request;
import com.ixxjd.utils.http.response.Response;
import com.xiaoleilu.hutool.http.Header;
import org.apache.http.cookie.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author hetaoo
 * @Description  解析牛客网问题
 * @Date 2017/9/22
 */
public class NewCoderQuestionPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setCycleRetryTimes(5).setTimeOut(60 * 1000);

    private static String SINGLE = "[单选题]";

    private static String MULTIPLE = "[多选题]";

    @Override
    public void process(Page page) {
        Document root = Jsoup.parse(page.getHtml().toString());

        // 1.获取题目
        Elements selectElement = root.getElementsByClass("question-main");
        String title = selectElement.text();

        // 2.获取选项
        Elements slectionElements = root.select("div.result-answer-item > pre");
        List<String> slections = new ArrayList<>();
        for (Element selection : slectionElements) {
            slections.add(selection.text());
        }

        // 3.获取正确答案
        Elements answerElement =  root.select("div.result-subject-answer > h1");
        String answer = answerElement.text();
        answer = answer.split("你的答案")[0];
        answer = answer.split("正确答案: ")[1].replace(" ","").replace(" ",",").trim();
        String[] answers = answer.split(",");

        // 4.问题提示
        if (answers.length == 1){
            title = SINGLE + " " + title;
        }else {
            title = MULTIPLE + " " + title;
        }

        // 5. 拼装返回参数
        String url = page.getRequest().getUrl();
        Map<String, Object> urlParams = UrlMapConvertUtil.getUrlParams(url.split("\\?")[1]);
        String qid = (String) urlParams.get("qid");

        Question question = new Question();
        question.setqId(qid);
        question.setTitle(title);
        question.setSelections(slections);
        question.setAnswers(answers);

        page.putField("result",question);

        // 4.获取评论
        // 4.1 获取推荐评论
//        String url = page.getRequest().getUrl();
//        Map<String, Object> urlParams = UrlMapConvertUtil.getUrlParams(url.split("\\?")[1]);
//        String qid = (String) urlParams.get("qid");
//        Request request = null;
//
//        List<Cookie> cookies = (List<Cookie>)page.getRequest().getExtra("cookies");
//        try {
//            request = new Request("https://www.nowcoder.com/comment/recommendComment?questionId=" + qid);
//            request.setCookies(cookies);
//            request.addHeader(Header.REFERER.toString(),page.getRequest().getUrl());
//        } catch (MethodNotSupportException e) {
//
//        }
//        if (request != null) {
//            Response response = HttpClientUtil.doRequestInString(request);
//            System.out.println(response.getResponseText());
//        }
    }

    @Override
    public Site getSite() {
        return this.site;
    }
}
