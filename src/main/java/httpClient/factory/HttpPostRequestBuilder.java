package httpClient.factory;

import com.alibaba.fastjson.JSON;
import httpClient.HttpRequestConfig;
import httpClient.constants.HttpContentType;
import httpClient.constants.HttpHeader;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import spring.PropertiesResolver;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

public class HttpPostRequestBuilder extends HttpRequestBuilder {

    public HttpPostRequestBuilder(HttpRequestConfig httpRequestConfig, PropertiesResolver propertiesResolver) {
        super(httpRequestConfig, propertiesResolver);
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
        Object reqBody = httpRequestConfig.getReqBody();
        if (reqBody == null) {
            return null;
        }
        String bodyStr = reqBody instanceof String ? (String) reqBody : JSON.toJSONString(reqBody);
        StringEntity stringEntity = new StringEntity(bodyStr);
        return stringEntity;
    }
}
