package httpClient.request;

import org.apache.http.Header;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

public interface HttpRequestConfig {

    String getHttpMethod();

    String getEntityType();

    String getDomain();

    String getPath();

    Object getReqBody();

    Integer getConnectTimeout();

    Integer getSocketTimeout();

    Map<String, String> getHeaderMap();

    Map<String, String> getParamMap();

    Map<String, String> getPathVariableMap();

    String getHeaderValue(String headerKey);

    Header[] getHeaders();

    List<NameValuePair> getParameters();
}
