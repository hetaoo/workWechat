package com.ixxjd.utils.http;


import com.ixxjd.utils.http.exception.MethodNotSupportException;
import com.ixxjd.utils.http.request.BaseEntityRequest;
import com.ixxjd.utils.http.request.Request;
import com.ixxjd.utils.http.response.Response;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * httpclient工具类
 */
public class HttpClientUtil {

    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_CONNECT_TIMEOUT = 10 * 1000;
    private static final int MAX_SOCKET_TIMEOUT = 10 * 1000;
    private static final int MAX_CONNECT_REQUEST_TIMEOUT = 10 * 1000;
    private static final int MAX_TOTAL = 100;

    static {
        // connecting pools
        connMgr = new PoolingHttpClientConnectionManager();

        //pool size
        connMgr.setMaxTotal(MAX_TOTAL);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

        RequestConfig.Builder configBuilder = RequestConfig.custom();

        // timeout unit : milliseconds
        configBuilder.setConnectTimeout(MAX_CONNECT_TIMEOUT);
        configBuilder.setSocketTimeout(MAX_SOCKET_TIMEOUT);
        configBuilder.setConnectionRequestTimeout(MAX_CONNECT_REQUEST_TIMEOUT);

        // check availability
        //configBuilder.setStaleConnectionCheckEnabled(true);
        requestConfig = configBuilder.build();
    }

    /**
     * 返回结果为String
     *
     * @param request request object
     * @return response object
     */
    public static Response doRequestInString(Request request) {
        try {
            HttpRequestBase baseRequest = request.getHttpRequest();

            Header[] headers = request.getAllHeaders();
            if (null != headers && headers.length > 0) {
                baseRequest.setHeaders(headers);
            }
            baseRequest.setConfig(requestConfig);
            if (request instanceof BaseEntityRequest) { //to post domain
                ((HttpEntityEnclosingRequestBase) baseRequest).setEntity(((BaseEntityRequest) request).getEntity());
            }

            //add cookie
            List<Cookie> cookies = request.getCookies();
            CookieStore cookieStore = new BasicCookieStore();
            if (CollectionUtils.isNotEmpty(cookies)) {
                for (Cookie cookie : cookies) {
                    BasicClientCookie basicClientCookie = new BasicClientCookie(cookie.getName(), cookie.getValue());
                    basicClientCookie.setDomain(cookie.getDomain());
                    basicClientCookie.setPath(cookie.getPath());
                    basicClientCookie.setExpiryDate(cookie.getExpiryDate());
                    basicClientCookie.setComment(cookie.getComment());
                    basicClientCookie.setVersion(cookie.getVersion());
                    cookieStore.addCookie(basicClientCookie);
                }
            }

            return toXHttpResponseInString(baseRequest, request.getResponseDefaultCharset(), cookieStore, request.isUseSSL());
        } catch (IOException e) {
            e.printStackTrace();
            return getErrorXResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (MethodNotSupportException mnse) {
            return getErrorXResponse(HttpServletResponse.SC_METHOD_NOT_ALLOWED, mnse.getMessage());
        }
    }

    /**
     * 返回结果类型为InputStream
     *
     * @param request
     * @return
     */
    public static Response doRequestInInputStream(Request request) {
        try {
            HttpRequestBase baseRequest = request.getHttpRequest();

            Header[] headers = request.getAllHeaders();
            if (null != headers && headers.length > 0) {
                baseRequest.setHeaders(headers);
            }
            baseRequest.setConfig(requestConfig);
            if (request instanceof BaseEntityRequest) { //to post domain
                ((HttpEntityEnclosingRequestBase) baseRequest).setEntity(((BaseEntityRequest) request).getEntity());
            }

            //add cookie
            List<Cookie> cookies = request.getCookies();
            CookieStore cookieStore = new BasicCookieStore();
            if (CollectionUtils.isNotEmpty(cookies)) {
                for (Cookie cookie : cookies) {
                    BasicClientCookie basicClientCookie = new BasicClientCookie(cookie.getName(), cookie.getValue());
                    basicClientCookie.setDomain(cookie.getDomain());
                    basicClientCookie.setPath(cookie.getPath());
                    basicClientCookie.setExpiryDate(cookie.getExpiryDate());
                    basicClientCookie.setComment(cookie.getComment());
                    basicClientCookie.setVersion(cookie.getVersion());
                    cookieStore.addCookie(basicClientCookie);
                }
            }

            return toXHttpResponseInInputStream(baseRequest, request.getResponseDefaultCharset(), cookieStore, request.isUseSSL());
        } catch (IOException e) {
            e.printStackTrace();
            return getErrorXResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (MethodNotSupportException mnse) {
            return getErrorXResponse(HttpServletResponse.SC_METHOD_NOT_ALLOWED, mnse.getMessage());
        }
    }

    private static Response getErrorXResponse(int statusCode, String errorMsg) {
        return new Response(statusCode, errorMsg, null, null, null);
    }

    /**
     * 返回结果为String
     *
     * @param request
     * @param defaultCharset
     * @param useSSL
     * @return
     * @throws IOException
     */
    private static Response toXHttpResponseInString(HttpUriRequest request, String defaultCharset
            , CookieStore cookieStore, boolean useSSL) throws IOException {
        try (CloseableHttpClient httpClient = getHttpsClient(cookieStore)) {
            HttpResponse response = httpClient.execute(request);
            Response result = new Response(response.getStatusLine().getStatusCode(), response.getAllHeaders());
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //content encoding
                String charset = (null == entity.getContentEncoding()) ? defaultCharset : entity.getContentEncoding().getValue();
                result.setContentEncoding(charset);

                //content type
                String contentType = (null == entity.getContentType()) ? "" : entity.getContentType().getValue();
                result.setContentType(contentType);
                result.setCookieStore(cookieStore.getCookies());

                result.setResponseText(EntityUtils.toString(entity, defaultCharset));

                //关闭流
                EntityUtils.consume(response.getEntity());
            }
            return result;
        }
    }

    /**
     * 请求的结果为InputStream
     *
     * @param request
     * @param defaultCharset
     * @param useSSL
     * @return
     * @throws IOException
     */
    private static Response toXHttpResponseInInputStream(HttpUriRequest request, String defaultCharset
            , CookieStore cookieStore, boolean useSSL) throws IOException {
        try (CloseableHttpClient httpClient = getHttpsClient(cookieStore)) {
            HttpResponse response = httpClient.execute(request);
            Response result = new Response(response.getStatusLine().getStatusCode(), response.getAllHeaders());
            HttpEntity entity = response.getEntity();
            if (entity != null) {

                //content encoding
                String charset = (null == entity.getContentEncoding()) ? defaultCharset : entity.getContentEncoding().getValue();
                result.setContentEncoding(charset);

                //content type
                String contentType = (null == entity.getContentType()) ? "" : entity.getContentType().getValue();
                result.setContentType(contentType);
                result.setCookieStore(cookieStore.getCookies());

                //复制 防止finally将流关闭
                InputStream inputStream = entity.getContent();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = inputStream.read(buffer)) > -1) {
                    baos.write(buffer, 0, len);
                }
                baos.flush();

                result.setInputStream(new ByteArrayInputStream(baos.toByteArray()));

                EntityUtils.consume(response.getEntity());
            }
            return result;
        }
    }

    private static CloseableHttpClient getHttpsClient(CookieStore cookieStore) {
        return HttpClients.custom()
                .setSSLSocketFactory(createSSLConnSocketFactory())
                .setConnectionManager(connMgr)
                .setConnectionManagerShared(true)
                .setDefaultRequestConfig(requestConfig)
                .setDefaultCookieStore(cookieStore)
                .build();
    }

    //create SSLConnectionSocketFactory to trust all
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        try {
            SSLContext sslcontext = SSLContexts.custom()
                    .loadTrustMaterial(null, (TrustStrategy) (chain, authType) -> true)
                    .build();
            return new SSLConnectionSocketFactory(sslcontext,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            e.printStackTrace();
        }
        return null;
    }
}
