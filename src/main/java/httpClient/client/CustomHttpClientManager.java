package httpClient.client;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class CustomHttpClientManager extends HttpClientManager {

    private Integer timeout = 20 * 1000;
    private Integer connectionTimeout = 10 * 1000;
    private Integer retryCount = 1;


    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    @Override
    public void init() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout)
                .setConnectTimeout(connectionTimeout)
                .build();

        // retry


        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();


        this.httpClient = httpClient;
    }
}
