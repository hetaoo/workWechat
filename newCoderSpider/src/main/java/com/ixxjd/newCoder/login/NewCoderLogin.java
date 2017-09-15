package com.ixxjd.newCoder.login;

import com.ixxjd.spider.SpiderNewCoderQuestion;
import com.ixxjd.utils.Constants;
import com.xiaoleilu.hutool.http.Header;
import com.xiaoleilu.hutool.http.HttpRequest;
import com.xiaoleilu.hutool.http.HttpResponse;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hetaoo
 * @Description 牛客网模拟登录
 * @Date 2017/9/15
 */
public class NewCoderLogin {
    private static final String EMAIL = "tao.he@hand-china.com";
    private static final String PWD = "123456qq";
    private static final String REMEMBER = "true";

    private static final String URL = "https://www.nowcoder.com/login/do";

    private static final String REFERER = "https://www.nowcoder.com/login?callBack=https%3A%2F%2Fwww.nowcoder.com%2Fprofile%2F9084877";

    public boolean execute(){
        Map<String,List<String>> headMap = new HashMap<String,List<String>>();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("email",EMAIL);
        paramMap.put("pwd",PWD);
        paramMap.put("remember",REMEMBER);

        HttpResponse response = HttpRequest.post(URL).header(Header.USER_AGENT, Constants.USER_AGENT)
                .header(Header.REFERER, REFERER)
                .header(Header.ACCEPT_LANGUAGE, Constants.ACCEPT_LANGUAGE)
                .header(Header.ACCEPT, Constants.ACCEPT)
                .form(paramMap).execute();
        List<HttpCookie> cookies = response.getCookie();
        List<String> setCookies = response.headers().get("Set-Cookie");


        SpiderNewCoderQuestion.preProcessParse(cookies,setCookies);

        return false;
    }

    public static void main(String[] args) {
        NewCoderLogin newCoderLogin = new NewCoderLogin();
        newCoderLogin.execute();
    }
}
