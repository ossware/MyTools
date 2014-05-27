package com.ehome.springmvc.dao;

import com.ehome.springmvc.model.Blog;
import org.apache.ibatis.annotations.*;

/**
 * Created by haoxiaolei on 2014/5/26 0022.
 */
public interface BlogDAO {


    /**
     * 新添加一个Blog
     * @param blog
     * @return
     */
    @Insert("insert into blog(title,authername,content) values(#{title}, #{authername}, #{content})")
    public boolean addBlog(Blog blog);


}
