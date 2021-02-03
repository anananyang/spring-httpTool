package spring.annotation;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import spring.ClassPathHttpToolScanner;

import java.lang.annotation.Annotation;
import java.util.Map;

public class HttpReqScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> attributeMap = importingClassMetadata.getAnnotationAttributes(HttpReqScan.class.getName());
        AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(attributeMap);
        ClassPathHttpToolScanner scanner = new ClassPathHttpToolScanner(registry, false);
        // 先获取 annotation
        Class<? extends Annotation> annotationClass = annoAttrs.getClass("annotationClass");
        // 如果不是默认 Annotation.class
        if (!Annotation.class.equals(annotationClass)) {
            scanner.setAnnotationClass(annotationClass);
        }
        Class<?> markerInterface = annoAttrs.getClass("markerInterface");
        if(!Class.class.equals(markerInterface)) {
            scanner.setMarkerInterface(markerInterface);
        }
        Class<? extends BeanNameGenerator> nameGenerator = annoAttrs.getClass("nameGenerator");
        if(!BeanNameGenerator.class.equals(nameGenerator)) {
            scanner.setBeanNameGenerator(BeanUtils.instantiateClass(nameGenerator));
        }
        String httpClientBeanName = annoAttrs.getString("httpClientBeanName");
        if(StringUtils.isNotBlank(httpClientBeanName)) {
            scanner.setHttpClientBeanName(httpClientBeanName);
        }
        scanner.setResourceLoader(this.resourceLoader);
        scanner.registerFilters();  // 在这里注册需要扫描出来的注解
        scanner.scan(annoAttrs.getStringArray("value"));
    }
}
