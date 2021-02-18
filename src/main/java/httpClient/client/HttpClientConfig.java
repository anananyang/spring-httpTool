package httpClient.client;

public class HttpClientConfig {

    private Integer timeout = 20 * 1000;
    private Integer connectionTimeout = 10 * 1000;
    private Integer retryCount = 1;
    // manage connection pool
    private Integer totalMaxConnetions = 200;
    private Integer defaultMaxPerRoute = 20;
    private Integer maxLogLen = 512;


    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public Integer getTotalMaxConnetions() {
        return totalMaxConnetions;
    }

    public void setTotalMaxConnetions(Integer totalMaxConnetions) {
        this.totalMaxConnetions = totalMaxConnetions;
    }

    public Integer getDefaultMaxPerRoute() {
        return defaultMaxPerRoute;
    }

    public void setDefaultMaxPerRoute(Integer defaultMaxPerRoute) {
        this.defaultMaxPerRoute = defaultMaxPerRoute;
    }

    public Integer getMaxLogLen() {
        return maxLogLen;
    }

    public void setMaxLogLen(Integer maxLogLen) {
        this.maxLogLen = maxLogLen;
    }
}
