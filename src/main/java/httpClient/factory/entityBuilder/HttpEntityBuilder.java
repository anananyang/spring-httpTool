package httpClient.factory.entityBuilder;

import httpClient.requestConfig.HttpRequestConfig;
import org.apache.http.HttpEntity;

import java.io.UnsupportedEncodingException;

public abstract class HttpEntityBuilder {

    public HttpRequestConfig httpRequestConfig;

    public HttpEntityBuilder(HttpRequestConfig httpRequestConfig) {
        this.httpRequestConfig = httpRequestConfig;
    }

    public abstract HttpEntity build() throws UnsupportedEncodingException;
}
