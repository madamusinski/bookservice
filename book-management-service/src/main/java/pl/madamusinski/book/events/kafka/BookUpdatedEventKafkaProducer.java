package pl.madamusinski.book.events.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pl.madamusinski.book.events.kafka.config.NotificationProperties;
import pl.madamusinski.book.events.model.BookUpdatedEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class BookUpdatedEventKafkaProducer {

    private final KafkaTemplate<String, BookUpdatedEvent> bookUpdateEventKafkaTemplate;
    private final NotificationProperties notificationProperties;

    public void sendMessage(BookUpdatedEvent message) {
        bookUpdateEventKafkaTemplate.send(notificationProperties.getKafka().topic(), message);
        log.info("Sent successfully message to kafka topic {} with payload {}", notificationProperties.getKafka().topic(), message);
    }
}
