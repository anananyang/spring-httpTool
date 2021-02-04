package httpClient.responseHandle;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * singleton
 */
public class HttpResoponseHandler {

    private static final HttpResoponseHandler handler = new HttpResoponseHandler();

    private HttpResoponseHandler() {
    }

    public static HttpResoponseHandler getInstance() {
        return handler;
    }


    public Object handle(Method method, HttpRequestBase httpRequest, HttpResponse httpResponse) throws Throwable {
        if(httpResponse == null) {
            throw new RuntimeException("http response is null");
        }
        HttpEntity httpEntity = httpResponse.getEntity();
        Integer stateCode = httpResponse.getStatusLine().getStatusCode();
        if(stateCode >= 300) {
            EntityUtils.consume(httpEntity);
            throw new RuntimeException("http request [ " + httpRequest.getURI().toString()
                    + " ] stateCode [ " + stateCode + " ]: " + httpResponse.getStatusLine().getReasonPhrase());
        }
        String str = EntityUtils.toString(httpEntity, "UTF-8");
        System.out.println(str);

        Type returnType = method.getReturnType();
        return ReturnTypeResolverStaticFactory.getReturnTypeResolver(returnType).resolve(str, returnType);
    }

}
