package httpClient;

import httpClient.constants.HttpMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequestConfig<T> {

    /**
     * http request method
     * only support Get、Post now
     */
    private String httpMethod;

    private String url;
    private String path;

    private String reqBody;

    private Integer connectTimeout;
    private Integer socketTimeout;

    private Map<String, String> headerMap;
    private Map<String, String> paramMap;


    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setReqBody(String reqBody) {
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


    public String getHttpMethod() {
        return httpMethod;
    }

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }

    public String getReqBody() {
        return reqBody;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public Header[] getHaders() {
        if(headerMap == null || headerMap.isEmpty()) {
            return null;
        }
        Integer numOfHeadr = headerMap.size();
        Header[] headers = new Header[numOfHeadr];
        int index = 0;
        for(Map.Entry entry : headerMap.entrySet()) {
            String headerName = (String)entry.getKey();
            String headerValue = (String)entry.getValue();
            headers[index] = new BasicHeader(headerName, headerValue);
        }

        return headers;
    }

    public List<NameValuePair> getParameters() {
        if(paramMap == null || paramMap.isEmpty()) {
            return null;
        }
        Integer numOfParams = paramMap.size();
        List<NameValuePair> list = new ArrayList<>(numOfParams);
        for(Map.Entry entry : headerMap.entrySet()) {
            String paramName = (String)entry.getKey();
            String paramValue = (String)entry.getValue();
            list.add(new BasicNameValuePair(paramName, paramValue));
        }

        return list;
    }


}
