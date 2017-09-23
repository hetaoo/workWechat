package com.ixxjd.spider;

import com.ixxjd.utils.Constants;
import com.ixxjd.utils.ErrorCode;
import com.ixxjd.utils.UrlMapConvertUtil;
import com.ixxjd.utils.http.HttpClientUtil;
import com.ixxjd.utils.http.exception.MethodNotSupportException;
import com.ixxjd.utils.http.request.Request;
import com.ixxjd.utils.http.request.RequestMethod;
import com.ixxjd.utils.http.request.UrlEncodedFormRequest;
import com.ixxjd.utils.http.response.Response;
import com.xiaoleilu.hutool.http.Header;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.cookie.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

import java.util.*;

/**
 * @author hetaoo
 * @Description 爬取牛客网题目
 * @Date 2017/9/15
 */
public class SpiderNewCoderQuestion {
    private static final String URL = "https://www.nowcoder.com/makePaper";

    private static final String REFERER = "https://www.nowcoder.com/intelligentTest";

    public void uriChain(List<Cookie> cookies) throws MethodNotSupportException {
        if (CollectionUtils.isEmpty(cookies)) {
            throw new RuntimeException(ErrorCode.COOKIES_NULL.getDesc());
        }

        Map<String, Object> questionUrl = getQuestionUrl(cookies);
        List<String> qIdList = parseQuestionId(questionUrl);
        preProcessParse(questionUrl,qIdList);
    }

    /**
     * 获取问题真实链接
     * @param cookies
     * @return
     * @throws MethodNotSupportException
     */
    private Map<String,Object> getQuestionUrl(List<Cookie> cookies) throws MethodNotSupportException {
        Map<String,Object> result = new HashMap<String,Object>();

        //构建查询参数
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("source",0);
        paramMap.put("tagIds",570); // 570 -> java 题目
        paramMap.put("difficulty",3); //难度级别
        paramMap.put("questionCount",30); // 题量

        //构建请求头
        Map<String,String> headMap = new HashMap<String,String>();
        headMap.put(Header.USER_AGENT.toString(), Constants.USER_AGENT);
        headMap.put(Header.REFERER.toString(), REFERER);
        headMap.put(Header.ACCEPT_LANGUAGE.toString(), Constants.ACCEPT_LANGUAGE);
        headMap.put(Header.ACCEPT.toString(), Constants.ACCEPT);

        UrlEncodedFormRequest request = new UrlEncodedFormRequest(URL, RequestMethod.POST);
        request.addHeaders(headMap);
        request.addUrlParams(paramMap);
        request.setCookies(cookies);
        request.setUseSSL(true);
        Response response = HttpClientUtil.doRequestInString(request);

        if (response.getStatusCode() == 302) {
            String location = response.getHeader("Location").getValue();
            result.put("url",location);
            result.put("cookies",response.getCookieStore());
            return result;
        }

        return Collections.emptyMap();
    }

    private List<String> parseQuestionId(Map<String,Object> urlCookiesMap) throws MethodNotSupportException {
        if (MapUtils.isEmpty(urlCookiesMap)) {
            throw new RuntimeException(ErrorCode.COOKIES_NULL.getDesc());
        }

        List<String> result = new ArrayList<String>();

        //构建请求头
        Map<String,String> headMap = new HashMap<String,String>();
        headMap.put(Header.USER_AGENT.toString(), Constants.USER_AGENT);
        headMap.put(Header.REFERER.toString(), URL);
        headMap.put(Header.ACCEPT_LANGUAGE.toString(), Constants.ACCEPT_LANGUAGE);
        headMap.put(Header.ACCEPT.toString(), Constants.ACCEPT);

        // 提交请求
        Request request = new Request(urlCookiesMap.get("url").toString());
        request.addHeaders(headMap);
        request.setCookies((List<Cookie>) urlCookiesMap.get("cookies"));
        Response response = HttpClientUtil.doRequestInString(request);

        // 获取新的cookies
        urlCookiesMap.put("cookies",response.getCookieStore());

        // 解析html
        Document root = Jsoup.parse(response.getResponseText());
        Elements elements = root.getElementsByClass("answer-sheet-box");
        Elements selects = elements.select("li a");
        for (Element dataQid : selects){
            // 将解析结果加入结果集合
            result.add(dataQid.attr("data-qid"));
        }
        return result;
    }

    public void processQuesiton(Map<String,Object> urlCookiesMap,List<String> qIdList){
        if (MapUtils.isEmpty(urlCookiesMap)) {
            throw new RuntimeException(ErrorCode.COOKIES_NULL.getDesc());
        }



    }
    /**
     * 解析前处理
     */
    public void preProcessParse(Map<String,Object> urlCookiesMap,List<String> qIdList ) throws MethodNotSupportException {
        // 1、获取tid
        String url = urlCookiesMap.get("url").toString();
        Map<String, Object> urlParams = UrlMapConvertUtil.getUrlParams(url.split("\\?")[1]);
        String tid = (String) urlParams.get("tid");

        // 构建spider
        Spider spider = Spider.create(new NewCoderQuestionPageProcessor());
        Site site = spider.getSite();
        // 设置cookie
        for (Cookie cookie : (List<Cookie>)urlCookiesMap.get("cookies")){
            site.addCookie(cookie.getName(),cookie.getValue());
        }
        // 设置抓取url
        Iterator<String> iterator = qIdList.iterator();
        while (iterator.hasNext()) {
            String qid = iterator.next();
            spider.addUrl("https://www.nowcoder.com/test/question/done?tid="+tid+"&qid="+qid);
        }
        // 开启30个线程抓取并启动
        spider.thread(30).run();
    }
}
