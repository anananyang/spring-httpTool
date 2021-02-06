package httpClient.annotation;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface HttpReqParam {
    String value() default "";     // 参数名
}
