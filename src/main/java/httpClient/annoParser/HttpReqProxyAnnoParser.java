package httpClient.annoParser;

import httpClient.annotation.HttpReqProxy;
import httpClient.request.HttpRequestCustomConfig;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;

public class HttpReqProxyAnnoParser implements HttpToolAnnoParser {

    @Override
    public void parse(Annotation annotation, HttpRequestCustomConfig httpRequestConfig) {
        HttpReqProxy httpReqProxy = (HttpReqProxy) annotation;
        String proxyHost = httpReqProxy.proxyHost();
        String proxyPort = httpReqProxy.proxyPort();
        if(StringUtils.isBlank(proxyHost) || StringUtils.isBlank(proxyPort)) {
            return;
        }
        httpRequestConfig.setUseHttpProxy(true);
        httpRequestConfig.setHttpProxyHost(proxyHost);
        httpRequestConfig.setHttpProxyPort(proxyPort);
    }
}
