package com.codelab.repository;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaRepository {

    private final static String TOPIC = "test";
    private final static String BOOTSTRAP_SERVER = "localhost:9092";

    private static Producer<Long, String> createProducer() {
        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);

        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaRepository");

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());

        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }

    public static boolean publish(final String movieName) {

        final Producer<Long, String> producer = createProducer();

        try {
            final ProducerRecord<Long, String> record = new ProducerRecord<>(TOPIC, movieName);

            producer.send(record).get();

            System.out.println("published to kafka successfully");
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            producer.flush();
            producer.close();
        }
    }
}
