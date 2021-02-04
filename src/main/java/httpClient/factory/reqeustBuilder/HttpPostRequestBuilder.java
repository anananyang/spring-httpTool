package httpClient.factory.reqeustBuilder;

import httpClient.HttpRequestConfig;
import httpClient.factory.entityBuilder.HttpEntityStaticFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import spring.PropertiesResolver;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

public class HttpPostRequestBuilder extends HttpRequestBuilder {

    public HttpPostRequestBuilder(HttpRequestConfig httpRequestConfig, PropertiesResolver propertiesResolver) {
        super(httpRequestConfig, propertiesResolver);
    }

    @Override
    public HttpRequestBase build() throws URISyntaxException, UnsupportedEncodingException{

        Header[] headers = this.getHeaders();
        List<NameValuePair> parameters = this.getParameters();
        RequestConfig config = this.getRequestConfig();
        HttpEntity httpEntity = this.getHttpEntity();
        URIBuilder uriBuilder = new URIBuilder(this.getUrl());
        if(parameters != null) {
            uriBuilder.setParameters(parameters);
        }

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
    private HttpEntity getHttpEntity() throws UnsupportedEncodingException{
        return HttpEntityStaticFactory.createHttpEntityBuilder(this.httpRequestConfig).build();
    }
}
