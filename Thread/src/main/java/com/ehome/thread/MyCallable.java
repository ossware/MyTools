package com.ehome.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package com.ehome.thread
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 14-2-26 下午3:28
 * @Copyright: 2014 ihome.com
 */
public class MyCallable {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        long total = 260000;
        int pageSize = 2;
        int taskSize = 4;
        long batchNum = countBatchNum(total, pageSize * taskSize);  // 要分多少批处理完数据
        System.out.println(batchNum);
        int t = 0;
        List<Future<List<Object>>> list = new ArrayList<Future<List<Object>>>();
        for (int b = 0; b < batchNum; b++) {
            // 创建一个线程池
            ExecutorService pool = Executors.newFixedThreadPool(taskSize);
            // 创建多个有返回值的任务
            for (long i = 0; i < taskSize; i++) {
//                Callable<List<Object>> c = new ProcessDataCallable("insert", 0, 0, 10000);    // 批量插入数据
                Callable<List<Object>> c = new ProcessDataCallable("query", (t * pageSize), pageSize, 0);     // 查询数据
                // 执行任务并获取Future对象
                Future<List<Object>> f = pool.submit(c);
                list.add(f);
                t++;
            }
            // 关闭线程池
            pool.shutdown();
        }
        System.out.println("----程序结束运行----，程序运行时间【" + (System.currentTimeMillis() - startTime) + "毫秒】");

        // 获取所有并发任务的运行结果
        for (Future<List<Object>> f : list) {
            // 从Future对象上获取任务的返回值，并输出到控制台
            Object obj = f.get();
            if (obj != null) {
                if (obj instanceof Boolean) {
                    System.out.println("返回的结果：" + obj);
                } else if (obj instanceof List) {
                    List<Object> subList = (List<Object>) obj;
                    for (Object s : subList) {
                        System.out.println("返回的结果：" + s);
                    }
                }
            }
        }
        System.out.println("list.size():::" + list.size());
    }

    /**
     * 计算数据页数
     * @param dataSum
     * @param pageSize
     * @return
     */
    private static Long countBatchNum(Long dataSum, int pageSize) {
        Long page = 0L;
        if ((dataSum % pageSize) > 0) {
            page = (dataSum / pageSize) + 1;
        } else if ((dataSum % pageSize) == 0) {
            page = (dataSum / pageSize);
        }

        return page;
    }
}
