package httpClient.factory;


import httpClient.HttpRequestConfig;
import httpClient.constants.HttpMethod;
import org.apache.commons.lang3.StringUtils;

/**
 * 静态工厂
 */
public class HttpReqesutBuilderStaticFactory {

    public static HttpRequestBuilder createHttpRequestBuilder(HttpRequestConfig httpRequestConfig) {
        String httpMethod = httpRequestConfig.getHttpMethod();
        if (StringUtils.isBlank(httpMethod)) {
            throw new RuntimeException("http method does not exist");
        }
        switch (httpMethod) {
            case HttpMethod.GET:
                return new HttpGetRequestBuilder(httpRequestConfig);

            case HttpMethod.POST:
                return new HttpPostRequestBuilder(httpRequestConfig);

            default:
                throw new RuntimeException("http method is unknown");
        }
    }
}
