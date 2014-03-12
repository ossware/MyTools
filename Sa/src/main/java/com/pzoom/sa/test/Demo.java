package com.pzoom.sa.test;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.List;
import com.sa.sdk.builder.SAApi;
import com.sa.sdk.builder.ServiceBuilder;
import com.sa.sdk.request.QueryRequest;
import com.sa.sdk.response.ProfileSearchResponse;
import com.sa.sdk.response.ProfileSearchResult;
import com.sa.sdk.response.SNSSearchResult;
/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package PACKAGE_NAME
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 14-3-6 上午11:51
 * @Copyright: 2014 ihome.com
 */
public class Demo {
    /**
     * App key and secret for accessing the API web service. It is generated specifically for you and should not be modified
     */
    private static final String appKey = "277d4a3b-98eb-4aec-a46d-276951ae1e15";
    private static final String appSecret = "f3mt/VJJilvHWNChp4bjdX+uPV5iFf1w";

    private SAApi api = new SAApi() {
        /**
         * Configure the URL for accessing the web service. It should not be modified
         */
        public String getAPIUrlAddress() {
            return "http://api.soanalytics.com/saapi";
        }
    };

    /**
     * Demonstration of searching a profile feeds
     */
    public void testSearchProfile() {
        ServiceBuilder builder = new ServiceBuilder(api, appKey, appSecret);
        try {
            QueryRequest request = (QueryRequest) builder.build(QueryRequest.class);
            ProfileSearchResponse result = request.getProfileSearchResponse("testing", "SINA", "数字营销", 1, 20, "pDate", "CN");

            System.out.println("Result Count: " + result.getResultCount());

            File file = new File("searchProfile.txt");
            FileWriter printer = new FileWriter(file);
            if (result.getResult() != null) {
                for (ProfileSearchResult searchResult : result.getResult()) {
                    printer.write(searchResult + "\n");
                }
            }
            printer.flush();
            printer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Demonstration of obtaining a feed information
     */
    public void testGetFeedId() {
        ServiceBuilder builder = new ServiceBuilder(api, appKey, appSecret);
        try {
            QueryRequest request = (QueryRequest) builder.build(QueryRequest.class);
            List<SNSSearchResult> result = request.getSNSFeed("05_0000_9223370666339675242_01805705");

            File file = new File("searchFeed.txt");
            FileWriter printer = new FileWriter(file);
            if (result != null) {
                for (SNSSearchResult searchResult : result) {
                    printer.write(searchResult + "\n");
                }
            }
            printer.flush();
            printer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method to execute this demonstration
     *
     * @param args
     */
    public static void main(String args[]) {
        Demo test = new Demo();

        Method[] methodList = test.getClass().getDeclaredMethods();

        try {
            for (Method method : methodList) {

                if (method.getName().equals("main"))
                    continue;
                System.out.println("Invoking method:" + method.getName());
                method.setAccessible(true);
                method.invoke(test);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
