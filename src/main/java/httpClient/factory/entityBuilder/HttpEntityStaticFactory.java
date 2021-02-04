package httpClient.factory.entityBuilder;

import httpClient.HttpRequestConfig;
import httpClient.constants.HttpEntityType;
import org.apache.commons.lang3.StringUtils;

public class HttpEntityStaticFactory {

    public static HttpEntityBuilder createHttpEntityBuilder(HttpRequestConfig httpRequestConfig) {
        String entityType = httpRequestConfig.getEntityType();
        if (StringUtils.isBlank(entityType)) {
            throw new RuntimeException("entity type is unknown");
        }
        switch (entityType) {
            case HttpEntityType.STRING:
                return new HttpStringEntityBuilder(httpRequestConfig);

            case HttpEntityType.FILE:
                return new HttpFileEntityBuilder(httpRequestConfig);

            case HttpEntityType.FORM:
                return new HttpFormEntityBuilder(httpRequestConfig);

            default:
                throw new RuntimeException("entity type [ " + entityType + " ] can not be recognized");
        }
    }

}
