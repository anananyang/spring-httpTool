package spring;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.Set;

public class ClassPathHttpToolScanner extends ClassPathBeanDefinitionScanner {

    private Class<? extends Annotation> annotationClass;
    private Class<?> markerInterface;
    private String httpClientManagerBeanName;
    private String propertiesResolverName;
    private String proxyRuleName;


    public ClassPathHttpToolScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public void setMarkerInterface(Class<?> markerInterface) {
        this.markerInterface = markerInterface;
    }

    public void setHttpClientManagerBeanName(String httpClientManagerBeanName) {
        this.httpClientManagerBeanName = httpClientManagerBeanName;
    }

    public void setPropertiesResolverName(String propertiesResolverName) {
        this.propertiesResolverName = propertiesResolverName;
    }

    public void setProxyRuleName(String proxyRuleName) {
        this.proxyRuleName = proxyRuleName;
    }

    public void registerFilters() {
        if (annotationClass != null) {
            this.addIncludeFilter(new AnnotationTypeFilter(annotationClass));
        }
        // 禁止 markerInterface
        if (markerInterface != null) {
            this.addIncludeFilter(new AssignableTypeFilter(markerInterface) {
                @Override
                protected boolean matchClassName(String className) {
                    return false;
                }
            });
        }
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> definitionHolders = super.doScan(basePackages);
        if (definitionHolders.isEmpty()) {

            return definitionHolders;
        }

        Iterator it = definitionHolders.iterator();
        while (it.hasNext()) {
            BeanDefinitionHolder definitionHolder = (BeanDefinitionHolder) it.next();
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition) definitionHolder.getBeanDefinition();
            MutablePropertyValues mutablePropertyValues = beanDefinition.getPropertyValues();
            mutablePropertyValues.addPropertyValue("httpToolInterface", beanDefinition.getBeanClassName());
            mutablePropertyValues.addPropertyValue("httpClientManager", new RuntimeBeanReference(httpClientManagerBeanName));
            mutablePropertyValues.addPropertyValue("propertiesResolver", new RuntimeBeanReference(propertiesResolverName));
            if (StringUtils.isNotBlank(proxyRuleName)) {
                mutablePropertyValues.addPropertyValue("proxyRule", new RuntimeBeanReference(proxyRuleName));
            }
            // 实际初始化的是一个工厂类，调用该工厂类的 getObject 方法，利用 JDK 动态代理生成一个代理对象
            beanDefinition.setBeanClass(HttpToolFactoryBean.class);
            beanDefinition.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);
        }

        return definitionHolders;
    }

    /**
     * 如果扫描的是接口，必须要重载这个方法，否则会识别不到接口
     * 因为 spring 默认只能读出 Class
     *
     * @param beanDefinition
     * @return
     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }
}
