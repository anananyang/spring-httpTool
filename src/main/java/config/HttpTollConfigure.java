package config;

import annotation.HttpReqScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@HttpReqScan(value = {"demo.spring"}, annotationClass = annotation.HttpReq.class)
public class HttpTollConfigure {
}
