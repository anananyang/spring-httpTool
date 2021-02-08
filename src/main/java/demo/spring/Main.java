package demo.spring;

import com.alibaba.fastjson.JSONObject;
import demo.spring.rmi.HttpReqeustPostTest;
import demo.spring.rmi.HttpRequestGetTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");

//
//        HttpRequestGetTest httpRequestRmiTest = (HttpRequestGetTest)applicationContext.getBean("httpRequestGetTest");
//        httpRequestRmiTest.getRemoteObj("index.html");
//
//        System.out.println("\n\n\n");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobilephone", "13611111111");
        jsonObject.put("password", "123456");

        Map<String, String> header = new HashMap() {{
            put("name", "anyang");
//            put("appName", "anyang");
        }};
        HttpReqeustPostTest httpRequestRmiTest2 = (HttpReqeustPostTest)applicationContext.getBean("httpReqeustPostTest");

        httpRequestRmiTest2.getRemoteObj(jsonObject, header);

//        for(int i = 0; i < 10; i++) {
//            Runnable runnable = new Runnable() {
//                @Override
//                public void run() {
//                    httpRequestRmiTest2.getRemoteObj(jsonObject, header);
//                }
//            };
//            runnable.run();
//        }



    }
}
