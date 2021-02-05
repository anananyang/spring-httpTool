package httpClient.annoParser;

import httpClient.request.HttpRequestCustomConfig;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;


public class HttpReqBodyAnnoParser implements HttpToolParamAnnoParser {

    @Override
    public void parse(Annotation annotation,
                      Parameter parameter,
                      Object arg,
                      HttpRequestCustomConfig httpRequestConfig) {
        if(arg == null) {
            return;
        }

        httpRequestConfig.setReqBody(arg);
    }
}
