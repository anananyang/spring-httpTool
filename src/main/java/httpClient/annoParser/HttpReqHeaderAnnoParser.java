package httpClient.annoParser;

import httpClient.annotation.HttpReqHeader;
import httpClient.requestConfig.HttpRequestCustomerConfig;
import org.apache.commons.lang3.StringUtils;


import java.lang.annotation.Annotation;

public class HttpReqHeaderAnnoParser implements HttpToolAnnoParser{

    @Override
    public void parse(Annotation annotation, HttpRequestCustomerConfig httpRequestConfig) {
        HttpReqHeader httpReqHeader = (HttpReqHeader) annotation;

        String haederName = httpReqHeader.name();
        String headerValue = httpReqHeader.value();
        if(StringUtils.isBlank(haederName)) {
            return;
        }

        httpRequestConfig.addHeader(haederName, headerValue);
    }
}
