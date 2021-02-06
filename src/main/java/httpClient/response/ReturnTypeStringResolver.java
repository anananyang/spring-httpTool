package httpClient.response;

import java.lang.reflect.Type;

public class ReturnTypeStringResolver implements ReturnTypeResolver{

    private static final ReturnTypeResolver resolver = new ReturnTypeStringResolver();

    private ReturnTypeStringResolver() {
    }

    public static ReturnTypeResolver getInstance() {
        return resolver;
    }

    @Override
    public Object resolve(String str, Type type) {
        return str;
    }
}
