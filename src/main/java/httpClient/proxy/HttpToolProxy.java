package httpClient.proxy;

import httpClient.responseHandle.HttpResoponseHandler;
import httpClient.factory.reqeustBuilder.HttpReqesutBuilderStaticFactory;
import httpClient.factory.reqeustBuilder.HttpRequestBuilder;
import httpClient.requestConfig.HttpRequestConfig;
import httpClient.requestConfig.HttpRequestConfigAdapter;
import httpClient.requestConfig.HttpRequestConfigParser;
import httpClient.requestConfig.HttpRequestCustomerConfig;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.Asserts;
import spring.PropertiesResolver;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class HttpToolProxy<T> implements InvocationHandler {

    private Class<T> httpToolInterface;
    // 默认的httpClient
    private CloseableHttpClient httpClient;
    // resolve properties
    private PropertiesResolver propertiesResolver;
    // reponse handle
    private HttpResoponseHandler resoponseHandler;


    public HttpToolProxy(Class<T> httpToolInterface,
                         CloseableHttpClient httpClient,
                         PropertiesResolver propertiesResolver,
                         HttpResoponseHandler resoponseHandler) {

        Asserts.notNull(httpToolInterface, "httpToolInterface");
        Asserts.notNull(httpClient, "httpClient");
        Asserts.notNull(propertiesResolver, "propertiesResolver");
        Asserts.notNull(resoponseHandler, "resoponseHandler");

        this.httpToolInterface = httpToolInterface;
        this.httpClient = httpClient;
        this.propertiesResolver = propertiesResolver;
        this.resoponseHandler = resoponseHandler;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        HttpRequestCustomerConfig customerConfig = HttpRequestConfigParser.parse(httpToolInterface, method, args);
        HttpRequestConfig httpRequestConfig = new HttpRequestConfigAdapter(customerConfig, propertiesResolver);
        HttpRequestBuilder requestBuilder = HttpReqesutBuilderStaticFactory.createHttpRequestBuilder(httpRequestConfig);
        HttpRequestBase httpRequest = requestBuilder.build();
        HttpClientContext context = HttpClientContext.create();
        HttpResponse httpResponse = null;
        try {
            Long startMillis = System.currentTimeMillis();
            httpResponse = httpClient.execute(httpRequest, context);
            Long endMillis = System.currentTimeMillis();

            System.out.println("total time: " + (endMillis - startMillis) + "ms");

            return resoponseHandler.handle(method, httpRequest, httpResponse);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }
}
