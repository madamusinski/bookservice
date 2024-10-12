package pl.madamusinski.book.events.model;

import lombok.Builder;
import pl.madamusinski.book.model.Book;

import java.time.Instant;
import java.util.UUID;

@Builder
public record BookUpdatedEvent(String transactionId, BookEventType bookEventType, Instant occurrenceTime, Book book) implements PublishableEvent {

    public static BookUpdatedEvent createBookEvent(Book book, BookEventType bookEventType, Instant occurrenceTime) {
        return BookUpdatedEvent.builder()
                .transactionId(UUID.randomUUID().toString())
                .bookEventType(bookEventType)
                .occurrenceTime(occurrenceTime)
                .book(book)
                .build();
    }

    public enum BookEventType {
        CREATED("Book created"),
        UPDATED("Book updated");

        private final String message;

        BookEventType(String message) {
            this.message = message;
        }

        public String message() {
            return this.message;
        }
    }
}

