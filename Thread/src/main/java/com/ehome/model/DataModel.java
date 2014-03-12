package com.ehome.model;
/**
 * @version V1.0
 * @Project: ci-webapi
 * @Title:
 * @Package com.ehome
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 14-3-12 上午10:21
 * @Copyright: 2014 ihome.com
 */
public class DataModel {

    private int id;
    private String name;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
