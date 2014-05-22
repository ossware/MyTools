package com.ehome.springmvc.dao;

import com.ehome.springmvc.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by haoxiaolei on 2014/5/22 0022.
 */
public interface UserDAO {

    /**
     * 获取所有User
     * @return
     */
    @Select("select * from User")
    public List<User> getAllUsers();

    /**
     * 根据id获取某个User
     * @param id
     * @return
     */
    @Select("select * from User where id=#{id}")    //注意这里只有一个参数，则#{}中的标识符可以任意取
    public User getUserById(long id);

    /**
     * 根据id和name获取User
     * @param id
     * @param userName
     * @return
     */
    @Select("select * from User where id=#{id} and userName like #{userName}")
    public User getUserByIdAndName(@Param("id")long id, @Param("userName")String userName);

    /**
     * 新添加一个User
     * @param user
     * @return
     */
    @Select("insert into user(userName, userAge, userAddress) values(#{userName}, #{userAge}, #{userAddress})")
    public boolean addNewUser(User user);

    /**
     * 删除User
     * @param id
     * @return
     */
    @Select("delete from user where id=#{id}")
    public boolean deleteUser(long id);

    /**
     * 更新User
     * @param user
     * @return
     */
    @Select("update user set userName=#{userName},userAddress=#{userAddress} where id=#{id}")
    public boolean updateUser(User user);

}
