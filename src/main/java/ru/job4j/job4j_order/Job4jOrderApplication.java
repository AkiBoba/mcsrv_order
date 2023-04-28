package ru.job4j.job4j_order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.client.RestTemplate;

import static java.lang.System.out;

@SpringBootApplication
@Configuration
@EnableKafka
public class Job4jOrderApplication {

    @Bean
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Job4jOrderApplication.class, args);
        out.println("Order app run");
    }

}
