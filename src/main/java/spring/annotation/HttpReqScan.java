package spring.annotation;

import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(HttpReqScannerRegistrar.class)
public @interface HttpReqScan {

    String[] value() default "";   // basePackage

    Class<? extends Annotation> annotationClass() default Annotation.class;

    Class<?> markerInterface() default Class.class;

    Class<? extends BeanNameGenerator> nameGenerator() default BeanNameGenerator.class;

    String httpClientBeanName();

    String propertiesResolverName();

}
