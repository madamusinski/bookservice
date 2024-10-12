package pl.madamusinski.book.events;

import pl.madamusinski.book.events.model.PublishableEvent;

public interface EventPublisher {

    void publishEvent(PublishableEvent event);

}
