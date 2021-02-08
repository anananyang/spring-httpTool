package httpClient.connection;

import org.apache.commons.lang3.StringUtils;
import util.ListUtil;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SocksProxyRule {

    private static final String PROXY_CONFIG_SEPARATOR = ":";
    private boolean switchOn = false;
    private List<String> excludeRegexps;
    private List<Pattern> excludePatterns;
    private String proxy;
    private String proxyScheme;
    private String proxyHost;
    private Integer proxyPort;
    private String localIp;   // local address
    // if target address is localaddress whether it use proxy
    private Boolean localAddrUseProxy = false;


    public SocksProxyRule() {
        localIp = this.getLocalIP();
        System.out.println("local host ip is " + localIp);
    }

    private String getLocalIP() {
        try {
            return InetAddress.getByName(getHostName()).getHostAddress();
        } catch (Exception e) {
            // TODO log
            return null;
        }

    }

    private String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            // TODO log
            return null;
        }
    }

    public void setSwitchOn(Boolean switchOn) {
        this.switchOn = switchOn;
    }

    public void setExcludePatterns(List<Pattern> excludePatterns) {
        this.excludePatterns = excludePatterns;
    }

    public void setProxy(String proxy) {
        if (StringUtils.isBlank(proxy)) {
            return;
        }
        this.proxy = proxy;
        String[] array = proxy.split(PROXY_CONFIG_SEPARATOR);
        if (array.length != 3) {
            throw new RuntimeException("代理 [ " + proxy + " ] 配置错误，请按照 scheme:host:port 格式进行配置");
        }
        this.proxyScheme = array[0];
        this.proxyHost = array[1];
        this.proxyPort = array[2] == null ? null : Integer.valueOf(array[2]);
    }

    public void setExcludeRegexps(List<String> excludeRegexps) {
        this.excludeRegexps = excludeRegexps;
        if (ListUtil.isEmpty(excludeRegexps)) {
            return;
        }
        List<Pattern> patterns = new ArrayList<>();
        for (String reg : excludeRegexps) {
            patterns.add(Pattern.compile(reg));
        }
        this.excludePatterns = patterns;
    }

    public void setProxyScheme(String proxyScheme) {
        this.proxyScheme = proxyScheme;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyScheme() {
        return proxyScheme;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public Boolean isSwitchOn() {
        return switchOn;
    }

    public Boolean isSocksProxy() {
        return StringUtils.isNotBlank(proxyScheme)
                && (proxyScheme.indexOf("socks") >= 0 || proxyScheme.indexOf("SOCKS") >= 0);
    }

    public Boolean isSocks4Proxy() {
        return "socks4".equalsIgnoreCase(proxyScheme);
    }

    public Boolean checkUseProxy(URI uri) {
        return checkUseProxy(uri == null ? null : uri.toString());
    }

    public Boolean checkUseProxy(String url) {
        if (StringUtils.isBlank(url) || !isSwitchOn() || !isValidProxy() || !isSocksProxy()) {
            return false;
        }

        //  local address
        if (isLocalAddress(url) && !localAddrUseProxy) {
            return false;
        }

        if (isExcludeUrl(url)) {
            return false;
        }

        return true;
    }

    private Boolean isExcludeUrl(String url) {
        if (ListUtil.isEmpty(excludePatterns)) {
            return false;
        }

        for (Pattern pattern : excludePatterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }

        return false;
    }


    private Boolean isLocalAddress(String url) {
        return url.contains("127.0.0.1")
                || url.contains("localhost")
                || (StringUtils.isNotBlank(this.localIp) && url.contains(localIp));
    }


    private Boolean isValidProxy() {
        return StringUtils.isNotBlank(proxyScheme)
                && StringUtils.isNotBlank(proxyHost)
                && proxyPort != null;
    }


    public Proxy getNetProxy() {
        if (!isSwitchOn() || !isValidProxy()) {
            return null;
        }

        InetSocketAddress sa = new InetSocketAddress(this.getProxyHost(), this.getProxyPort());
        Proxy.Type type = isSocksProxy() ? Proxy.Type.SOCKS : Proxy.Type.HTTP;
        Proxy proxy = new Proxy(type, sa);
        return proxy;
    }


}
