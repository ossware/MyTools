package com.ihome.concurrent.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import com.ihome.concurrent.MQEngine;
import com.ihome.concurrent.MyMessageHandler;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package com.ihome.concurrent.test
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 13-12-30 下午11:37
 * @Copyright: 2013 ihome.com
 */
public class TestMQ {
    public static void main(String[] args) {
        final AtomicLong l = new AtomicLong(0);
        //
        final MQEngine<String, MyMessageHandler> mq = new MQEngine<String, MyMessageHandler>(10, 50, MyMessageHandler.class);

        // 模拟客户并发数
        final int PRODUCER_SIZE = 200000;
        // 模拟每个客户平均请求次数
        final int REQUEST_TIME = 10;

        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < PRODUCER_SIZE; i++) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < REQUEST_TIME; i++) {
                        mq.push(String.valueOf(l.incrementAndGet()));
                    }
                }
            });
        }


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(mq.size());

    }
}
