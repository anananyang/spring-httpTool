package httpClient.connection;

import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.protocol.HttpContext;
import util.SocksUtil;

import java.io.IOException;
import java.net.Proxy;
import java.net.Socket;

public class MyPlainConnectionSocketFactory extends PlainConnectionSocketFactory {

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
