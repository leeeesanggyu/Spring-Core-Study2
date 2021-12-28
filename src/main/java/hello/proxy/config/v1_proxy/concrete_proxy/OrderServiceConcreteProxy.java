package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.V2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends OrderServiceV2 {

    private final OrderServiceV2 target;
    private final LogTrace logTrace;

    public OrderServiceConcreteProxy(OrderServiceV2 target, LogTrace logTrace) {
        //문법때문에 어쩔 수 없고 프록시에선 사용하지 않기 때문에 null을 넣어준다.
        super(null);
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void OrderItem(String itemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = logTrace.begin("OrderService.OrderItem()");
            target.OrderItem(itemId);
            logTrace.end(traceStatus);
        } catch (Exception e) {
            logTrace.exception(traceStatus, e);
            throw e;
        }
    }
}
