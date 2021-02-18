package httpClient.response;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.LogUtil;

import java.lang.reflect.Method;
import java.lang.reflect.Type;


public class HttpResoponseHandler {

    private static Logger logger = LoggerFactory.getLogger(HttpResoponseHandler.class);

    private static final HttpResoponseHandler handler = new HttpResoponseHandler();

    /**
     * singleton
     */
    private HttpResoponseHandler() {
    }

    public static HttpResoponseHandler getInstance() {
        return handler;
    }


    public String handle(Method method,
                         HttpRequestBase httpRequest,
                         HttpResponse httpResponse) throws Throwable {
        if (httpResponse == null) {
            throw new RuntimeException("http response is null");
        }
        HttpEntity httpEntity = httpResponse.getEntity();
        Integer stateCode = httpResponse.getStatusLine().getStatusCode();
        if (stateCode >= 300) {
            EntityUtils.consume(httpEntity);
            throw new HttpResponseException(stateCode, httpResponse.getStatusLine().getReasonPhrase());
        }
        return EntityUtils.toString(httpEntity, "UTF-8");
    }

}
