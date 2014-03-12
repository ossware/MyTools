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
        long totalNum = 1;
        int pageSize = 8000;
        int threadNum = 4;
        long startTime = System.currentTimeMillis();
        processDataMultiThreading(totalNum, pageSize, threadNum);
        System.out.println("总耗时：" + (System.currentTimeMillis() - startTime));
    }

    /**
     * 批量处理数据
     * 注：把总数据分成成批线程，然后每一批线程又可以在被使用当中动态取舍(取舍：在不需要那么多线程的时候就会把剩下的线程停掉)
     * @param totalNum  数据总条数
     * @param pageSize  页数据量
     * @param threadNum 总共要开多少个线程
     */
    public static void processDataMultiThreading(long totalNum, int pageSize, int threadNum) {
        long batchNum = countPageNum(totalNum, pageSize * threadNum);
        long batchCounter = 0;
        for (int bn = 0; bn < batchNum; bn++) {
            for (int i = 0; i < threadNum; i++) {
                ProcessDataThread processDataThread = new ProcessDataThread();
                long start = batchCounter * pageSize;
                processDataThread.setStart(start);
                if (totalNum < pageSize) {     // 如果不够一页就只开启1个线程，步长等于剩下的数量
                    pageSize = (int) totalNum;
                }
                processDataThread.setPageSize(pageSize);

                Thread thread = new Thread(processDataThread);
                thread.start();
                batchCounter++;
                totalNum = totalNum - pageSize;
                if (totalNum < 0) {
                    totalNum = 0;
                    break;
                }
            }
        }
    }

    public void queryData() {
        long total = 61;
        int pageSize = 10;
        long pageCount = countPageNum(total, pageSize);
        for (long t = 1; t <= pageCount; t++) {
            for (int i = 0; i < 2; i++) {
                ProcessDataThread processDataThread = new ProcessDataThread();

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
    private static Long countPageNum(Long dataSum, int pageSize) {
        long page = 0L;
        if ((dataSum % pageSize) > 0) {
            page = (dataSum / pageSize) + 1;
        } else if ((dataSum % pageSize) == 0) {
            page = (dataSum / pageSize);
        }

        return page;
    }

}
