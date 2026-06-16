package az.notificationservice.dto;

public record OrderEventDTO(
        Long orderId,
        Long productId,
        int quantity) {
}
