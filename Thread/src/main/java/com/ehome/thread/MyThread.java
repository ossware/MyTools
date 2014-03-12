package com.ehome.thread;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title: 线程调用
 * @Package com.ehome.thread
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 14-2-26 下午3:28
 * @Copyright: 2014 ihome.com
 */
public class MyThread {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
//        myThread.insertData();
        myThread.queryData();
    }

    public void queryData() {
        long total = 61;
        int pageSize = 10;
        long pageCount = countPageNum(total, pageSize);
        for (long t = 1; t <= pageCount; t++) {
            for (int i = 0; i < 2; i++) {
                ProcessDataThread processDataThread = new ProcessDataThread();
                processDataThread.setOperateType("query");

                int start = i * pageSize;
                processDataThread.setStart(start);
                processDataThread.setPageSize(pageSize);

                Thread thread = new Thread(processDataThread);
                thread.setName("线程--" + i);
                thread.start();
            }
        }
    }

    public static void insertData() {
        int processSize = 5000; // 一个线程处理多少个数据
        for (int i = 1; i <= 4; i++) {
            ProcessDataThread processDataThread = new ProcessDataThread();
            processDataThread.setOperateType("insert");
            processDataThread.setProcessSize(processSize);
            Thread thread = new Thread(processDataThread);
            thread.setName("线程 " + i);
            thread.start();
        }
    }

    /**
     * 计算数据页数
     * @param dataSum
     * @param pageSize
     * @return
     */
    private Long countPageNum(Long dataSum, int pageSize) {
        Long page = 0L;
        if ((dataSum % pageSize) > 0) {
            page = (dataSum / pageSize) + 1;
        } else if ((dataSum % pageSize) == 0) {
            page = (dataSum / pageSize);
        }

        return page;
    }

}
