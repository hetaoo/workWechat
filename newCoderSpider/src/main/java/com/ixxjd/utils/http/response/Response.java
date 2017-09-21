package com.ixxjd.utils.http.response;

import org.apache.http.Header;
import org.apache.http.cookie.Cookie;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * mailto:xiaobenma020@gmail.com
 */
public class Response {

    private int statusCode;
    private String responseText;
    private String contentType;
    private String contentEncoding;
    private InputStream inputStream;
    private List<Cookie> cookieStore;

    private Map<String, Header> headersMap;

    public Response(int statusCode) {
        this(statusCode, null, null, null, null);
    }

    public Response(int statusCode, Header[] headers) {
        this(statusCode, null, null, null, headers);
    }


    public Response(int statusCode, String responseText, String contentType
            , String contentEncoding, Header[] headers) {
        this.statusCode = statusCode;
        this.responseText = getNotNullString(responseText);
        this.contentType = getNotNullString(contentType);
        this.contentEncoding = getNotNullString(contentEncoding);
        headersMap = new LinkedHashMap<>();
        addHeaders(headers);
    }

    private void addHeaders(Header[] headers) {
        if (null != headers) {
            for (Header header : headers) {
                headersMap.put(header.getName(), header);
            }
        }
    }

    private String getNotNullString(String str) {
        return null == str ? "" : str;
    }

    private Header[] getNotNullHeaders(Header[] headers) {
        return null == headers ? new Header[0] : headers;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = getNotNullString(responseText);
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = getNotNullString(contentType);
    }

    public String getContentEncoding() {
        return contentEncoding;
    }

    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = getNotNullString(contentEncoding);
    }

    public Header getHeader(String name) {
        return headersMap.get(name);
    }

    public Header[] getAllHeaders() {
        return headersMap.values().toArray(new Header[headersMap.size()]);
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List<Cookie> getCookieStore() {
        return cookieStore;
    }

    public void setCookieStore(List<Cookie> cookieStore) {
        this.cookieStore = cookieStore;
    }
}
