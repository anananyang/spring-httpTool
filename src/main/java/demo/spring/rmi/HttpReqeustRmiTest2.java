package demo.spring.rmi;

import com.alibaba.fastjson.JSONObject;
import httpClient.annotation.*;
import httpClient.constants.HttpMethod;

@HttpReq(url = "${anyang.login.url}")
public interface HttpReqeustRmiTest2 {

    @HttpReqHeader(name = "Content-Type", value = "application/json")
    @HttpReqConfig(httpMethod = HttpMethod.POST, path = "/user/login")
    String getRemoteObj(@HttpReqBody JSONObject object);

}
