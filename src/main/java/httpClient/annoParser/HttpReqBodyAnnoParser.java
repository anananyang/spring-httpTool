package httpClient.annoParser;

import com.alibaba.fastjson.JSON;
import httpClient.HttpRequestConfig;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;


public class HttpReqBodyAnnoParser implements HttpToolParamAnnoParser {

    @Override
    public void parse(Annotation annotation,
                      Parameter parameter,
                      Object arg,
                      HttpRequestConfig httpReqeustBuilder) {
        if(arg == null) {
            return;
        }
        String json = JSON.toJSONString(arg);

        httpReqeustBuilder.setReqBody(json);
    }
}
