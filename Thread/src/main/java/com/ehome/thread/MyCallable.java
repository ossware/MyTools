package com.ehome.thread;

import java.util.ArrayList;
import java.util.Collection;
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
        List dataList = queryData();
        for (Object s : dataList) {
            System.out.println("返回的结果：id=" + s);
        }
    }

    private static List queryData() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        long total = 30;
        int eachThreadProcessCnt = 10;  // 每个线程处理的数据量
        long threadCnt = 2;              // 总共有几个线程
        Long batchCnt = countBatchNum(total, threadCnt * eachThreadProcessCnt); // 总共的数据量需要用几批线程来处理

        List<Future<List<Object>>> list = new ArrayList<Future<List<Object>>>();

        int t = 0;
        batchCnt = countBatchNum(total, (long) eachThreadProcessCnt);  // 总数据量需要用多少个线程
        for (long b = 1; b <= batchCnt; b++) {
            ExecutorService pool = Executors.newFixedThreadPool((int) threadCnt);
            // 创建多个有返回值的任务
            for (long i = 1; i <= threadCnt; i++) {
                if (b == batchCnt && i == threadCnt) eachThreadProcessCnt = 0;
                Callable<List<Object>> c = new ProcessDataCallable("query", (t * eachThreadProcessCnt), eachThreadProcessCnt, 0);     // 查询数据
                // 执行任务并获取Future对象
                Future<List<Object>> f = pool.submit(c);
                list.add(f);
                t++;
            }
            // 关闭线程池
            pool.shutdown();
        }










//        for (long b = 1; b <= batchCnt; b++) {
//            ExecutorService pool = Executors.newFixedThreadPool(threadCnt);
//            // 创建多个有返回值的任务
//            for (long i = 1; i <= threadCnt; i++) {
//                Callable<List<Object>> c = new ProcessDataCallable("query", (t * eachThreadProcessCnt), eachThreadProcessCnt, 0);     // 查询数据
//                // 执行任务并获取Future对象
//                Future<List<Object>> f = pool.submit(c);
//                list.add(f);
//                t++;
//            }
//            // 关闭线程池
//            pool.shutdown();
//        }




        System.out.println("----程序结束运行----，程序运行时间【" + (System.currentTimeMillis() - startTime) + "毫秒】");

        /********* 把获取到的数据组装并返回出去 *********/
        List returnList = new ArrayList();
        // 获取所有并发任务的运行结果
        for (Future<List<Object>> f : list) {
            // 从Future对象上获取任务的返回值
            Object obj = f.get();
            if (obj != null) {
                returnList.addAll((Collection) obj);
            }
        }

        return returnList;
    }

    /**
     * 计算数据页数（批数量）
     *
     * @param dataSum
     * @param pageSize
     *
     * @return
     */
    private static Long countBatchNum(Long dataSum, Long pageSize) {
        Long page = 0L;
        if ((dataSum % pageSize) > 0) {
            page = (dataSum / pageSize) + 1;
        } else if ((dataSum % pageSize) == 0) {
            page = (dataSum / pageSize);
        }

        return page;
    }
}
