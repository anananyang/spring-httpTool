package httpClient.factory.entityBuilder;

import com.alibaba.fastjson.JSON;
import httpClient.HttpRequestConfig;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

public class HttpStringEntityBuilder extends HttpEntityBuilder {

    public HttpStringEntityBuilder(HttpRequestConfig httpRequestConfig) {
        super(httpRequestConfig);
    }

    @Override
    public HttpEntity build() throws UnsupportedEncodingException{
        Object reqBody = httpRequestConfig.getReqBody();
        if (reqBody == null) {
            return null;
        }
        /**
         * TODO 原则上，这里要根据 Content-Type 来进行相应的处理
         * 1. 将 Content-Type 解析成 cntentType 对象，cntentType 有 type、subType，charset, boundary
         *     1.1、根据 subType 来判断内容来进行处理
         */
        String bodyStr = reqBody instanceof String ? (String) reqBody : JSON.toJSONString(reqBody);
        StringEntity stringEntity = new StringEntity(bodyStr);
        return stringEntity;
    }
}
