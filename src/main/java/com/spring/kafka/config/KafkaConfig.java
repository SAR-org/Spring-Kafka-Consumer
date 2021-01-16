package com.spring.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.spring.kafka.model.Student;

@Configuration
@EnableKafka
public class KafkaConfig {
	
	@Bean
	public ConsumerFactory<String, Student> consumerFactory(){
		Map<String, Object> config = new HashMap<String, Object>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "sar-1");
		config.put(JsonDeserializer.TRUSTED_PACKAGES, "com.spring.kafka.model");
		return new DefaultKafkaConsumerFactory<String, Student>(config);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Student> kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, Student> factory = new ConcurrentKafkaListenerContainerFactory<String, Student>();
		factory.setConsumerFactory(consumerFactory());
		
		return factory;
	}

}
