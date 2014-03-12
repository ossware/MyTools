package com.ihome.concurrent;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.nio.conn.NHttpClientConnectionManager;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:  业务相关消息处理器
 * @Package com.ihome.concurrent
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 13-12-30 下午11:15
 * @Copyright: 2013 ihome.com
 */
public class MyMessageHandler {

    static AtomicLong sentCount = new AtomicLong(0);

    static NHttpClientConnectionManager connMgr;

    @Override
    public void consume(String e) {
        sendToTomcat(e);
    }

    private CloseableHttpAsyncClient httpclient = null;

    public MyMessageHandler(){

        try {
            connMgr = new PoolingNHttpClientConnectionManager(new DefaultConnectingIOReactor());
            httpclient = HttpAsyncClients.createMinimal(connMgr);
            httpclient.start();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 发给sc
     * @param message
     */
    private void sendToTomcat(String message){
        long startTime = System.currentTimeMillis();

        try {
            // http[GET]请求,
            final HttpGet request1 = new HttpGet("http://localhost");
            Future<HttpResponse> future = httpclient.execute(request1, null);

            // and wait until a response is received
            HttpResponse response1;
            response1 = future.get();
            System.out.println("message " + message + ":" + request1.getRequestLine() + "->" + response1.getStatusLine());
            System.out.println(message + " Sent; Cost:" + (System.currentTimeMillis() - startTime) + "; Succeed  Sent: " + sentCount.incrementAndGet());
        } catch (Exception e1) {
            System.err.println(e1.getMessage());
        } finally{
            // 关闭链接
            if(null!=httpclient){
                try {
                    //httpclient.close();
                } catch (Exception e1) {
                    //e1.printStackTrace();
                    System.err.println(e1.getMessage());
                }
            }
        }
    }
}
