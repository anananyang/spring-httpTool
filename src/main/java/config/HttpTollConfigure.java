package config;

import annotation.HttpReqScan;
import httpClient.annotation.HttpReq;
import org.springframework.context.annotation.Configuration;

@Configuration
@HttpReqScan(value = {"demo.spring"}, annotationClass = HttpReq.class, httpClientBeanName = "httpClientRmi")
public class HttpTollConfigure {
}
