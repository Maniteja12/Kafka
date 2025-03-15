package com.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MessageProducer {
    String topicName = "test-topic-replicated";
    KafkaProducer<String, String> kafkaProducer;

    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    public MessageProducer(Map<String, Object> propsMap){
        kafkaProducer = new KafkaProducer<String, String>(propsMap);
    }
    public static Map<String, Object> propsMap(){
        Map<String, Object> propMaps = new HashMap<>();

        propMaps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092,localhost:9093,localhost:9094");
        propMaps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // Used coz the key value pair is stored in the form of bytes and this is useful to convert into readable form
        propMaps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        propMaps.put(ProducerConfig.ACKS_CONFIG, "all");
        return propMaps;
    }

    Callback callback = (recordMetadata, exception) ->{
        if(exception!= null)
            logger.error("Exception in publishedMessage as {}", exception.getMessage());
        else
            logger.info("Message published with offset as {} and partition as {}", recordMetadata.offset(), recordMetadata.partition());
    };

    public void close(){
        kafkaProducer.close();
    }

    public void publishMessageAsync(String key, String value){
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, key, value);
        kafkaProducer.send(producerRecord, callback);

    }

    // this is a synchronous message transfer method
    public void publishMessage(String key, String value){
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, key, value);

        try {
           RecordMetadata recordMetadata = kafkaProducer.send(producerRecord).get();
            //System.out.println("partition: " + recordMetadata.partition() + " ,offset: "+ recordMetadata.offset());
            logger.info("Message {} sent successfully for the key {}", value, key);
            logger.info("Message published with offset as {} and partition as {}", recordMetadata.offset(), recordMetadata.partition());
        } catch (InterruptedException | ExecutionException e) {
            //throw new RuntimeException(e);
            logger.error("Exception in publishedMessage as {}", e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MessageProducer messageProducer = new MessageProducer(propsMap());
        messageProducer.publishMessage("1","ABC");
        messageProducer.publishMessage("1","DEF");
        //messageProducer.publishMessageAsync(null,"adams-async");
        //Thread.sleep(3033);
    }
}
