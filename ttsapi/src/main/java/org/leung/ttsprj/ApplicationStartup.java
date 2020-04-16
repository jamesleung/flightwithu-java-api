package org.leung.ttsprj;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApplicationStartup {

    /*
    static final String queueName = "python-test6";

    @Bean
    Queue queue() {
        return new Queue(queueName, true);
    }

     */

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStartup.class, args);
    }
}
