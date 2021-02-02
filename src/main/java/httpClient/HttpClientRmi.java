package httpClient;

import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.FactoryBean;

public class HttpClientRmi implements FactoryBean{

    private Object object;

    @Override
    public Object getObject() throws Exception {
        object = HttpClients.createDefault();
        return object;
    }

    @Override
    public Class<?> getObjectType() {
        return object.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
