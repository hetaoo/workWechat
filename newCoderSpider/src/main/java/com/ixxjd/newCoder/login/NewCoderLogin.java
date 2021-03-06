package com.ixxjd.newCoder.login;

import com.ixxjd.spider.SpiderNewCoderQuestion;
import com.ixxjd.utils.Constants;
import com.ixxjd.utils.http.HttpClientUtil;
import com.ixxjd.utils.http.exception.MethodNotSupportException;
import com.ixxjd.utils.http.request.RequestMethod;
import com.ixxjd.utils.http.request.UrlEncodedFormRequest;
import com.ixxjd.utils.http.response.Response;
import com.xiaoleilu.hutool.http.Header;
import org.apache.http.cookie.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hetaoo
 * @Description 牛客网模拟登录
 * @Date 2017/9/15
 */
@Component
public class NewCoderLogin {
    private static final String EMAIL = "tao.he@hand-china.com";
    private static final String PWD = "123456qq";
    private static final String REMEMBER = "true";

    private static final String URL = "https://www.nowcoder.com/login/do";

    private static final String REFERER = "https://www.nowcoder.com/login?callBack=https%3A%2F%2Fwww.nowcoder.com%2Fprofile%2F9084877";

    @Autowired
    private SpiderNewCoderQuestion spiderNewCoderQuestion;

    @Scheduled(fixedRate = 1000 * 60 * 60)
    public boolean execute() throws MethodNotSupportException {
        Map<String,String> headMap = new HashMap<String,String>();
        headMap.put(Header.USER_AGENT.toString(), Constants.USER_AGENT);
        headMap.put(Header.REFERER.toString(), REFERER);
        headMap.put(Header.ACCEPT_LANGUAGE.toString(), Constants.ACCEPT_LANGUAGE);
        headMap.put(Header.ACCEPT.toString(), Constants.ACCEPT);

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("email",EMAIL);
        paramMap.put("pwd",PWD);
        paramMap.put("remember",REMEMBER);

        UrlEncodedFormRequest request = new UrlEncodedFormRequest(URL, RequestMethod.POST);
        request.addHeaders(headMap);
        request.addUrlParams(paramMap);
        request.setUseSSL(true);
        Response response = HttpClientUtil.doRequestInString(request);

        List<Cookie> cookies = Collections.emptyList();
        if (response.getStatusCode() == 200) {
            cookies = response.getCookieStore();
        }

        spiderNewCoderQuestion.uriChain(cookies);

        return false;
    }

    public static void main(String[] args) throws MethodNotSupportException {
        NewCoderLogin newCoderLogin = new NewCoderLogin();
        newCoderLogin.execute();
    }
}
