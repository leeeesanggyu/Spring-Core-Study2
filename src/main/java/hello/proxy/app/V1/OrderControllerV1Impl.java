package hello.proxy.app.V1;

public class OrderControllerV1Impl implements OrderControllerV1{

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
