package demo.spring.rmi;

import httpClient.annotation.*;
import httpClient.constants.HttpMethod;

@HttpReq(domain = "${anyang.go.domain}")
//@HttpReq(url = "www.anyanggo.tk")
public interface HttpRequestRmiTest {

    @HttpReqConfig(httpMethod = HttpMethod.GET, path = "/{path}")
//    @HttpReqHeader(name = "Content-Type", value = "application/json")
    String getRemoteObj(@PathVariable(name = "path")String path);
}
