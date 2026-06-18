package az.notificationservice.dto;

public record MailEventDTO(
        String to,
        String subject,
        String body) {
}
