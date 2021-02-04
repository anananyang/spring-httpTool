package demo.spring;

import com.alibaba.fastjson.JSONObject;
import demo.spring.rmi.HttpReqeustPostTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");


//        HttpRequestRmiTest httpRequestRmiTest = (HttpRequestRmiTest)applicationContext.getBean("httpRequestRmiTest");
//        httpRequestRmiTest.getRemoteObj("index.html");
//
//        System.out.println("\n\n\n");


        HttpReqeustPostTest httpRequestRmiTest2 = (HttpReqeustPostTest)applicationContext.getBean("httpReqeustRmiTest2");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobilephone", "13611111111");
        jsonObject.put("password", "123456");
        httpRequestRmiTest2.getRemoteObj(jsonObject);

    }
}
