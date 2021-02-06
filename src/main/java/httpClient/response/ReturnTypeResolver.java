package httpClient.response;

import java.lang.reflect.Type;

public interface ReturnTypeResolver {

    Object resolve(String str, Type type);
}
