package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        // 공통 로직 1
        log.info("start");
        String result = target.callA();
        log.info("result -> {}", result);

        // 공통 로직 2
        log.info("start");
        String result2 = target.callB();
        log.info("result -> {}", result2);
    }

    @Test
    void reflection1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //class info
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        log.info("class -> {}", classHello);

        Hello target = new Hello();

        // callA 메소드 정보
        Method callA = classHello.getMethod("callA");
        Object result = callA.invoke(target);
        log.info("result1 -> {}", result);

        // callB 메소드 정보
        Method callB = classHello.getMethod("callB");
        Object result2 = callB.invoke(target);
        log.info("result2 -> {}", result2);
    }

    @Test
    public void reflection2() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        Method callA = classHello.getMethod("callA");
        Method callB = classHello.getMethod("callB");
        Hello hello = new Hello();

        dynamicCall(callA, hello);
        dynamicCall(callB, hello);

    }

    private void dynamicCall(Method method, Object target) throws InvocationTargetException, IllegalAccessException {
        log.info("start");
        Object result = method.invoke(target);  // 추상화
        log.info("result -> {}", result);
    }

    class Hello {
        public String callA() {
            log.info("Call A");
            return "A";
        }

        public String callB() {
            log.info("Call B");
            return "B";
        }
    }
}
