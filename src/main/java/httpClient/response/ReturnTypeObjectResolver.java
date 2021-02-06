package httpClient.response;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;

public class ReturnTypeObjectResolver implements ReturnTypeResolver {

    private static final ReturnTypeResolver resolver = new ReturnTypeObjectResolver();

    private ReturnTypeObjectResolver() {
    }

    public static ReturnTypeResolver getInstance() {
        return resolver;
    }


    @Override
    public Object resolve(String str, Type type) {
        if(StringUtils.isBlank(str)) {
            return null;
        }

        return JSON.parseObject(str, type);
    }
}
