//package com.example.product.config;
//
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.ProducerListener;
//import org.springframework.kafka.support.converter.RecordMessageConverter;
//
//@Configuration
//public class KafkaTopicConfig {
//
//    private final KafkaProperties kafkaProperties;
//
//    public KafkaTopicConfig(KafkaProperties kafkaProperties) {
//        this.kafkaProperties = kafkaProperties;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(KafkaTemplate.class)
//    public KafkaTemplate<?, ?> kafkaTemplate(ProducerFactory<Object, Object> kafkaProducerFactory,
//                                             ProducerListener<Object, Object> kafkaProducerListener,
//                                             ObjectProvider<RecordMessageConverter> messageConverter) {
//        KafkaTemplate<Object, Object> kafkaTemplate = new KafkaTemplate<>(kafkaProducerFactory);
//        messageConverter.ifUnique(kafkaTemplate::setMessageConverter);
//        kafkaTemplate.setProducerListener(kafkaProducerListener);
//        kafkaTemplate.setDefaultTopic(this.kafkaProperties.getTemplate().getDefaultTopic());
//        return kafkaTemplate;
//    }
//}
//
