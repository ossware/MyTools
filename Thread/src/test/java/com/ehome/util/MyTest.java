package com.ehome.util;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package PACKAGE_NAME
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 14-2-26 下午5:28
 * @Copyright: 2014 ihome.com
 */
public class MyTest {

    @Test
    public void testModular() {
        System.out.println(20 % 8);
    }

    @Test
    public void pageing() {
        int count = 20000, pageSize = 200;
        for(int i = 0; i < 4; i++) {
            int start = i * pageSize;
            int end = start + pageSize;
            System.out.println("[" + start + "---" + end + "]");
        }
    }

    @Test
    public void batching() {
        int t = 0;
        for (int b = 0; b < 2; b++) {
            // 创建多个有返回值的任务
            for (long i = 0; i < 2; i++) {
                System.out.println(t);
                t++;
            }
        }
    }
}
