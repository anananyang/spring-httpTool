package httpClient.factory.entityBuilder;

import httpClient.request.HttpRequestConfig;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import java.util.List;

public class HttpFormEntityBuilder extends HttpEntityBuilder {

    public HttpFormEntityBuilder(HttpRequestConfig httpRequestConfig) {
        super(httpRequestConfig);
    }


    @Override
    public HttpEntity build() {

        List<NameValuePair> parameters = httpRequestConfig.getParameters();
        if(parameters == null || parameters.size() == 0) {
            return null;
        }
        return new UrlEncodedFormEntity(parameters, Consts.UTF_8);
    }
}
