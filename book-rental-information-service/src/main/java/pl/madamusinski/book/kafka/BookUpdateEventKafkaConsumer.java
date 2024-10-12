package pl.madamusinski.book.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.madamusinski.book.events.model.BookUpdatedEvent;
import pl.madamusinski.book.service.BookRentalInformationService;

@Component
@Slf4j
@RequiredArgsConstructor
public class BookUpdateEventKafkaConsumer {

    private final BookRentalInformationService bookRentalService;

    @KafkaListener(topics = "${notification.kafka.topic}", containerFactory = "kafkaListenerContainerFactory")
    public void processMessage(ConsumerRecord<String, BookUpdatedEvent> consumerRecord) {
        log.info("Received record {}", consumerRecord.value());
        var bookEvent = consumerRecord.value();
        if (BookUpdatedEvent.BookEventType.CREATED == bookEvent.bookEventType()) {
            bookRentalService.createBook(bookEvent.book());
        }
        if (BookUpdatedEvent.BookEventType.UPDATED == bookEvent.bookEventType()) {
            bookRentalService.updateBookStatusRented(bookEvent.book());
        }
    }
}
