package spring;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

public class PropertiesResolver implements EmbeddedValueResolverAware {

    private StringValueResolver stringValueResolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver = resolver;
    }

    public String resolveStringValue(String value) {
        if(StringUtils.isBlank(value)) {
            return value;
        }
        value = value.trim();
        if(value.indexOf("${") >= 0) {
            return stringValueResolver.resolveStringValue(value);
        }
        return value;
    }


}
