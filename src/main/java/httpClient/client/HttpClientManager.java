package httpClient.client;

import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

public abstract class HttpClientManager {


    protected CloseableHttpClient httpClient;

    /**
     * depend on spring bean init-method
     */
    public abstract void init() throws Throwable;

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public void destroy() throws IOException {
        if (httpClient == null) {
            return;
        }
        httpClient.close();
    }

}
