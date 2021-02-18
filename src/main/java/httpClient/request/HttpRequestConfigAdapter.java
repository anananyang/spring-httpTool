package httpClient.request;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.StringValueResolver;
import spring.PropertiesResolver;
import util.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpRequestConfigAdapter implements HttpRequestConfig {

    public HttpRequestCustomConfig httpRequestConfig;
    protected PropertiesResolver propertiesResolver;

    private String url;
    private Header[] headers;
    private List<NameValuePair> params;

    public HttpRequestConfigAdapter(HttpRequestCustomConfig httpRequestConfig,
                                    PropertiesResolver propertiesResolver) {

        this.httpRequestConfig = httpRequestConfig;
        this.propertiesResolver = propertiesResolver;
    }


    public String getUrl() {
        if (StringUtils.isBlank(this.url)) {
            this.url = getDomain() + getPath();
        }

        return this.url;
    }

    public String getDomain() {
        String domain = httpRequestConfig.getDomain();
        return propertiesResolver.resolveStringValue(domain);
    }

    public String getPath() {
        String path = httpRequestConfig.getPath();
        path = resolvePathVariable(path);
        return propertiesResolver.resolveStringValue(path);
    }

    public Header[] getHeaders() {
        if(ArrayUtils.isNotEmpty(headers)) {
            return headers;
        }
        Map<String, String> headerMap = httpRequestConfig.getHeaderMap();
        if (headerMap == null || headerMap.isEmpty()) {
            return null;
        }
        Integer numOfHeadrs = headerMap.size();
        Header[] headers = new Header[numOfHeadrs];
        int index = 0;
        for (Map.Entry entry : headerMap.entrySet()) {
            String headerName = (String) entry.getKey();
            String headerValue = propertiesResolver.resolveStringValue((String) entry.getValue());
            headers[index] = new BasicHeader(headerName, headerValue);
            index++;
        }
        this.headers = headers;
        return this.headers;
    }

    public String getHeaderValue(String headerKey) {
        if (StringUtils.isBlank(headerKey)) {
            return null;
        }
        Map<String, String> headerMap = httpRequestConfig.getHeaderMap();
        if (headerMap == null || headerMap.isEmpty()) {
            return null;
        }
        String value = headerMap.get(headerKey);
        return propertiesResolver.resolveStringValue(value);
    }


    public List<NameValuePair> getParameters() {
        if (ListUtil.isNotEmpty(this.params)) {
            return this.params;
        }
        Map<String, String> paramMap = this.getParamMap();
        if (paramMap == null || paramMap.isEmpty()) {
            return null;
        }
        Integer numOfParams = paramMap.size();
        List<NameValuePair> list = new ArrayList<>(numOfParams);
        for (Map.Entry entry : paramMap.entrySet()) {
            String paramName = (String) entry.getKey();
            String paramValue = (String) entry.getValue();
            list.add(new BasicNameValuePair(paramName, paramValue));
        }

        this.params = list;
        return this.params;
    }


    public RequestConfig getRequestConfig() {
        if (httpRequestConfig == null) {
            return null;
        }
        /**
         * add more request request
         */
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(httpRequestConfig.getConnectTimeout())
                .setSocketTimeout(httpRequestConfig.getSocketTimeout())
                .build();

        return requestConfig;
    }


    private String resolvePathVariable(String path) {
        if (StringUtils.isBlank(path)) {
            return path;
        }
        Map<String, String> pathVariabelMap = httpRequestConfig.getPathVariableMap();
        if (pathVariabelMap == null || pathVariabelMap.isEmpty()) {
            return path;
        }

        for (Map.Entry entry : pathVariabelMap.entrySet()) {
            String name = (String) entry.getKey();
            if (StringUtils.isBlank(name)) {
                continue;
            }
            String value = (String) entry.getValue();
            String match = "\\{" + name + "\\}";
            path = path.replaceAll(match, value);
        }

        return path;
    }

    @Override
    public String getHttpMethod() {
        return httpRequestConfig.getHttpMethod();
    }

    @Override
    public String getEntityType() {
        return httpRequestConfig.getEntityType();
    }

    @Override
    public Object getReqBody() {
        return httpRequestConfig.getReqBody();
    }

    @Override
    public Integer getConnectTimeout() {
        return httpRequestConfig.getConnectTimeout();
    }

    @Override
    public Integer getSocketTimeout() {
        return httpRequestConfig.getSocketTimeout();
    }

    @Override
    public Map<String, String> getHeaderMap() {
        return httpRequestConfig.getHeaderMap();
    }

    @Override
    public Map<String, String> getParamMap() {
        return httpRequestConfig.getParamMap();
    }

    @Override
    public Map<String, String> getPathVariableMap() {
        return httpRequestConfig.getPathVariableMap();
    }


    @Override
    public HttpHost getHttpProxy() {
        if (!httpRequestConfig.getUseHttpProxy()) {
            return null;
        }
        String port = propertiesResolver.resolveStringValue(httpRequestConfig.getHttpProxyPort());
        String host = propertiesResolver.resolveStringValue(httpRequestConfig.getHttpProxyHost());
        if (StringUtils.isBlank(port) || StringUtils.isBlank(host)) {
            return null;
        }

        HttpHost httpHost = new HttpHost(host, Integer.valueOf(port), "http");
        return httpHost;
    }
}
