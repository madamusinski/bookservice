package pl.madamusinski.book.events.kafka.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "notification")
@Setter
@Getter
public class NotificationProperties {
    private KafkaProperties kafka;

    public record KafkaProperties(String topic) {}
}
