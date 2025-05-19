package com.dgaznares.inditex.msprices.infrastucture.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureMockMvc
@SpringBootTest
class PriceControllerTest {
	
    @Autowired
    private MockMvc mockMvc;

    private final String url = "/api/prices";

	@Test
	void test1() throws Exception {
		// Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
	    mockMvc.perform(get(url)
	            .param("dateTime", "2020-06-14T10:00:00Z")
	            .param("productId", "35455")
	            .param("brandId", "1"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.productId").value(35455))
	            .andExpect(jsonPath("$.brandId").value(1))
	            .andExpect(jsonPath("$.priceList").value(1))
	            .andExpect(jsonPath("$.price").value(35.50));
	}
	
	@Test
	void test2() throws Exception {
		//Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	    mockMvc.perform(get(url)
	            .param("dateTime", "2020-06-14T16:00:00Z")
	            .param("productId", "35455")
	            .param("brandId", "1"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.productId").value(35455))
	            .andExpect(jsonPath("$.brandId").value(1))
	            .andExpect(jsonPath("$.priceList").value(2))
	            .andExpect(jsonPath("$.price").value(25.45));
	}

	
	@Test
	void test3() throws Exception {
		// Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA
	    mockMvc.perform(get(url)
	            .param("dateTime", "2020-06-14T21:00:00Z")
	            .param("productId", "35455")
	            .param("brandId", "1"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.productId").value(35455))
	            .andExpect(jsonPath("$.brandId").value(1))
	            .andExpect(jsonPath("$.priceList").value(1))
	            .andExpect(jsonPath("$.price").value(35.50));
	}

	@Test
	void test4() throws Exception {
		// Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
	    mockMvc.perform(get(url)
	            .param("dateTime", "2020-06-15T10:00:00Z")
	            .param("productId", "35455")
	            .param("brandId", "1"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.productId").value(35455))
	            .andExpect(jsonPath("$.brandId").value(1))
	            .andExpect(jsonPath("$.priceList").value(1))
	            .andExpect(jsonPath("$.price").value(35.50));
	}
	@Test
	void test5() throws Exception {
		// Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
	    mockMvc.perform(get(url)
	            .param("dateTime", "2020-06-16T21:00:00Z")
	            .param("productId", "35455")
	            .param("brandId", "1"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.productId").value(35455))
	            .andExpect(jsonPath("$.brandId").value(1))
	            .andExpect(jsonPath("$.priceList").value(4))
	            .andExpect(jsonPath("$.price").value(38.95));
	}
	@Test
	void test6() throws Exception {
		// Test 6: petición a las 21:00 del día 16 del 2021 del producto 35455   para la brand 1 (ZARA)
		// El test no encontrará precio alguno
	    mockMvc.perform(get(url)
	            .param("dateTime", "2021-06-16T21:00:00Z")
	            .param("productId", "35455")
	            .param("brandId", "1"))
	            .andExpect(status().isNotFound());
	}
	@Test
	void test7() throws Exception {
		// Test 7: Error en formato de fecha. Unespected Error.
	    mockMvc.perform(get(url)
	            .param("dateTime", "2021-06-16T21:00:00")
	            .param("productId", "35455")
	            .param("brandId", "1"))
	            .andExpect(status().isInternalServerError());
	}
	
}
