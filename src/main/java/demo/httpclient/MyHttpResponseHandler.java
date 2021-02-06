package demo.httpclient;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MyHttpResponseHandler implements ResponseHandler {

    @Override
    public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
        StatusLine statusLine = httpResponse.getStatusLine();
        if (statusLine.getStatusCode() > 300) {
            throw new RuntimeException("http response exception: " + statusLine.getStatusCode());
        }

        Header header = httpResponse.getFirstHeader("Content-Type");
        if(header.getValue().equals("application/json")) {
            System.out.println("json");
        }

        HttpEntity entity = httpResponse.getEntity();
        return EntityUtils.toString(entity, "UTF-8");
    }
}
