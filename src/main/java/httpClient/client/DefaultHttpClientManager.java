package httpClient.client;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class DefaultHttpClientManager extends HttpClientManager {


    @Override
    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * spring bean init-method
     */
    @Override
    public void init() {
        this.httpClient = HttpClients.createDefault();
    }


}
