package httpClient.connection;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.LogUtil;
import util.SocksUtil;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.Proxy;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class MySSLConnectionSocketFactory extends SSLConnectionSocketFactory {

    private static Logger logger = LoggerFactory.getLogger(MySSLConnectionSocketFactory.class);

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
        if (proxy != null) {
            LogUtil.info(logger, "[proxy]{}", proxy);
        }
        Socket socket = new Socket(proxy);
        if (proxy != null && proxyRule.isSocks4Proxy()) {
            SocksUtil.socks4(socket);
        }
        return socket;
    }
}
