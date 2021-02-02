package spring;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

public class HttpToolFactoryBean<T> implements FactoryBean<T> {

    private Class<T> httpToolInterface;
    private CloseableHttpClient httpClient;


    public void setHttpToolInterface(Class<T> httpToolInterface) {
        this.httpToolInterface = httpToolInterface;
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public T getObject() throws Exception {
        return (T) Proxy.newProxyInstance(httpToolInterface.getClassLoader(),
                new Class[]{httpToolInterface},
                new HttpToolProxy(httpToolInterface, httpClient));
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
