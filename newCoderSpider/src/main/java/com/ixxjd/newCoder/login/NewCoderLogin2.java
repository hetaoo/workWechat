package com.ixxjd.newCoder.login;

import com.ixxjd.utils.Constants;
import com.ixxjd.utils.http.HttpClientUtil;
import com.ixxjd.utils.http.exception.MethodNotSupportException;
import com.ixxjd.utils.http.request.RequestMethod;
import com.ixxjd.utils.http.request.UrlEncodedFormRequest;
import com.ixxjd.utils.http.response.Response;
import com.xiaoleilu.hutool.http.Header;
import org.apache.http.cookie.Cookie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hetaoo
 * @Description 牛客网模拟登录
 * @Date 2017/9/15
 */
public class NewCoderLogin2 {
    private static final String EMAIL = "tao.he@hand-china.com";
    private static final String PWD = "123456qq";
    private static final String REMEMBER = "true";

    private static final String URL = "https://www.nowcoder.com/login/do";
    private static final String URL2 = "https://www.nowcoder.com/makePaper";

    private static final String REFERER = "https://www.nowcoder.com/login?callBack=https%3A%2F%2Fwww.nowcoder.com%2Fprofile%2F9084877";
    private static final String REFERER2 = "https://www.nowcoder.com/9084877";

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
//        request.setUseSSL(true);
        request.addHeaders(headMap);
        request.addUrlParams(paramMap);
        Response response = HttpClientUtil.doRequestInString(request);

        List<Cookie> cookies = response.getCookieStore();

        Map<String,Object> paramMap2 = new HashMap<>();
        paramMap2.put("source","0");
        paramMap2.put("tagIds","570");
        paramMap2.put("difficulty","3");
        paramMap2.put("questionCount","30");

        Map<String,String> headMap2 = new HashMap<String,String>();
        headMap2.put(Header.USER_AGENT.toString(), Constants.USER_AGENT);
        headMap2.put(Header.REFERER.toString(), REFERER2);
        headMap2.put(Header.ACCEPT_LANGUAGE.toString(), Constants.ACCEPT_LANGUAGE);
        headMap2.put(Header.ACCEPT.toString(), Constants.ACCEPT);

        UrlEncodedFormRequest request2 = new UrlEncodedFormRequest(URL2, RequestMethod.POST);
        request2.addHeaders(headMap2);
        request2.addUrlParams(paramMap2);
        request2.setCookies(cookies);
        request2.setUseSSL(true);
        Response response2 = HttpClientUtil.doRequestInString(request2);

        return false;
    }

    public static void main(String[] args) throws MethodNotSupportException {
        NewCoderLogin2 newCoderLogin = new NewCoderLogin2();
        newCoderLogin.execute();
    }
}
