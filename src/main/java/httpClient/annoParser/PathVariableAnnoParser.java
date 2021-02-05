package httpClient.annoParser;

import httpClient.request.HttpRequestCustomerConfig;
import httpClient.annotation.PathVariable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

public class PathVariableAnnoParser implements HttpToolParamAnnoParser {

    @Override
    public void parse(Annotation annotation,
                      Parameter parameter,
                      Object arg,
                      HttpRequestCustomerConfig httpRequestConfig) {

        PathVariable pathVariable = (PathVariable) annotation;
        String name = pathVariable.name();
        if (arg == null) {
            throw new RuntimeException("path variable [ " + name + " ] is null");
        }
        String value = arg instanceof String ? (String) arg : arg.toString();

        httpRequestConfig.addPathVaribale(name, value);
    }
}
