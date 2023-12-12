package com.yash.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yash.productservice.dto.ProductRequestDTO;
import com.yash.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	ProductRepository productRepository;

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer(
			"mongo:4.4.2"
	);

	static {
		mongoDBContainer.start();
	}

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry){
		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);

	}

	@Test
	void shouldCreateProduct() throws Exception {

		ProductRequestDTO productRequestDTO = ProductRequestDTO.builder().name("iphone 14").description("best rate").
				price(BigDecimal.valueOf(89000.00)).build();

		mock.perform(MockMvcRequestBuilders.post("/api/v1/product/createProduct").contentType(
				MediaType.APPLICATION_JSON
		). content(
			objectMapper.writeValueAsString(productRequestDTO)
		)).andExpect(status().isCreated());
		//Assertions.assertEquals(1, productRepository.findAll().size() );
	}

	@Test
	void shouldHaveProducts() throws Exception{
		mock.perform(MockMvcRequestBuilders.get("/api/v1/product/getProducts").contentType(MediaType.APPLICATION_JSON)).
				andDo(print()).andExpect(status().isOk());
	}

}
