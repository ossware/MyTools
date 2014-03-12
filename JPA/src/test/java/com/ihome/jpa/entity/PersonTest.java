package com.ihome.jpa.entity;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package PACKAGE_NAME
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 14-2-21 下午3:53
 * @Copyright: 2014 ihome.com
 */
public class PersonTest {

    private Person person = null;

    @Before
    public void setUp() throws Exception {
        person = new Person();
        person.setName("单车上的理想");
    }

    @Test
    public void save() {
        // 1、得到工厂，此时就会创建表
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ihome-jpa"); // --->sessionFactory-->session

        EntityManager manager = factory.createEntityManager();
        EntityTransaction ts = manager.getTransaction();
        ts.begin(); // 开始事务

        manager.persist(person);    // 持久化对象

        manager.close();
        factory.close();

    }
}
