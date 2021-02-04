package httpClient;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestConfig<T> {

    /**
     * http request method
     * only support Get、Post now
     */
    private String httpMethod;
    private String entityType;

    private String domain;
    private String path;

    private Object reqBody;

    private Integer connectTimeout;
    private Integer socketTimeout;

    private Map<String, String> headerMap;
    private Map<String, String> paramMap;
    private Map<String, String> pathVariableMap;


    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setReqBody(Object reqBody) {
        this.reqBody = reqBody;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public void addHeader(String name, String value) {
        if(headerMap == null) {
            headerMap = new HashMap<>();
        }
        headerMap.put(name, value);
    }

    public void addParam(String name, String value) {
        if(paramMap == null) {
            paramMap = new HashMap<>();
        }
        paramMap.put(name, value);
    }

    public void addPathVaribale(String name, String value) {
        if(pathVariableMap == null) {
            pathVariableMap = new HashMap<>();
        }
        pathVariableMap.put(name, value);
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getEntityType() {
        return entityType;
    }

    public String getDomain() {
        return domain;
    }

    public String getPath() {
        return path;
    }

    public Object getReqBody() {
        return reqBody;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public Map<String, String> getPathVariableMap() {
        return pathVariableMap;
    }

    public String getHeaderValue(String headerKey) {
        if(StringUtils.isBlank(headerKey)) {
            return null;
        }
        if(headerMap == null || headerMap.isEmpty()) {
            return null;
        }
        return headerMap.get(headerKey);
    }

}
