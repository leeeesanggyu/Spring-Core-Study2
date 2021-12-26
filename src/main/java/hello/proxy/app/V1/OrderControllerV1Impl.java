package hello.proxy.app.V1;

public class OrderControllerV1Impl implements OrderControllerV1{

    //주입 받은 서비스는 실제 객체가 아니고 Proxy를 주입받음(InterfaceProxyConfig에서 설정함)
    private final OrderServiceV1 orderService;

    public OrderControllerV1Impl(OrderServiceV1 orderServiceV1) {
        this.orderService = orderServiceV1;
    }

    @Override
    public String request(String itemId) {
        orderService.OrderItem(itemId);
        return "ok";
    }

    @Override
    public String noLog() {
        return "ok";
    }
}
