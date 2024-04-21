package ru.korepanov.gamification.configuration;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class AMQPConfiguration {

    @Bean
    public TopicExchange challengesTopicExchange(@Value("${amqp.exchange.attempts}") final String exchangeName) {
        return ExchangeBuilder
                .topicExchange(exchangeName)
                .durable(true)
                .build();
    }

    @Bean
    public Queue gamificationQueue(@Value("${amqp.queue.gamification}") final String queueName) {
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    public Binding correctAttemotsBinding(final Queue gamificationQueue, final TopicExchange attemptsExchange) {
        return BindingBuilder
                .bind(gamificationQueue)
                .to(attemptsExchange)
                .with("attempt.correct");
    }

    @Bean
    public MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();

        final MappingJackson2MessageConverter jsonConvertor = new MappingJackson2MessageConverter();
        jsonConvertor
                .getObjectMapper()
                .registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));

        factory.setMessageConverter(jsonConvertor);
        return factory;
    }

    @Bean
    public RabbitListenerConfigurer rabbitListenerConfigurer(final MessageHandlerMethodFactory messageHandlerMethodFactory) {
        return c -> c.setMessageHandlerMethodFactory(messageHandlerMethodFactory);
    }
}