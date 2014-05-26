package com.ehome.springmvc.dao.impl;

import com.ehome.springmvc.dao.BaseDAO;
import com.ehome.springmvc.dao.BlogDAO;
import com.ehome.springmvc.mapper.BlogMapper;
import com.ehome.springmvc.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package com.ehome.springmvc.dao.impl
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 2014/5/26 0026 18:22
 * @Copyright: 2014 ihome.com
 */
public class BlogDAOImpl extends BaseDAO implements BlogDAO {
    @Autowired
    private BlogMapper blogMapper;


    public boolean addBlog(Blog blog) {
        return false;
    }

    public Blog getBlog() {
        blogMapper.getBlog
        return null;
    }
}
