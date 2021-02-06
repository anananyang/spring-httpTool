package httpClient.annoParser;

import httpClient.request.HttpRequestCustomConfig;

import java.lang.annotation.Annotation;

public interface HttpToolAnnoParser {
    void parse(Annotation annotation,
                HttpRequestCustomConfig httpRequestConfig);
}
