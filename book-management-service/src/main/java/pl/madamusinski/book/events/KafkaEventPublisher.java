package pl.madamusinski.book.events;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.madamusinski.book.events.kafka.BookUpdatedEventKafkaProducer;
import pl.madamusinski.book.events.model.BookUpdatedEvent;
import pl.madamusinski.book.events.model.PublishableEvent;

@Component
@RequiredArgsConstructor
public class KafkaEventPublisher implements EventPublisher {

    private final BookUpdatedEventKafkaProducer producer;

    @Override
    public void publishEvent(PublishableEvent event) {
        if(event instanceof BookUpdatedEvent bookUpdatedEvent) {
            producer.sendMessage(bookUpdatedEvent);
        }
    }
}
