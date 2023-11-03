package dev.concat.vab.productservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
public class JacksonObjectMapperConfiguration {
    @Bean
    public ObjectMapper testcontainersObjectMapper() {
        return new ObjectMapper();
    }
}
