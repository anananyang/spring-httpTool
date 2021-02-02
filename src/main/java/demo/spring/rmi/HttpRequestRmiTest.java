package demo.spring.rmi;

import httpClient.annotation.HttpReq;
import httpClient.annotation.HttpReqConfig;
import httpClient.annotation.HttpReqHeader;
import httpClient.annotation.HttpReqParam;
import httpClient.constants.HttpMethod;

@HttpReq(url = "https://www.anyanggo.tk")
//@HttpReq(url = "www.anyanggo.tk")
public interface HttpRequestRmiTest {

    @HttpReqConfig(httpMethod = HttpMethod.GET, path = "/index.html")
    @HttpReqHeader(name = "Content-Type", value = "application/json")
    String getRemoteObj(@HttpReqParam("value") int value);
}
