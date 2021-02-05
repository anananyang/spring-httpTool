package httpClient.annoParser;

import httpClient.request.HttpRequestCustomerConfig;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

public interface HttpToolParamAnnoParser {

    void parse(Annotation annotation,
               Parameter parameter,
               Object arg,
               HttpRequestCustomerConfig httpRequestConfig);
}
