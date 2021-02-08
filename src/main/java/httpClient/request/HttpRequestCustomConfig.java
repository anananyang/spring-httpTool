package httpClient.request;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestCustomConfig<T> {

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

    private Boolean useHttpProxy = false;
    private String httpProxyHost;
    private String httpProxyPort;


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

    public void setUseHttpProxy(Boolean useHttpProxy) {
        this.useHttpProxy = useHttpProxy;
    }

    public void setHttpProxyHost(String httpProxyHost) {
        this.httpProxyHost = httpProxyHost;
    }

    public void setHttpProxyPort(String httpProxyPort) {
        this.httpProxyPort = httpProxyPort;
    }

    public void addHeader(String name, String value) {
        if (headerMap == null) {
            headerMap = new HashMap<>();
        }
        headerMap.put(name, value);
    }

    public void addHeader(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        if (headerMap == null) {
            headerMap = new HashMap<>();
        }

        for (Map.Entry entry : map.entrySet()) {
            String key = (String) entry.getKey();
            Object value = (Object) entry.getValue();
            headerMap.put(key, value instanceof String ? (String) value : value.toString());
        }

    }

    public void addParam(String name, String value) {
        if (paramMap == null) {
            paramMap = new HashMap<>();
        }
        paramMap.put(name, value);
    }

    public void addParam(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        if (paramMap == null) {
            paramMap = new HashMap<>();
        }

        for (Map.Entry entry : map.entrySet()) {
            String key = (String) entry.getKey();
            Object value = (Object) entry.getValue();
            paramMap.put(key, value instanceof String ? (String) value : value.toString());
        }

    }



    public void addPathVaribale(String name, String value) {
        if (pathVariableMap == null) {
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

    public Boolean getUseHttpProxy() {
        return useHttpProxy;
    }

    public String getHttpProxyHost() {
        return httpProxyHost;
    }

    public String getHttpProxyPort() {
        return httpProxyPort;
    }
}
