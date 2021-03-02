package demo.spring.rmi;

import com.alibaba.fastjson.JSONObject;
import httpClient.annotation.*;
import httpClient.constants.HttpContentType;
import httpClient.constants.HttpHeader;
import httpClient.constants.HttpMethod;

import java.util.Map;

@HttpReq(domain = "${anyang.login.url}")
public interface HttpReqeustPostTest {

    @HttpReqHeader(name = HttpHeader.CONTENT_TYPE, value = HttpContentType.APPLICATION_JSON)
    @HttpReqConfig(httpMethod = HttpMethod.POST, path = "/user/login")
    @HttpReqProxy(proxyHost = "${anyang.http.proxy.host}", proxyPort = "${anyang.http.proxy.port}")
    String getRemoteObj(@HttpReqBody JSONObject object,
                        @HttpReqHeader Map<String, String> header,
                        @HttpReqParam("paramTest") Long paramTest);

}
