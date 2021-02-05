package spring.config;

import httpClient.annotation.HttpReq;
import org.springframework.context.annotation.Configuration;
import spring.annotation.HttpReqScan;

@Configuration
@HttpReqScan(value = {"demo.spring"},
             annotationClass = HttpReq.class,
             httpClientManagerBeanName = "httpClientManager",
             propertiesResolverName = "propertiesResolverName")
public class HttpTollConfigure {
}
