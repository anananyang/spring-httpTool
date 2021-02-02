package httpClient.annoParser;


import httpClient.annotation.HttpReqConfig;
import httpClient.HttpRequestConfig;

import java.lang.annotation.Annotation;

public class HttpReqConfigAnnoParser implements HttpToolAnnoParser {

    @Override
    public void parse(Annotation annotation, HttpRequestConfig httpReqeustBuilder) {
        HttpReqConfig httpReqConfig = (HttpReqConfig) annotation;

        String httpMethod = httpReqConfig.httpMethod();
        String path = httpReqConfig.path();
        Integer connectTimeout = httpReqConfig.connectTimeout();
        Integer socketTimeout = httpReqConfig.socketTimeout();

        httpReqeustBuilder.setHttpMethod(httpMethod);
        httpReqeustBuilder.setPath(path);
        httpReqeustBuilder.setConnectTimeout(connectTimeout);
        httpReqeustBuilder.setSocketTimeout(socketTimeout);
    }
}
