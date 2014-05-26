package com.ehome.springmvc.biz;

import com.ehome.springmvc.model.User;

import java.util.List;

/**
 * Created by haoxiaolei on 2014/5/23 0023.
 */
public interface UserBiz {
    public boolean addNewUser(User user);

    public List<User> getAllUsers();
}
