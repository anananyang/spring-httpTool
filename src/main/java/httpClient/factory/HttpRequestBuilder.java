package httpClient.factory;

import httpClient.HttpRequestConfig;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

public abstract class HttpRequestBuilder {

    protected HttpRequestConfig httpRequestConfig;

    public HttpRequestBuilder(HttpRequestConfig httpRequestConfig) {
        this.httpRequestConfig = httpRequestConfig;
    }

    public abstract HttpRequestBase build() throws URISyntaxException, UnsupportedEncodingException;

    protected String getUrl() {
        if (httpRequestConfig == null) {
            return null;
        }
        // TODO 简陋版，需要重构的
        return httpRequestConfig.getUrl() + httpRequestConfig.getPath();
    }

    protected Header[] getHeaders() {
        return httpRequestConfig == null ? null : httpRequestConfig.getHaders();
    }

    protected List<NameValuePair> getPaarameters() {
        return httpRequestConfig == null ? null : httpRequestConfig.getParameters();
    }


    protected RequestConfig getRequestConfig() {
        if(httpRequestConfig == null) {
            return null;
        }
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(httpRequestConfig.getConnectTimeout())
                .setSocketTimeout(httpRequestConfig.getSocketTimeout())
                .build();

        return requestConfig;
    }

}
