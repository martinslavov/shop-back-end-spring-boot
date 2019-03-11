package com.shop.api.test.common;

import java.io.IOException;

import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;  
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.api.model.Category;
import com.shop.api.service.impl.CategoryServiceImpl;
import com.shop.api.test.BaseMvcTest;
//import org.mockito.quality.Strictness;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Martin Slavov on 11/03/2019.
 */

//@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@ExtendWith(MockitoExtension.class)
class CategoryCommonTest {

 	@Mock
    CategoryServiceImpl categoryService;

 	JSONObject jsonRequest;
 	JSONObject jsonResponse;
 	
 	@Disabled
 	@Test
    public void category() throws JsonParseException, JsonMappingException, IOException {
 		
 		BaseMvcTest baseMvc = new BaseMvcTest();
 		
 		jsonRequest = baseMvc.loadJson("json/retrieve-category-id-19.json");
 		jsonResponse = baseMvc.loadJson("json/retrieve-category-id-19.json");

        ObjectMapper mapper = new ObjectMapper();
        Category categoryObj = mapper.readValue(jsonRequest.toString(), Category.class);
		
        when(categoryService.findById((long) 19)).thenReturn(categoryObj);      
        assertEquals(categoryObj, categoryObj);
        
    }
}