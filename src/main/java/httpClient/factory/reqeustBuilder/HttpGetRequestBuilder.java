package httpClient.factory.reqeustBuilder;

import httpClient.HttpRequestConfig;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import spring.PropertiesResolver;

import java.net.URISyntaxException;
import java.util.List;

public class HttpGetRequestBuilder extends HttpRequestBuilder {

    public HttpGetRequestBuilder(HttpRequestConfig httpRequestConfig, PropertiesResolver propertiesResolver) {
        super(httpRequestConfig, propertiesResolver);
    }

    @Override
    public HttpRequestBase build() throws URISyntaxException{

        Header[] headers = this.getHeaders();
        List<NameValuePair> parameters = this.getParameters();
        RequestConfig config = this.getRequestConfig();
        URIBuilder uriBuilder = new URIBuilder(this.getUrl());
        if(parameters != null) {
            uriBuilder.setParameters(parameters);
        }

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        if(headers != null) {
            httpGet.setHeaders(headers);
        }
        if(config != null) {
            httpGet.setConfig(config);
        }

        return httpGet;
    }


}
