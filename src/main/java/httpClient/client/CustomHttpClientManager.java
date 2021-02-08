package httpClient.client;

import httpClient.connection.MyPlainConnectionSocketFactory;
import httpClient.connection.MySSLConnectionSocketFactory;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import util.ListUtil;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class CustomHttpClientManager extends HttpClientManager {

    private HttpClientConfig httpClientConfig;
    private List<HttpRequestInterceptor> requestInterceptors;   // 可以用来做一些加密或者统计的工作
    private List<HttpResponseInterceptor> responseInterceptors;
    private RedirectStrategy redirectStrategy;
    private HttpClientConnectionManager clientConnectionManager;


    public void setHttpClientConfig(HttpClientConfig httpClientConfig) {
        this.httpClientConfig = httpClientConfig;
    }

    public void setRequestInterceptors(List<HttpRequestInterceptor> requestInterceptors) {
        this.requestInterceptors = requestInterceptors;
    }

    public void setResponseInterceptors(List<HttpResponseInterceptor> responseInterceptors) {
        this.responseInterceptors = responseInterceptors;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    @Override
    public void init() throws Throwable {

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setRetryHandler(this.createDefaultRetryHandler());
        httpClientBuilder.setDefaultRequestConfig(this.createDefaultReqeustConfig());
        httpClientBuilder.setRedirectStrategy(this.redirectStrategy);
        httpClientBuilder.setConnectionManager(this.createConnectionManager());
        this.addAllReqeustInterceptor(httpClientBuilder);
        this.addAllResponseInterceptor(httpClientBuilder);

        CloseableHttpClient httpClient = httpClientBuilder.build();

        this.httpClient = httpClient;
    }

    private RequestConfig createDefaultReqeustConfig() {
        if (httpClientConfig == null) {
            return null;
        }
        return RequestConfig.custom()
                .setSocketTimeout(httpClientConfig.getTimeout())
                .setConnectTimeout(httpClientConfig.getConnectionTimeout())
                .build();
    }

    private HttpRequestRetryHandler createDefaultRetryHandler() {
        return new DefaultHttpReqeustRetryHandler(httpClientConfig.getRetryCount());
    }

    private void addAllReqeustInterceptor(HttpClientBuilder httpClientBuilder) {
        if (ListUtil.isEmpty(requestInterceptors)) {
            return;
        }

        for (HttpRequestInterceptor requestInterceptor : requestInterceptors) {
            httpClientBuilder.addInterceptorLast(requestInterceptor);
        }
    }

    private void addAllResponseInterceptor(HttpClientBuilder httpClientBuilder) {
        if (ListUtil.isEmpty(responseInterceptors)) {
            return;
        }
        for (HttpResponseInterceptor responseInterceptor : responseInterceptors) {
            httpClientBuilder.addInterceptorLast(responseInterceptor);
        }
    }

    private HttpClientConnectionManager createConnectionManager() throws NoSuchAlgorithmException {
        if (this.clientConnectionManager != null) {
            return clientConnectionManager;
        }
        // custom socket factory
        // socks proxy
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new MyPlainConnectionSocketFactory())
                .register("https", new MySSLConnectionSocketFactory())
                .build();

        PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager(registry);
        clientConnectionManager.setDefaultMaxPerRoute(httpClientConfig.getDefaultMaxPerRoute());
        clientConnectionManager.setMaxTotal(httpClientConfig.getTotalMaxConnetions());

        return clientConnectionManager;
    }

}
