package spring;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;

public class HttpToolScannerConfigure implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    private String basePackage;
    private Class<? extends Annotation> annotationClass;
    private Class<?> markerInterface;
    private ApplicationContext applicationContext;    // 作为默认的 classloader
    private BeanNameGenerator nameGenerator;          // 名字生成器
//    private StringValueResolver embeddedValueResolver;  // properties 解析
    private String httpClientManagerBeanName;
    private String propertiesResolverName;
    private String proxyRuleName;
//
//    @Override
//    public void setEmbeddedValueResolver(StringValueResolver resolver) {
//        this.embeddedValueResolver = resolver;
//    }


    public void setHttpClientManagerBeanName(String httpClientManagerBeanName) {
        this.httpClientManagerBeanName = httpClientManagerBeanName;
    }

    public void setPropertiesResolverName(String propertiesResolverName) {
        this.propertiesResolverName = propertiesResolverName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setMarkerInterface(Class<?> markerInterface) {
        this.markerInterface = markerInterface;
    }

    public void setNameGenerator(BeanNameGenerator nameGenerator) {
        this.nameGenerator = nameGenerator;
    }

    public void setProxyRuleName(String proxyRuleName) {
        this.proxyRuleName = proxyRuleName;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    /**
     * 配置自定义扫描器，对指定路径下的资源进行扫描
     *
     * @param registry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ClassPathHttpToolScanner scanner = new ClassPathHttpToolScanner(registry, false);
        scanner.setAnnotationClass(this.annotationClass);
        scanner.setMarkerInterface(this.markerInterface);
        scanner.setResourceLoader(this.applicationContext);
        scanner.setBeanNameGenerator(this.nameGenerator);
        scanner.setHttpClientManagerBeanName(this.httpClientManagerBeanName);
        scanner.setPropertiesResolverName(this.propertiesResolverName);
        scanner.setProxyRuleName(proxyRuleName);
//        scanner.setEmbeddedValueResolver(this.embeddedValueResolver);
        scanner.registerFilters();  // 在这里注册需要扫描出来的注解
        // 开始扫描
        scanner.scan(StringUtils.tokenizeToStringArray(this.basePackage, ",; \t\n"));
    }
}
