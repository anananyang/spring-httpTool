package httpClient.factory.entityBuilder;

import com.alibaba.fastjson.JSON;
import httpClient.constants.HttpContentType;
import httpClient.constants.HttpHeader;
import httpClient.request.HttpRequestConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

public class HttpStringEntityBuilder extends HttpEntityBuilder {

    public HttpStringEntityBuilder(HttpRequestConfig httpRequestConfig) {
        super(httpRequestConfig);
    }

    @Override
    public HttpEntity build() throws UnsupportedEncodingException{
        Object reqBody = httpRequestConfig.getReqBody();
        String content = this.getContent(reqBody);
        StringEntity stringEntity = new StringEntity(content);
        return stringEntity;
    }

    private String getContent(Object reqBody) {
        if (reqBody == null) {
            return null;
        } else if (reqBody instanceof String) {
            return (String) reqBody;
        }

        String contentType = httpRequestConfig.getHeaderValue(HttpHeader.CONTENT_TYPE);
        if(StringUtils.isBlank(contentType) || !isApplicationJson(contentType)) {
            return reqBody.toString();
        }

        return JSON.toJSONString(reqBody);
    }

    private Boolean isApplicationJson(String contentType) {
        return contentType.indexOf(HttpContentType.APPLICATION_JSON) >= 0;
    }
}
