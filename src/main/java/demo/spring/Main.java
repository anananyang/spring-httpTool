package demo.spring;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import com.alibaba.fastjson.JSONObject;
import demo.spring.rmi.HttpReqeustPostTest;
import demo.spring.rmi.HttpRequestGetTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger("demo.spring.Main");
//    private static final Logger logger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);


    public static void main(String[] args) {
        // set logger level
        ch.qos.logback.classic.Logger log = (ch.qos.logback.classic.Logger)logger;
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);

        // spring
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");

////
//        HttpRequestGetTest httpRequestRmiTest = (HttpRequestGetTest)applicationContext.getBean("httpRequestGetTest");
//        httpRequestRmiTest.getRemoteObj("index.html");
////
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
