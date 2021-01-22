package demo.spring.rmi;

import annotation.HttpReq;

@HttpReq
public interface HttpRequestRmiTest {

    int getRemoteObj();
}
