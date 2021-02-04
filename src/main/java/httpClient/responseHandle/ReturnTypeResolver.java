package httpClient.responseHandle;

import java.lang.reflect.Type;

public interface ReturnTypeResolver {

    Object resolve(String str, Type type);
}
