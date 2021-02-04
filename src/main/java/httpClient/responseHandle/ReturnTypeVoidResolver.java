package httpClient.responseHandle;

import java.lang.reflect.Type;

public class ReturnTypeVoidResolver implements ReturnTypeResolver{

    private static final ReturnTypeResolver resolver = new ReturnTypeVoidResolver();

    private ReturnTypeVoidResolver() {
    }

    public static ReturnTypeResolver getInstance() {
        return resolver;
    }

    @Override
    public Object resolve(String str, Type type) {
        return null;
    }
}
