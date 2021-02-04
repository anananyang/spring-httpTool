package httpClient.factory.entityBuilder;

import httpClient.HttpRequestConfig;
import org.apache.http.HttpEntity;

public class HttpFileEntityBuilder extends HttpEntityBuilder{

    public HttpFileEntityBuilder(HttpRequestConfig httpRequestConfig) {
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
