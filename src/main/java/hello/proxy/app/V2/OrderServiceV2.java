package hello.proxy.app.V2;

import hello.proxy.app.V1.OrderRepositoryV1;

public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;

    public OrderServiceV2(OrderRepositoryV2 orderRepositoryV2) {
        this.orderRepository = orderRepositoryV2;
    }

    public void OrderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
