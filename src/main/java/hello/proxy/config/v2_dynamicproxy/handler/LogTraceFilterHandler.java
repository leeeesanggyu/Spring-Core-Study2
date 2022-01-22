package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogTraceFilterHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace logTrace;
    private final String[] pattern;

    public LogTraceFilterHandler(Object target, LogTrace logTrace, String[] pattern) {
        this.target = target;
        this.logTrace = logTrace;
        this.pattern = pattern;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 메소드 이름 필터
        if (!PatternMatchUtils.simpleMatch(pattern, method.getName())) {
            return method.invoke(target, args);
        }

        TraceStatus traceStatus = null;
        try {
            String message =
                    method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            traceStatus = logTrace.begin(message);

            Object result = method.invoke(target, args);
            logTrace.end(traceStatus);
            return result;
        } catch (Exception e) {
            logTrace.exception(traceStatus, e);
            throw e;
        }
    }
}
