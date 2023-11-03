package dev.concat.vab.productservice;

import dev.concat.vab.productservice.config.JacksonObjectMapperConfiguration;
import dev.concat.vab.productservice.dto.CapProductRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = JacksonObjectMapperConfiguration.class)
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@AllArgsConstructor
public class ProductServiceApplicationTests {


    @Container
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.30");


    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    public ProductServiceApplicationTests(
                                           @Qualifier(value = "testcontainersObjectMapper") ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }
    @Test
    void shouldCreateCapProduct() throws Exception {
        CapProductRequest productRequest = getProductRequest();
        String productRequestString =  objectMapper.writeValueAsString(productRequest);
        System.out.println("productRequestString:\n "+productRequestString);
        mockMvc.perform(MockMvcRequestBuilders.post("http://127.0.0.1:8069/document/v1/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productRequestString))
                .andExpect(status().isCreated());
    }

    private CapProductRequest getProductRequest() {
        return CapProductRequest.builder()
                .id(UUID.randomUUID().toString())
                .name("Iphone 13")
                .description("Iphone 13")
                .price(BigDecimal.valueOf(1200)).build();
    }

}
