package com.ehome.springmvc.model;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package com.ehome.springmvc.model
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 2014/5/22 0022 16:57
 * @Copyright: 2014 ihome.com
 */
public class Blog {
    private Long id;
    private String title;
    private String date;
    private String authername;
    private String content;

    /*************************************/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthername() {
        return authername;
    }

    public void setAuthername(String authername) {
        this.authername = authername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
