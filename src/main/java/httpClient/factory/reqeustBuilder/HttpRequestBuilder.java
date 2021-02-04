package httpClient.factory.reqeustBuilder;

import httpClient.HttpRequestConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.Assert;
import spring.PropertiesResolver;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class HttpRequestBuilder {

    protected HttpRequestConfig httpRequestConfig;
    protected PropertiesResolver propertiesResolver;

    public HttpRequestBuilder(HttpRequestConfig httpRequestConfig, PropertiesResolver propertiesResolver) {
        Assert.notNull(httpRequestConfig);
        Assert.notNull(propertiesResolver);
        this.httpRequestConfig = httpRequestConfig;
        this.propertiesResolver = propertiesResolver;
    }

    public abstract HttpRequestBase build() throws URISyntaxException, UnsupportedEncodingException;

    protected String getUrl() {
        return getDomain() + getPath();
    }

    protected String getDomain() {
        String domain = httpRequestConfig.getDomain();
        return propertiesResolver.resolveStringValue(domain);
    }

    protected String getPath() {
        String path = httpRequestConfig.getPath();
        path = resolvePathVariable(path);
        return propertiesResolver.resolveStringValue(path);
    }

    protected Header[] getHeaders() {
        Map<String, String> headerMap = httpRequestConfig.getHeaderMap();
        if(headerMap == null || headerMap.isEmpty()) {
            return null;
        }
        Integer numOfHeadrs = headerMap.size();
        Header[] headers = new Header[numOfHeadrs];
        int index = 0;
        for(Map.Entry entry : headerMap.entrySet()) {
            String headerName = (String)entry.getKey();
            String headerValue = propertiesResolver.resolveStringValue((String)entry.getValue());
            headers[index] = new BasicHeader(headerName, headerValue);
        }

        return headers;
    }

    protected String getHeaderValue(String headerKey) {
        return httpRequestConfig.getHeaderValue(headerKey);
    }


    protected List<NameValuePair> getParameters() {
        Map<String, String> paramMap = httpRequestConfig.getParamMap();
        if(paramMap == null || paramMap.isEmpty()) {
            return null;
        }
        Integer numOfParams = paramMap.size();
        List<NameValuePair> list = new ArrayList<>(numOfParams);
        for(Map.Entry entry : paramMap.entrySet()) {
            String paramName = (String)entry.getKey();
            String paramValue = (String)entry.getValue();
            list.add(new BasicNameValuePair(paramName, paramValue));
        }

        return list;
    }


    protected RequestConfig getRequestConfig() {
        if(httpRequestConfig == null) {
            return null;
        }
        /**
         * add more request config
         */
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(httpRequestConfig.getConnectTimeout())
                .setSocketTimeout(httpRequestConfig.getSocketTimeout())
                .build();

        return requestConfig;
    }


    private String resolvePathVariable(String path) {
        if(StringUtils.isBlank(path)) {
            return path;
        }
        Map<String, String> pathVariabelMap = httpRequestConfig.getPathVariableMap();
        if(pathVariabelMap == null || pathVariabelMap.isEmpty()) {
            return path;
        }

        for(Map.Entry entry : pathVariabelMap.entrySet()) {
            String name = (String) entry.getKey();
            if(StringUtils.isBlank(name)) {
                continue;
            }
            String value = (String) entry.getValue();
            String match = "\\{" + name + "\\}";
            path = path.replaceAll(match, value);
        }

        return path;
    }

}
