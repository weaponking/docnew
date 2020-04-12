import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UrlController {


    @GetMapping("/t/{topic}")
    public String test(@PathVariable String topic) {
        new Thread(new R(topic)).start();
        return "success";
    }
}
class R implements Runnable {

    private String topic;

    public R(String topic) {
        this.topic = topic;
    }

    public void run() {
        Map<String, Object> configs = new HashMap<>();
        configs.put("bootstrap.servers", "localhost:9092");
        configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configs.put("group.id", topic);
        KafkaConsumer<String, String> consumer = new KafkaConsumer(configs);
        consumer.subscribe(Arrays.asList(topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ZERO);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }
}
