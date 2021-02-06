package httpClient.annoParser;


import httpClient.annotation.HttpReqConfig;
import httpClient.request.HttpRequestCustomConfig;

import java.lang.annotation.Annotation;

public class HttpReqConfigAnnoParser implements HttpToolAnnoParser {

    @Override
    public void parse(Annotation annotation, HttpRequestCustomConfig httpRequestConfig) {
        HttpReqConfig httpReqConfig = (HttpReqConfig) annotation;

        String httpMethod = httpReqConfig.httpMethod();
        String entityType = httpReqConfig.entityType();
        String path = httpReqConfig.path();
        Integer connectTimeout = httpReqConfig.connectTimeout();
        Integer socketTimeout = httpReqConfig.socketTimeout();

        httpRequestConfig.setHttpMethod(httpMethod);
        httpRequestConfig.setEntityType(entityType);   // 目前支持 StringEntity
        httpRequestConfig.setPath(path);
        httpRequestConfig.setConnectTimeout(connectTimeout);
        httpRequestConfig.setSocketTimeout(socketTimeout);
    }
}
