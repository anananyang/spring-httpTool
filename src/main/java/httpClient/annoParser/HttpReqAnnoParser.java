package httpClient.annoParser;

import httpClient.annotation.HttpReq;
import httpClient.request.HttpRequestCustomConfig;

import java.lang.annotation.Annotation;

public class HttpReqAnnoParser implements HttpToolAnnoParser{

    @Override
    public void parse(Annotation annotation, HttpRequestCustomConfig httpRequestConfig) {
        HttpReq httpReq = (HttpReq)annotation;
        String domain = httpReq.domain();
        httpRequestConfig.setDomain(domain);
    }
}
