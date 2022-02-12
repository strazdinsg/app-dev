package no.ntnu.kafkademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Listens for incoming Kafka messages on a given topic
 */
@Component
public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger("Consumer");

    /**
     * This method is called whenever a message on topic "dbUpdate" is received
     * @param message The received message
     */
    @KafkaListener(id = "dummy", topics = "dbUpdate")
    public void onKafkaMessageReceived(String message) {
        logger.info("Kafka message received: " + message);
    }
}
