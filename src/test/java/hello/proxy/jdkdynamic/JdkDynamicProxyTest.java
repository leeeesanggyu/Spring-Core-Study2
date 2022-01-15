package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        //구현체 생성
        AInterface target = new AImpl();
        //핸들러 생성
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        //Proxy class 생성
        AInterface proxyInstance = (AInterface) Proxy.newProxyInstance(
                AInterface.class.getClassLoader(),
                new Class[]{AInterface.class},
                handler
        );

        //프록시 실행
        proxyInstance.call(); //handler의 invoke가 실행되고 call이 넘어감

        log.info("target class -> {}", target.getClass());
        log.info("proxyInstance class -> {}", proxyInstance.getClass());
    }

    @Test
    void dynamicB() {
        BInterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        BInterface proxyInstance = (BInterface) Proxy.newProxyInstance(
                AInterface.class.getClassLoader(),
                new Class[]{BInterface.class},
                handler
        );

        proxyInstance.call();

        log.info("target class -> {}", target.getClass());
        log.info("proxyInstance class -> {}", proxyInstance.getClass());

    }
}
