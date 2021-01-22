package demo.spring;

import demo.spring.rmi.HttpRequestRmiTest;
import demo.spring.rmi.HttpRequestRmiTestt;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        String[] name = applicationContext.getBeanDefinitionNames();
        HttpRequestRmiTest httpRequestRmiTest = (HttpRequestRmiTest)applicationContext.getBean("httpRequestRmiTest");
        httpRequestRmiTest.getRemoteObj();
        HttpRequestRmiTestt httpRequestRmiTestt = (HttpRequestRmiTestt)applicationContext.getBean("httpRequestRmiTestt");
        httpRequestRmiTestt.getRemoteObj2();
    }
}
