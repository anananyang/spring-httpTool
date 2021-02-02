package httpClient;

import httpClient.annoParser.*;
import httpClient.annotation.*;
import httpClient.factory.HttpReqesutBuilderStaticFactory;
import httpClient.factory.HttpRequestBuilder;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestConfigParser {

    /**
     * 维护类注解和方法注解解析器
     */
    private static Map<String, HttpToolAnnoParser> annoParserMap = new HashMap<>();

    /**
     * 维护参数注解解析器
     */
    private static Map<String, HttpToolParamAnnoParser> paramAnnoParserMap = new HashMap<>();

    /**
     * 注册默认的注解解析器
     */
    static {
        annoParserMap.put(HttpReq.class.getSimpleName(), new HttpReqAnnoParser());
        annoParserMap.put(HttpReqConfig.class.getSimpleName(), new HttpReqConfigAnnoParser());
        annoParserMap.put(HttpReqHeader.class.getSimpleName(), new HttpReqHeaderAnnoParser());

        paramAnnoParserMap.put(HttpReqBody.class.getSimpleName(), new HttpReqBodyAnnoParser());
        paramAnnoParserMap.put(HttpReqParam.class.getSimpleName(), new HttpReqParamAnnoParser());
    }


    public static HttpRequestConfig parse(Class httpToolInterface,
                                           Method method,
                                           Object[] args) {

        HttpRequestConfig httpRequestConfig = new HttpRequestConfig();
        // 解析类注解
        parseInterfaceAnno(httpRequestConfig, httpToolInterface);
        // 解析方法注解
        parseMethodAnno(httpRequestConfig, method);
        // 解析参数注解
        parseParamAnno(httpRequestConfig, method.getParameters(), args);

        return httpRequestConfig;
    }

    /**
     * 对类注解进行解析
     *
     * @param httpRequestConfig
     * @param httpToolInterface
     */
    private static void parseInterfaceAnno(HttpRequestConfig httpRequestConfig,
                                           Class httpToolInterface) {
        parseAnnotation(httpRequestConfig, httpToolInterface.getAnnotations());
    }

    /**
     * 对方法上的注解进行解析
     *
     * @param httpRequestConfig
     * @param method
     */
    private static void parseMethodAnno(HttpRequestConfig httpRequestConfig,
                                        Method method) {
        parseAnnotation(httpRequestConfig, method.getAnnotations());
    }


    private static void parseAnnotation(HttpRequestConfig httpRequestConfig, Annotation[] annotations) {
        if (ArrayUtils.isEmpty(annotations)) {
            return;
        }
        for (Annotation annotation : annotations) {
            String annoName = annotation.annotationType().getSimpleName();
            HttpToolAnnoParser parser = annoParserMap.get(annoName);
            if (parser == null) {
                throw new RuntimeException("annotation [ " + annoName + " ] can not be recognized");
            }
            parser.parse(annotation, httpRequestConfig);
        }
    }


    /**
     * 对参数上对注解进行解析
     *
     * @param httpRequestConfig
     * @param parameters
     * @param args
     */
    private static void parseParamAnno(HttpRequestConfig httpRequestConfig,
                                       Parameter[] parameters,
                                       Object[] args) {
        if (ArrayUtils.isEmpty(parameters)) {
            return;
        }

        // 如果参数没有注解，则默认返回 HttpReqParam 注解
        Integer paramLen = parameters.length;
        for (int index = 0; index < paramLen; index++) {
            Parameter parameter = parameters[index];
            Object arg = args[index];
            parseParamAnno(httpRequestConfig, parameter, arg);
        }
    }

    private static void parseParamAnno(HttpRequestConfig httpRequestConfig,
                                       Parameter parameter,
                                       Object arg) {
        Annotation[] annotations = parameter.getAnnotations();
        if(ArrayUtils.isEmpty(annotations)) {
            // TODO param does not have any annotations
            return;
        }
        for (Annotation annotation : annotations) {
            String annoName = annotation.annotationType().getSimpleName();
            HttpToolParamAnnoParser parser = paramAnnoParserMap.get(annoName);
            if (parser == null) {
                throw new RuntimeException("annotation [ " + annoName + " ] can not be recognized");
            }
            parser.parse(annotation, parameter, arg, httpRequestConfig);
        }

    }
}
