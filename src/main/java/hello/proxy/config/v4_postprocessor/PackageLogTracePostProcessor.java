package hello.proxy.config.v4_postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
public class PackageLogTracePostProcessor implements BeanPostProcessor {

    private final String basePackage;
    private final Advisor advisor;

    public PackageLogTracePostProcessor(String basePackage, Advisor advisor) {
        this.basePackage = basePackage;
        this.advisor = advisor;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("bean -> {}, beanName -> {}", bean.getClass(), beanName);

        final String packageName = bean.getClass().getPackageName();
        if (!packageName.startsWith(basePackage)) {
            return bean;
        }

        final ProxyFactory proxy = new ProxyFactory(bean);
        proxy.addAdvisor(advisor);
        log.info("target -> {}, proxy -> {}", bean.getClass(), proxy.getClass());
        return proxy.getProxy();
    }
}
