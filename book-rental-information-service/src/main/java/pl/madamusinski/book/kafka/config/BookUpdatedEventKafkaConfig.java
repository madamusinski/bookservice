package pl.madamusinski.book.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import pl.madamusinski.book.events.model.BookUpdatedEvent;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BookUpdatedEventKafkaConfig {

    @Bean
    public ConsumerFactory<String, BookUpdatedEvent> bookUpdatedEventKafkaConsumerFactory(NotificationProperties properties, @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers) {
        Map<String, Object> configProperties = new HashMap<>();
        configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, properties.getKafka().groupId());
        configProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, properties.getKafka().autoOffsetResetPolicy());
        return new DefaultKafkaConsumerFactory<>(configProperties, new StringDeserializer(), new JsonDeserializer<>(BookUpdatedEvent.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BookUpdatedEvent> kafkaListenerContainerFactory(ConsumerFactory<String, BookUpdatedEvent> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, BookUpdatedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
