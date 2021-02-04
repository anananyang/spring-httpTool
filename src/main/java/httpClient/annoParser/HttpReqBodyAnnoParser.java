package httpClient.annoParser;

import httpClient.requestConfig.HttpRequestCustomerConfig;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;


public class HttpReqBodyAnnoParser implements HttpToolParamAnnoParser {

    @Override
    public void parse(Annotation annotation,
                      Parameter parameter,
                      Object arg,
                      HttpRequestCustomerConfig httpRequestConfig) {
        if(arg == null) {
            return;
        }

        httpRequestConfig.setReqBody(arg);
    }
}
