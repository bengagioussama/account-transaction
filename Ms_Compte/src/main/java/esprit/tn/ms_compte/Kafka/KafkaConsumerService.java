package esprit.tn.ms_compte.Kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {
    @KafkaListener(topics = "transaction-topic")
    public void consume(String message) {
        log.info("Received message from Kafka: {}", message);
        try {
            // Process message
        } catch (Exception e) {
            log.error("Error processing Kafka message: {}", e.getMessage(), e);
            // Consider implementing dead-letter queue pattern
        }
    }
}
