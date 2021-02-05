package httpClient.annoParser;

import httpClient.annotation.HttpReqHeader;
import httpClient.request.HttpRequestCustomConfig;
import org.apache.commons.lang3.StringUtils;


import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.Map;

public class HttpReqHeaderAnnoParser implements HttpToolAnnoParser, HttpToolParamAnnoParser{

    @Override
    public void parse(Annotation annotation, HttpRequestCustomConfig httpRequestConfig) {
        HttpReqHeader httpReqHeader = (HttpReqHeader) annotation;

        String haederName = httpReqHeader.name();
        String headerValue = httpReqHeader.value();
        if(StringUtils.isBlank(haederName)) {
            return;
        }

        httpRequestConfig.addHeader(haederName, headerValue);
    }


    @Override
    public void parse(Annotation annotation,
                      Parameter parameter,
                      Object arg,
                      HttpRequestCustomConfig httpRequestConfig) {
        HttpReqHeader httpReqHeader = (HttpReqHeader) annotation;
        if(arg instanceof Map)  {
            httpRequestConfig.addHeader((Map) arg);
        } else {
            String haederName = httpReqHeader.name();
            String headerValue = arg == null ? null : arg.toString();
            httpRequestConfig.addHeader(haederName, headerValue);
        }
    }
}
