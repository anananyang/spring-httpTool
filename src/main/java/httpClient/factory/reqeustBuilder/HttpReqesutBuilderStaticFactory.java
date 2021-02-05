package httpClient.factory.reqeustBuilder;


import httpClient.constants.HttpMethod;
import httpClient.request.HttpRequestConfig;
import httpClient.request.HttpRequestConfigAdapter;
import httpClient.request.HttpRequestCustomConfig;
import org.apache.commons.lang3.StringUtils;
import spring.PropertiesResolver;

/**
 * 静态工厂
 */
public class HttpReqesutBuilderStaticFactory {

    public static HttpRequestBuilder createHttpRequestBuilder(HttpRequestCustomConfig httpRequestCustomerConfig,
                                                              PropertiesResolver propertiesResolver) {

        HttpRequestConfig httpRequestConfig = new HttpRequestConfigAdapter(httpRequestCustomerConfig, propertiesResolver);
        return createHttpRequestBuilder(httpRequestConfig);
    }


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

            /**
             * PUT、DELETED and so on
             */

            default:
                throw new RuntimeException("http method is unknown");
        }
    }

}
