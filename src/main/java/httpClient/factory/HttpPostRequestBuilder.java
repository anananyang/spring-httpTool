package httpClient.factory;

import httpClient.HttpRequestConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

public class HttpPostRequestBuilder extends HttpRequestBuilder {

    public HttpPostRequestBuilder(HttpRequestConfig httpRequestConfig) {
        super(httpRequestConfig);
    }

    @Override
    public HttpRequestBase build() throws URISyntaxException, UnsupportedEncodingException{

        Header[] headers = this.getHeaders();
        RequestConfig config = this.getRequestConfig();
        URIBuilder uriBuilder = new URIBuilder(this.getUrl());
        HttpEntity httpEntity = this.getHttpEntity();

        HttpPost httpPost = new HttpPost(uriBuilder.build());
        if (headers != null) {
            httpPost.setHeaders(headers);
        }
        if (config != null) {
            httpPost.setConfig(config);
        }
        if (httpEntity != null) {
            httpPost.setEntity(httpEntity);
        }
        return httpPost;
    }

    /**
     * 默认用 stringEntity
     *
     * @return
     */
    private StringEntity getHttpEntity() throws UnsupportedEncodingException{
        String reqBody = httpRequestConfig.getReqBody();
        if (StringUtils.isBlank(reqBody)) {
            return null;
        }
        StringEntity stringEntity = new StringEntity(reqBody);
        return stringEntity;
    }
}
