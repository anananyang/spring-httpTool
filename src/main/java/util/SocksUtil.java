package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.SocketImpl;

public class SocksUtil {

    private static Logger logger = LoggerFactory.getLogger(SocksUtil.class);

    public static void socks4(Socket socket) {
        Class clazzSocks = socket.getClass();
        Method setSockVersion = null;
        Field sockImplField = null;
        SocketImpl socksimpl = null;
        try {
            sockImplField = clazzSocks.getDeclaredField("impl");
            sockImplField.setAccessible(true);
            socksimpl = (SocketImpl) sockImplField.get(socket);
            Class clazzSocksImpl = socksimpl.getClass();
            setSockVersion = clazzSocksImpl.getDeclaredMethod("setV4");
            setSockVersion.setAccessible(true);
            if (null != setSockVersion) {
                setSockVersion.invoke(socksimpl);
            }
            sockImplField.set(socket, socksimpl);
        } catch (Exception e) {
            LogUtil.error(logger, "socks 4 proxy error");
//            throw e;
        }

    }
}
