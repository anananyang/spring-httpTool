package demo;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Main {

    public static void main(String[] args) {
        CloseableHttpClient client = null;
        try {
            client = HttpClients.createDefault();
            MyHttpResponseHandler responseHandler = new MyHttpResponseHandler();
            httpGet(client, responseHandler);
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
        HttpGet httpGet = new HttpGet("https://www.anyanggo.tk");

        try {
            String str = (String)client.execute(httpGet, responseHandler);
            System.out.println(str);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    private static void httpPost(CloseableHttpClient client, MyHttpResponseHandler responseHandler) {
        HttpPost httpPost = new HttpPost("http://47.99.184.154:6180/fauna/user/login");
        String jsonStr = "{\"mobilephone\":\"13611111111\",\"password\":\"123456\"}";
        try {
            // 自定义代理
            HttpHost httpHost = new HttpHost("58.220.95.30", 10174, "http");
            RequestConfig customCofnig = RequestConfig.custom().setProxy(httpHost).build();
            httpPost.setConfig(customCofnig);
            StringEntity stringEntity = new StringEntity(jsonStr);
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Content-Type", "application/json");
            System.out.println("httpPost reqeust: " + httpPost.toString());
            String str = (String) client.execute(httpPost, responseHandler);
            System.out.println(str);
        }catch (Exception e) {
            System.out.println(e);
        }

    }

}
