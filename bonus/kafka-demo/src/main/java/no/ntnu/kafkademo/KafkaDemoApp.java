package no.ntnu.kafkademo;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Proof-of-concept for sending and receiving Apache Kafka messages
 * NB: Apache Kafka service must be running externally for this to work!
 */
@SpringBootApplication
@EnableScheduling
public class KafkaDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoApp.class, args);
    }

    /**
     * Create a Kafka topic if it does not exist already
     *
     * @return A new Kafka topic
     */
    @Bean
    public NewTopic createTopic() {
        return TopicBuilder.name("dbUpdate")
                .build();
    }

}
