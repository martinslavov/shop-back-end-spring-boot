package com.shop.api.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shop.api.model.Category;
import com.shop.api.service.CategoryService;
import com.shop.api.test.BaseMvcTest;
import com.shop.api.test.utils.LifecycleLoggerWithTestInstance;
import com.shop.api.test.utils.TestUtils;

/**
 * Created by Martin Slavov on 11/03/2019.
 */

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("CategoryController - Integration Test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CategoryControllerIT extends BaseMvcTest
						implements LifecycleLoggerWithTestInstance {
	
	@Mock
	CategoryService categoryService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryControllerIT.class);
	
	private static String id;
	private static final String CATEGORY = "/api/categories";	
	
	@Test
    @Disabled
    @Order(1)
    @RepeatedTest(1)
    @DisplayName("Start categories unit test")
    void startCategoriesTest() {
        System.out.println("Start categories unit test");
    }
	
    @Test
    @Order(2)
    @RepeatedTest(10)
    @DisplayName("Get all categories")
	public void retrieveCategory() throws RestClientException, Exception {
		
//			//Get date from database
//			 ResponseEntity<String> response = getWithDataEntity(CATEGORY + "/" + id);		
//			 String category = loadJson("json/retrieve-category-id-19.json").toString();	
//			 JSONAssert.assertEquals(category, response.getBody(), false);
    	 int randomNum = ThreadLocalRandom.current().nextInt(1, 10);
    	 LOGGER.info("Get category with id: " + randomNum);
		 try {
			 mockMvc.perform( getWithToken(CATEGORY + "/" + randomNum) )
		        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		    	.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
		    LOGGER.error("Error", e);
		    Assert.fail("failed by:" + e.getMessage());
		}
		LOGGER.info("Retrieve category finish succesfully");
	}
		
    @Test
    @Order(3)
    @DisplayName("Create category")
    public void createCategory() {
        try {
        	ResultActions resultActions =  mockMvc.perform( postWithDataAndToken(CATEGORY, "create-category.json") )
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            	.andExpect(MockMvcResultMatchers.status().isCreated());
        	
        	MvcResult result = resultActions.andReturn();
        	String contentAsString = result.getResponse().getContentAsString();        	
        	JSONObject jsonObj = new JSONObject(contentAsString);
        	id = jsonObj.get("id").toString();
        	
            // verify
            int status = result.getResponse().getStatus();
            assertEquals(HttpStatus.CREATED.value(), status, "Incorrect Response Status");

            // verify that service method was called once
//            verify(categoryService).save(any(Category.class));
            
            Category resultCategory = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Category.class);
            assertNotNull(resultCategory);       	
        	
        	LOGGER.info("Created category with id: " + id);
        } catch (Exception e) {
            LOGGER.error("Error", e);
            Assert.fail("failed by:" + e.getMessage());
        }
    }
    
    @Test
    @Order(4)
    @DisplayName("Delete category")
    public void deleteCategory() { 	
        try {
            mockMvc.perform( deleteWithDataAndToken(CATEGORY + "/delete/" + id) )
                .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            LOGGER.error("Error", e);
            Assert.fail("failed by:" + e.getMessage());
        }
    }
}
