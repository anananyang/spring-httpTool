package httpClient.connection;

import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.LogUtil;
import util.SocksUtil;

import java.io.IOException;
import java.net.Proxy;
import java.net.Socket;

public class MyPlainConnectionSocketFactory extends PlainConnectionSocketFactory {

    private static Logger logger = LoggerFactory.getLogger(MySSLConnectionSocketFactory.class);

    @Override
    public Socket createSocket(HttpContext context) throws IOException{
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
