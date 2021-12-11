package hello.proxy.app.V1;


public class OrderServiceV1Impl implements OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;

    public OrderServiceV1Impl(OrderRepositoryV1 orderRepositoryV1) {
        this.orderRepository = orderRepositoryV1;
    }

    @Override
    public void OrderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
