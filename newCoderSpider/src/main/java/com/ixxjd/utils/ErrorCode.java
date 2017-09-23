package com.ixxjd.utils;

/**
 * Created by hetaoo on 2017/9/23.
 * 错误编码
 */
public enum  ErrorCode {
    COOKIES_NULL("00001","cookies为null");

    private String code;

    private String desc;

    ErrorCode(String code,String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
