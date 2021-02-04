package httpClient.factory.reqeustBuilder;


import httpClient.HttpRequestConfig;
import httpClient.constants.HttpMethod;
import org.apache.commons.lang3.StringUtils;
import spring.PropertiesResolver;

/**
 * 静态工厂
 */
public class HttpReqesutBuilderStaticFactory {

    public static HttpRequestBuilder createHttpRequestBuilder(HttpRequestConfig httpRequestConfig,
                                                              PropertiesResolver propertiesResolver) {
        String httpMethod = httpRequestConfig.getHttpMethod();
        if (StringUtils.isBlank(httpMethod)) {
            throw new RuntimeException("http method does not exist");
        }
        switch (httpMethod) {
            case HttpMethod.GET:
                return new HttpGetRequestBuilder(httpRequestConfig, propertiesResolver);

            case HttpMethod.POST:
                return new HttpPostRequestBuilder(httpRequestConfig, propertiesResolver);

            /**
             * PUT、DELETED and so on
             */

            default:
                throw new RuntimeException("http method is unknown");
        }
    }
}
