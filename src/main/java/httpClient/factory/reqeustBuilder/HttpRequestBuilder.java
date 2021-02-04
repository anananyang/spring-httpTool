package httpClient.factory.reqeustBuilder;

import httpClient.requestConfig.HttpRequestConfig;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.Asserts;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

public abstract class HttpRequestBuilder {

    protected HttpRequestConfig httpRequestConfig;

    public HttpRequestBuilder(HttpRequestConfig httpRequestConfig) {
        Asserts.notNull(httpRequestConfig, "httpRequestConfig");
        this.httpRequestConfig = httpRequestConfig;
    }

    public abstract HttpRequestBase build() throws URISyntaxException, UnsupportedEncodingException;

    protected String getUrl() {
        return getDomain() + getPath();
    }

    protected String getDomain() {
        return httpRequestConfig.getDomain();

    }

    protected String getPath() {
        return httpRequestConfig.getPath();
    }

    protected Header[] getHeaders() {
        return httpRequestConfig.getHeaders();
    }

    protected List<NameValuePair> getParameters() {
        return httpRequestConfig.getParameters();
    }


    protected RequestConfig getRequestConfig() {
        if (httpRequestConfig == null) {
            return null;
        }
        /**
         * add more request requestConfig
         */
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(httpRequestConfig.getConnectTimeout())
                .setSocketTimeout(httpRequestConfig.getSocketTimeout())
                .build();

        return requestConfig;
    }
}
