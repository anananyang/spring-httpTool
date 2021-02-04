package httpClient.proxy;

import httpClient.HttpRequestConfig;
import httpClient.HttpRequestConfigParser;
import spring.PropertiesResolver;
import httpClient.factory.reqeustBuilder.HttpReqesutBuilderStaticFactory;
import httpClient.factory.reqeustBuilder.HttpRequestBuilder;
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
    // resolve properties
    private PropertiesResolver propertiesResolver;


    public HttpToolProxy(Class<T> httpToolInterface,
                         CloseableHttpClient httpClient,
                         PropertiesResolver propertiesResolver) {
        this.httpToolInterface = httpToolInterface;
        this.httpClient = httpClient;
        this.propertiesResolver = propertiesResolver;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        HttpRequestConfig httpRequestConfig = HttpRequestConfigParser.parse(httpToolInterface, method, args);
        HttpRequestBuilder requestBuilder = HttpReqesutBuilderStaticFactory.createHttpRequestBuilder(httpRequestConfig, propertiesResolver);
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
        } finally {

        }
    }
}
