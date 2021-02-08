package httpClient.connection;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.protocol.HttpContext;
import util.SocksUtil;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.Proxy;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class MySSLConnectionSocketFactory extends SSLConnectionSocketFactory {

    public MySSLConnectionSocketFactory() throws NoSuchAlgorithmException {
        super(SSLContext.getDefault());
    }


    @Override
    public Socket createSocket(HttpContext context) throws IOException {
        SocksProxyRule proxyRule = (SocksProxyRule) context.getAttribute(SocksProxyHttpContext.PROXY_RULE_ARRT_KEY);
        if (proxyRule == null || !proxyRule.isSwitchOn()) {
            return super.createSocket(context);
        }
        Proxy proxy = proxyRule.getNetProxy();
        Socket socket = new Socket(proxy);
        if(proxy != null && proxyRule.isSocks4Proxy()) {
            SocksUtil.socks4(socket);
        }
        return socket;
    }
}
