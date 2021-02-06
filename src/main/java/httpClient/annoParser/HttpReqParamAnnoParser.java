package httpClient.annoParser;

import httpClient.annotation.HttpReqHeader;
import httpClient.request.HttpRequestCustomConfig;
import httpClient.annotation.HttpReqParam;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.Map;

public class HttpReqParamAnnoParser implements HttpToolParamAnnoParser {

    /**
     * annotation 可能不存在
     *
     * @param annotation
     * @param parameter
     * @param arg
     * @param httpRequestConfig
     */
    @Override
    public void parse(Annotation annotation,
                      Parameter parameter,
                      Object arg,
                      HttpRequestCustomConfig httpRequestConfig) {
        HttpReqParam httpReqParam = (HttpReqParam) annotation;
        HttpReqHeader httpReqHeader = (HttpReqHeader) annotation;
        if (arg instanceof Map) {
            httpRequestConfig.addParam((Map) arg);
        } else {
            String paramName = httpReqParam != null ? httpReqParam.value() : null;
            if (StringUtils.isBlank(paramName)) {
                // TODO 日志打印 paramKey is empty
                return;
            }
            String paramValue = arg == null ? null : arg.toString();
            httpRequestConfig.addParam(paramName, paramValue);
        }


    }
}
