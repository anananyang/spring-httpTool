package httpClient.requestConfig;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import spring.PropertiesResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpRequestConfigAdapter implements HttpRequestConfig {

    public HttpRequestCustomerConfig httpRequestConfig;
    protected PropertiesResolver propertiesResolver;

    public HttpRequestConfigAdapter(HttpRequestCustomerConfig httpRequestConfig,
                                    PropertiesResolver propertiesResolver) {

        this.httpRequestConfig = httpRequestConfig;
        this.propertiesResolver = propertiesResolver;
    }


    public String getUrl() {
        return getDomain() + getPath();
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

        return headers;
    }

    public String getHeaderValue(String headerKey) {
        if (StringUtils.isBlank(headerKey)) {
            return null;
        }
        Map<String, String> headerMap = httpRequestConfig.getHeaderMap();
        if (headerMap == null || headerMap.isEmpty()) {
            return null;
        }
        return headerMap.get(headerKey);
    }


    public List<NameValuePair> getParameters() {
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

        return list;
    }


    public RequestConfig getRequestConfig() {
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
}
