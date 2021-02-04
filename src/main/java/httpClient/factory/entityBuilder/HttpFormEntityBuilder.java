package httpClient.factory.entityBuilder;

import httpClient.HttpRequestConfig;
import org.apache.http.HttpEntity;

public class HttpFormEntityBuilder extends HttpEntityBuilder {

    public HttpFormEntityBuilder(HttpRequestConfig httpRequestConfig) {
        super(httpRequestConfig);
    }

    /**
     * TODO
     *
     * @return
     */
    @Override
    public HttpEntity build() {
        return null;
    }
}
