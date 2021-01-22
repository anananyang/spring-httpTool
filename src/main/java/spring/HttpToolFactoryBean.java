package spring;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

public class HttpToolFactoryBean<T> implements FactoryBean<T> {

    private Class<T> httpToolInterface;

    public void setHttpToolInterface(Class<T> httpToolInterface) {
        this.httpToolInterface = httpToolInterface;
    }

    @Override
    public T getObject() throws Exception {
        return (T) Proxy.newProxyInstance(httpToolInterface.getClassLoader(),
                new Class[]{httpToolInterface},
                new HttpToolProxy(httpToolInterface));
    }

    @Override
    public Class<?> getObjectType() {
        return httpToolInterface;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
