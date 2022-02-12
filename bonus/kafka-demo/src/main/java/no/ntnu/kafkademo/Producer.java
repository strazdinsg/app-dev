package no.ntnu.kafkademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Sends periodic Kafka messages
 */
@Component
public class Producer {
    /**
     * Kafka message template, for sending messages
     */
    @Autowired
    private KafkaTemplate<String, String> template;

    /** A counter to ensure unique messages */
    private static int counter = 0;

    private static final Logger logger = LoggerFactory.getLogger("Producer");

    /**
     * Sends a Kafka message once every 5 seconds
     */
    @Scheduled(fixedRate = 5000)
    public void sendMessage() {
        String message = "Database update Nr " + counter;
        logger.info("Sending message: " + message);
        template.send("dbUpdate", message);
        counter++;
    }
}
