package httpClient.annoParser;

import httpClient.annotation.HttpReqParam;
import httpClient.request.HttpRequestCustomConfig;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.Map;

public class HttpReqParamAnnoParser implements HttpToolParamAnnoParser {

    @Override
    public void parse(Annotation annotation,
                      Parameter parameter,
                      Object arg,
                      HttpRequestCustomConfig httpRequestConfig) {
        HttpReqParam httpReqParam = (HttpReqParam) annotation;
        if (arg instanceof Map) {
            httpRequestConfig.addParam((Map) arg);
        } else {
            String paramName = httpReqParam.value();
            String paramValue = arg == null ? null : arg.toString();
            httpRequestConfig.addParam(paramName, paramValue);
        }
    }
}
