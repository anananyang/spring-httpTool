package demo.spring;

import com.alibaba.fastjson.JSONObject;
import demo.spring.rmi.HttpReqeustRmiTest2;
import demo.spring.rmi.HttpRequestRmiTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        String[] name = applicationContext.getBeanDefinitionNames();
        HttpRequestRmiTest httpRequestRmiTest = (HttpRequestRmiTest)applicationContext.getBean("httpRequestRmiTest");
        httpRequestRmiTest.getRemoteObj(5);

        System.out.println("\n\n\n");


        HttpReqeustRmiTest2 httpRequestRmiTest2 = (HttpReqeustRmiTest2)applicationContext.getBean("httpReqeustRmiTest2");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobilephone", "13611111111");
        jsonObject.put("password", "123456");
//        httpRequestRmiTest2.getRemoteObj("{\"mobilephone\":\"13611111111\",\"password\":\"123456\"}");
        httpRequestRmiTest2.getRemoteObj(jsonObject);

    }
}
