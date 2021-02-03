package httpClient.proxy;

import httpClient.HttpRequestConfig;
import httpClient.factory.HttpReqesutBuilderStaticFactory;
import httpClient.factory.HttpRequestBuilder;
import httpClient.HttpRequestConfigParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class HttpToolProxy<T> implements InvocationHandler {

    private Class<T> httpToolInterface;
    // 默认的httpClient
    private CloseableHttpClient httpClient;

    public HttpToolProxy(Class<T> httpToolInterface, CloseableHttpClient httpClient) {
        this.httpToolInterface = httpToolInterface;
        this.httpClient = httpClient;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        HttpRequestConfig httpRequestConfig = HttpRequestConfigParser.parse(httpToolInterface, method, args);
        HttpRequestBuilder requestBuilder = HttpReqesutBuilderStaticFactory.createHttpRequestBuilder(httpRequestConfig);
        HttpRequestBase httpRequestBase = requestBuilder.build();
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpRequestBase);
            HttpEntity httpEntity = httpResponse.getEntity();
            String str = EntityUtils.toString(httpEntity, "UTF-8");
            System.out.println(str);
            return str;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
