package az.notificationservice.listener;

import az.notificationservice.configuration.RabbitMQConfig;
import az.notificationservice.dto.OrderEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationListener {
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receive(OrderEventDTO event) {

        log.info("Sifariş alındı: OrderId={}, ProductId={}, Quantity={}",
                event.orderId(),
                event.productId(),
                event.quantity()
        );
    }
}
