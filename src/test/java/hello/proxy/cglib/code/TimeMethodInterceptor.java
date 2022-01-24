package hello.proxy.cglib.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("Time Proxy 실행");
        Long startTime = System.currentTimeMillis();

        //Method 보다 MethodProxy가 더 빠름
//        Object result = method.invoke(target, args);
        Object result = methodProxy.invoke(target, args);

        Long endTime = System.currentTimeMillis();
        log.info("time->{}", endTime - startTime);
        return result;
    }
}
