package com.ixxjd.spider;

import com.ixxjd.utils.Constants;
import com.ixxjd.utils.UrlMapConvertUtil;
import com.xiaoleilu.hutool.http.Header;
import com.xiaoleilu.hutool.http.HttpRequest;
import com.xiaoleilu.hutool.http.HttpResponse;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hetaoo
 * @Description 爬取牛客网题目
 * @Date 2017/9/15
 */
public class SpiderNewCoderQuestion {
    private static final String URL = "https://www.nowcoder.com/makePaper?";

    private static final String REFERER = "https://www.nowcoder.com/intelligentTest";

    public void doSpider(){




    }

    /**
     * 解析处理
     */
    public static void preProcessParse(List<HttpCookie> cookies,List<String> setCookies){

        //构建查询参数
        Map<String,Object> queryParam = new HashMap<>();
        queryParam.put("source",3);
        queryParam.put("tagIds",570); // 570 -> java 题目
        queryParam.put("difficulty",3); //难度级别
        queryParam.put("questionCount",30); // 题量

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("source",3);
        paramMap.put("tagIds",570);
        paramMap.put("difficulty",3);
        paramMap.put("questionCount",30);

        HttpRequest header = HttpRequest.get("https://www.nowcoder.com/intelligentTest").header(Header.USER_AGENT, Constants.USER_AGENT).header(Header.REFERER, REFERER).header(Header.ACCEPT_LANGUAGE, Constants.ACCEPT_LANGUAGE).header(Header.ACCEPT, Constants.ACCEPT);
        for (String setCookie : setCookies) {
            header.header(Header.SET_COOKIE,setCookie);
        }
        for (HttpCookie cookie : cookies) {
            header.cookie(cookie);
        }
        HttpResponse response = header.execute();

        header = HttpRequest.get(URL + UrlMapConvertUtil.getUrlParamsByMap(queryParam)).header(Header.USER_AGENT, Constants.USER_AGENT).header(Header.REFERER, "https://www.nowcoder.com/intelligentTest").header(Header.ACCEPT_LANGUAGE, Constants.ACCEPT_LANGUAGE).header(Header.ACCEPT, Constants.ACCEPT);
        for (String setCookie : response.headers().get("Set-Cookie")) {
            header.header(Header.SET_COOKIE,setCookie);
        }
        for (HttpCookie cookie : response.getCookie()) {
            header.cookie(cookie);
        }

        HttpResponse response1 = header.execute();

        System.out.println(response1);
    }
}
