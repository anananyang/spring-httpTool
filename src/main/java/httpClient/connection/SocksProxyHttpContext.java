package httpClient.connection;

import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class SocksProxyHttpContext extends HttpClientContext {

    public static final String PROXY_RULE_ARRT_KEY = "proxy_rule_attr_key";

    public static SocksProxyHttpContext create(SocksProxyRule proxyRule) {
        return new SocksProxyHttpContext(new BasicHttpContext(), proxyRule);
    }

    public SocksProxyHttpContext(HttpContext context, SocksProxyRule proxyRule) {
        super(context);
        setProxyRule(proxyRule);
    }

    public SocksProxyRule getProxyRule() {
        return (SocksProxyRule) getAttribute(PROXY_RULE_ARRT_KEY);
    }

    public void setProxyRule(SocksProxyRule proxyRule) {
        setAttribute(PROXY_RULE_ARRT_KEY, proxyRule);
    }

}
