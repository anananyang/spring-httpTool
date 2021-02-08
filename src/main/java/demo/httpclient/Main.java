package demo.httpclient;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;

import javax.xml.ws.spi.http.HttpContext;
import java.net.URI;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CloseableHttpClient client = null;
        try {
            // 创建默认的 client
//            client = HttpClients.createDefault();
            client = HttpClients.custom()
                    .setRedirectStrategy(new LaxRedirectStrategy())  // 重定向处理
                    .build();
            MyHttpResponseHandler responseHandler = new MyHttpResponseHandler();
//            httpGet(client, responseHandler);
            httpPost(client, responseHandler);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                client.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private static void httpGet(CloseableHttpClient client, MyHttpResponseHandler responseHandler) {
//        HttpGet httpGet = new HttpGet("https://www.anyanggo.tk");
        HttpClientContext context = HttpClientContext.create();
        HttpGet httpGet = new HttpGet("http://anyang.ml");
        try {
            String str = (String)client.execute(httpGet, responseHandler, context);
            System.out.println(str);
            // 获取重定向路径
            HttpHost target = context.getTargetHost();
            List<URI> redirectLocations = context.getRedirectLocations();
            URI location = URIUtils.resolve(httpGet.getURI(), target, redirectLocations);
            System.out.println("Final HTTP location: " + location.toASCIIString());

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    private static void httpPost(CloseableHttpClient client, MyHttpResponseHandler responseHandler) {
        HttpPost httpPost = new HttpPost("http://47.99.184.154:6180/fauna/user/login");
        String jsonStr = "{\"mobilephone\":\"13611111111\",\"password\":\"123456\"}";
        try {
            // 自定义代理
            HttpHost httpHost = new HttpHost("101.205.120.102", 80, "http");
            RequestConfig customCofnig = RequestConfig.custom().setProxy(httpHost).build();
            httpPost.setConfig(customCofnig);
            StringEntity stringEntity = new StringEntity(jsonStr);
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Content-Type", "application/json");
            System.out.println("httpPost reqeust: " + httpPost.toString());
            Long startMillis = System.currentTimeMillis();
            String str = (String) client.execute(httpPost, responseHandler);
            Long endMillis = System.currentTimeMillis();

            System.out.println("total time: " + (endMillis - startMillis) + "ms");

            System.out.println(str);
        }catch (Exception e) {
            System.out.println(e);
        }
    }

}
