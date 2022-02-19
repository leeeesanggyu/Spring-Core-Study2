package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BasicTest {

    @Test
    public void basicConfig() {
        // 스프링 컨테이너 생성
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);

        final A beanA = applicationContext.getBean("beanA", A.class);
        beanA.helloA();

//        final B beanB = applicationContext.getBean("beanB", B.class);
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean("beanB", B.class));
    }

    @Slf4j
    @Configuration
    static class BasicConfig {
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }
    }

    @Slf4j
    static class A {
        public void helloA() {
            log.info("hello A");
        }
    }

    @Slf4j
    static class B {
        public void helloB() {
            log.info("hello B");
        }
    }
}
