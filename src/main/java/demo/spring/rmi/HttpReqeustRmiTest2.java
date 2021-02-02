package demo.spring.rmi;

import com.alibaba.fastjson.JSONObject;
import httpClient.annotation.*;
import httpClient.constants.HttpMethod;

@HttpReq(url = "http://47.99.184.154:6180/fauna")
public interface HttpReqeustRmiTest2 {


    @HttpReqHeader(name = "Content-Type", value = "application/json")
    @HttpReqConfig(httpMethod = HttpMethod.POST, path = "/user/login")
    String getRemoteObj(@HttpReqBody JSONObject object);

}
