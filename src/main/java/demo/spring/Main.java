package demo.spring;

import com.alibaba.fastjson.JSONObject;
import demo.spring.rmi.HttpReqeustPostTest;
import demo.spring.rmi.HttpRequestGetTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");

//
//        HttpRequestGetTest httpRequestRmiTest = (HttpRequestGetTest)applicationContext.getBean("httpRequestGetTest");
//        httpRequestRmiTest.getRemoteObj("index.html");

//        System.out.println("\n\n\n");


        HttpReqeustPostTest httpRequestRmiTest2 = (HttpReqeustPostTest)applicationContext.getBean("httpReqeustPostTest");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobilephone", "13611111111");
        jsonObject.put("password", "123456");
        httpRequestRmiTest2.getRemoteObj(jsonObject);
    }
}
