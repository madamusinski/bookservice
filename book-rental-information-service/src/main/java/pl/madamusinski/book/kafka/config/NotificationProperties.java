package pl.madamusinski.book.kafka.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "notification")
public class NotificationProperties {
    private KafkaProperties kafka;
    public record KafkaProperties(String topic, String groupId, String autoOffsetResetPolicy) {}
}
