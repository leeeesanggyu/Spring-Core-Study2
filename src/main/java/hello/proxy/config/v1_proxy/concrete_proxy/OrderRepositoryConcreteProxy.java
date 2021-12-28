package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.V2.OrderRepositoryV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {

    private final OrderRepositoryV2 target;
    private final LogTrace logTrace;

    public OrderRepositoryConcreteProxy(OrderRepositoryV2 orderRepositoryV2, LogTrace logTrace) {
        this.target = orderRepositoryV2;
        this.logTrace = logTrace;
    }

    @Override
    public void save(String itemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = logTrace.begin("OrderRepository.save()");
            target.save(itemId);
            logTrace.end(traceStatus);
        } catch (Exception e) {
            logTrace.exception(traceStatus, e);
            throw e;
        }
    }
}
