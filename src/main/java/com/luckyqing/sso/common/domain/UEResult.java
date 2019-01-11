package com.luckyqing.sso.common.domain;

import java.io.Serializable;

/**
 * @program: notice
 * @description:
 * @author: "清歌"
 * @create: 2018-08-16 10:04
 **/
public class UEResult implements Serializable {

    private String title;    //这里需要设置文件名称如
    private String original;    //这里需要设置文件名称如
    private String state;    //上传状态 SUCCESS必须大写
    private String url; //图片的url
    private String code;

    @Override
    public String toString() {
        return "UEResult{" +
                "title='" + title + '\'' +
                ", original='" + original + '\'' +
                ", state='" + state + '\'' +
                ", url='" + url + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
