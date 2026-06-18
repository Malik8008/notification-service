package az.notificationservice.listener;

import az.notificationservice.configuration.RabbitMQConfig;
import az.notificationservice.dto.OrderEventDTO;
import az.notificationservice.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationListener {
    private final MailService mailService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receive(OrderEventDTO event) {

        log.info("Sifariş alındı: OrderId={}, ProductId={}, Quantity={}",
                event.orderId(),
                event.productId(),
                event.quantity()
        );

//        try{mailService.sendMail(
//                "thesafarovmalik@gmail.com",
//                "Order Created",
//                "Sifariş qəbul edildi. Order ID: " + event.orderId()
//        );}
//        catch (Exception e){
//            log.error("Mail göndərilmədi", e);
//        }
    }
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter) {

        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);

        return factory;
    }
}
