package com.ehome.springmvc.model;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package com.ehome.springmvc.model
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 2014/5/22 0022 16:55
 * @Copyright: 2014 ihome.com
 */
public class User {
    private Long id;
    private String userName;
    private Integer userAge;
    private String userAddress;

    /***************************************/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
