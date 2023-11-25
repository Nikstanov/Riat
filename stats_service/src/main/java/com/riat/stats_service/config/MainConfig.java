package com.riat.stats_service.config;

import com.riat.stats_service.bean.JsonMessage;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableKafka
@RequiredArgsConstructor
public class MainConfig {

    @Value(value = "${spring.application.name}")
    private String name;

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    @Bean
    public KafkaTemplate<String, JsonMessage> kafkaTemplate() {
        KafkaTemplate<String, JsonMessage> kafkaTemplate = new KafkaTemplate<>(producerFactory());
        kafkaTemplate.setConsumerFactory(consumerFactory());
        return kafkaTemplate;
    }

    @Bean
    public ProducerFactory<String, JsonMessage> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, org.springframework.kafka.support.serializer.JsonSerializer.class);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, name);
        return props;
    }

    @Bean
    public ConsumerFactory<String, JsonMessage> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(JsonMessage.class, false));
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, name);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return props;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, JsonMessage> kafkaListenerContainerFactory() {
        // Позволяет создавать консюмеров, которые могут обрабатывать сообщения из нескольких партиций Kafka одновременно,
        // а также настраивать параметры такие как количество потоков, хэндлинг и т.д.
        ConcurrentKafkaListenerContainerFactory<String, JsonMessage> kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        // Настройка фабрики для создания консьюмера Kafka
        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
        // Обработка сообщений в 4 потока
        kafkaListenerContainerFactory.setConcurrency(4);
        return kafkaListenerContainerFactory;
    }

    @Bean
    public StringJsonMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }
}
