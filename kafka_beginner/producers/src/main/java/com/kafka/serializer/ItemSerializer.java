package com.kafka.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.domain.Item;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemSerializer implements Serializer {
    private static final Logger logger = LoggerFactory.getLogger(ItemSerializer.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, Object item){
        logger.info("Inside serialization logic");
        try {
            return  objectMapper.writeValueAsBytes(item);
        } catch (JsonProcessingException e) {
            logger.error("Error is serialization :: {}", e);
            throw new RuntimeException(e);
        }

    }

}
