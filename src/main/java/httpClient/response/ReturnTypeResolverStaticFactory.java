package httpClient.response;

import java.lang.reflect.Type;

public class ReturnTypeResolverStaticFactory {

    public static ReturnTypeResolver getReturnTypeResolver(Type type) {
        if (type == null) {
            return null;
        }

        if(type.equals(void.class)) {
            return ReturnTypeVoidResolver.getInstance();
        }
        else if(type.equals(String.class)) {
            return ReturnTypeStringResolver.getInstance();
        }
        else return ReturnTypeObjectResolver.getInstance();


    }

}
