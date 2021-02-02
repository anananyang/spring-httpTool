package httpClient.annoParser;

import httpClient.HttpRequestConfig;

import java.lang.annotation.Annotation;

public interface HttpToolAnnoParser {
    void parse(Annotation annotation,
                HttpRequestConfig httpReqeustBuilder);
}
