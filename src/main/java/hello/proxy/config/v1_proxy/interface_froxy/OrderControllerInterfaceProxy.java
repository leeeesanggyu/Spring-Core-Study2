package hello.proxy.config.v1_proxy.interface_froxy;

import hello.proxy.app.V1.OrderControllerV1;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1 {

    private final OrderControllerV1 target;
    private final LogTrace logTrace;

    @Override
    public String request(String itemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = logTrace.begin("OrderController.request()");
            String result = target.request(itemId);
            logTrace.end(traceStatus);
            return result;
        } catch(Exception e) {
            logTrace.exception(traceStatus, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
        return target.noLog();
    }
}
