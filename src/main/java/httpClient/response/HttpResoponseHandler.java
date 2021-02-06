package httpClient.response;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;


public class HttpResoponseHandler {

    private static final HttpResoponseHandler handler = new HttpResoponseHandler();

    /**
     * singleton
     */
    private HttpResoponseHandler() {}

    public static HttpResoponseHandler getInstance() {
        return handler;
    }


    public Object handle(Method method, HttpRequestBase httpRequest, HttpResponse httpResponse) throws Throwable {
        if (httpResponse == null) {
            throw new RuntimeException("http response is null");
        }
        HttpEntity httpEntity = httpResponse.getEntity();
        Integer stateCode = httpResponse.getStatusLine().getStatusCode();
        if (stateCode >= 300) {
            EntityUtils.consume(httpEntity);
            throw new HttpResponseException(stateCode, httpResponse.getStatusLine().getReasonPhrase());
        }
        String str = EntityUtils.toString(httpEntity, "UTF-8");
        System.out.println(str);

        Type returnType = method.getReturnType();
        return ReturnTypeResolverStaticFactory.getReturnTypeResolver(returnType).resolve(str, returnType);
    }

}
