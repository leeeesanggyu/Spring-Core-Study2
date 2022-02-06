package hello.proxy.advisor;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

@Slf4j
public class MultiAdvisorTest {

    @Test
    @DisplayName("여러개의 프록시를 적용2")
    public void multiAdvisorTest2() {
        // Client -> proxy2(advisor2) -> proxy1(advisor1) -> target

        // proxy1 생성
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.setMappedNames("find");
        log.info("{}", nameMatchMethodPointcut.getMethodMatcher());

        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(nameMatchMethodPointcut, new Advice1());
        proxyFactory.addAdvisor(advisor1);

        ServiceInterface proxy1 = (ServiceInterface) proxyFactory.getProxy();

        // proxy2 생성
        ProxyFactory proxyFactory2 = new ProxyFactory(proxy1);

        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(nameMatchMethodPointcut, new Advice2());
        proxyFactory2.addAdvisor(advisor2);

        ServiceInterface proxy2 = (ServiceInterface) proxyFactory2.getProxy();

        proxy2.find();
        proxy2.save();
    }

    @Test
    @DisplayName("한개의 proxy에 다수의 Advice 적용")
    public void multiAdvisorTest1() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.setMappedNames("find");
        log.info("{}", nameMatchMethodPointcut.getMethodMatcher());

        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(nameMatchMethodPointcut, new Advice1());
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(nameMatchMethodPointcut, new Advice2());

        proxyFactory.addAdvisor(advisor1);
        proxyFactory.addAdvisor(advisor2);

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        proxy.find();
        proxy.save();
    }

    @Slf4j
    static class Advice1 implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("proxy1 call");
            return invocation.proceed();
        }
    }

    @Slf4j
    static class Advice2 implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("proxy2 call");
            return invocation.proceed();
        }
    }
}
