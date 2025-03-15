package com.kafka.listeners;

import com.kafka.consumers.MessageConsumer;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MessageRebalanceListeners implements ConsumerRebalanceListener {
    private static final Logger logger = LoggerFactory.getLogger(MessageRebalanceListeners.class);
    KafkaConsumer<String, String> kafkaConsumer;
    public static final String serialiaziedFilePath = "consumers/src/main/resources/offset.ser";

    public MessageRebalanceListeners(KafkaConsumer<String, String> kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> collection) {
        logger.info("onPartitionsRevoked :: {}", collection);
        kafkaConsumer.commitSync();
        logger.info("Offsets committed {}:: ", collection);
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> collection) {
        logger.info("onPartitionsAssigned :: {}", collection);
        //kafkaConsumer.seekToBeginning(collection);

        Map<TopicPartition, OffsetAndMetadata> offsetMap = readOffsetSerializationFile();
        logger.info("OffsetMap :: {}", offsetMap);
        if(!offsetMap.isEmpty()){
            collection.forEach(partition -> {
                kafkaConsumer.seek(partition, offsetMap.get(partition));
            });
        }
    }
    private static Map<TopicPartition, OffsetAndMetadata> readOffsetSerializationFile()  {
        Map<TopicPartition, OffsetAndMetadata> offsetsMapFromPath = new HashMap<>();
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(serialiaziedFilePath);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            objectInputStream = new ObjectInputStream(bufferedInputStream);
            offsetsMapFromPath = (Map<TopicPartition, OffsetAndMetadata>) objectInputStream.readObject();
            logger.info("Offset Map read from the path is : {} ", offsetsMapFromPath);
        } catch (Exception e) {
            logger.error("Exception Occurred while reading the file : " + e);
        } finally {
            try{
                if (objectInputStream != null)
                    objectInputStream.close();
                if (fileInputStream != null)
                    fileInputStream.close();
                if (bufferedInputStream != null)
                    bufferedInputStream.close();
            }catch (Exception e){
                logger.error("Exception Occurred in closing the exception : " + e);
            }

        }
        return offsetsMapFromPath;
    }
}
