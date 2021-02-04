package httpClient.factory.entityBuilder;

import httpClient.requestConfig.HttpRequestConfig;
import org.apache.http.HttpEntity;

public class HttpFileEntityBuilder extends HttpEntityBuilder{

    public HttpFileEntityBuilder(HttpRequestConfig httpRequestConfig) {
        super(httpRequestConfig);
    }

    @Override
    public HttpEntity build() {
       throw new RuntimeException("Not support FileEntity at the moment");
    }
}
