package httpClient.client;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class CustomHttpClientManager extends HttpClientManager {

    private HttpClientConfig httpClientConfig;

    public void setHttpClientConfig(HttpClientConfig httpClientConfig) {
        this.httpClientConfig = httpClientConfig;
    }

    @Override
    public void init() {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(this.createDefaultReqeustConfig())
                .build();

        // retry

        // credentialsProvider

        // globe proxy

        // connect pool manager


        // globe request and response interceptor


        this.httpClient = httpClient;
    }

    private RequestConfig createDefaultReqeustConfig() {
        if(httpClientConfig == null) {
            return null;
        }
        return RequestConfig.custom()
                .setSocketTimeout(httpClientConfig.getTimeout())
                .setConnectTimeout(httpClientConfig.getConnectionTimeout())
                .build();
    }

}
