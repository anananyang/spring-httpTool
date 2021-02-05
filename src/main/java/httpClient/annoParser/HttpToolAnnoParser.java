package httpClient.annoParser;

import httpClient.request.HttpRequestCustomerConfig;

import java.lang.annotation.Annotation;

public interface HttpToolAnnoParser {
    void parse(Annotation annotation,
                HttpRequestCustomerConfig httpRequestConfig);
}
