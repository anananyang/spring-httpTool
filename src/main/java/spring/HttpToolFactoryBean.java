package spring;

import httpClient.responseHandle.HttpResoponseHandler;
import httpClient.proxy.HttpToolProxy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

public class HttpToolFactoryBean<T> implements FactoryBean<T> {

    private Class<T> httpToolInterface;
    private CloseableHttpClient httpClient;
    private PropertiesResolver propertiesResolver;


    public void setHttpToolInterface(Class<T> httpToolInterface) {
        this.httpToolInterface = httpToolInterface;
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void setPropertiesResolver(PropertiesResolver propertiesResolver) {
        this.propertiesResolver = propertiesResolver;
    }

    @Override
    public T getObject() throws Exception {
        HttpToolProxy httpToolProxy = new HttpToolProxy(httpToolInterface,
                httpClient,
                propertiesResolver,
                HttpResoponseHandler.getInstance());

        return (T) Proxy.newProxyInstance(httpToolInterface.getClassLoader(),
                new Class[]{httpToolInterface},
                httpToolProxy);
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
