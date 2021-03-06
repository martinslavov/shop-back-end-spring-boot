package com.shop.api.test;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Properties;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.api.controller.AuthenticationController;
import com.shop.api.model.LoginUser;

import org.springframework.web.context.WebApplicationContext;

/**
 * The Class BaseMvcTest.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2019-03-13
 */
public class BaseMvcTest {
	
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseMvcTest.class);

    /** The Constant LOGIN. */
    protected static final String LOGIN = "/api/token/generate-token";
    
    /** The Constant TEST_USERNAME. */
    protected static final String TEST_USERNAME = "test.username";
    
    /** The Constant TEST_PASSWORD. */
    protected static final String TEST_PASSWORD = "test.password";

    /** The mock mvc. */
    protected static MockMvc mockMvc;
    
    /** The conf props. */
    protected static Properties confProps;
    
    /** The token. */
    protected static String token;
    
    /** The headers. */
    protected static HttpHeaders headers = new HttpHeaders();
    
    /** The rest template. */
    protected static TestRestTemplate restTemplate = new TestRestTemplate();
    
    /** The аuthentication. */
    @Autowired
    public AuthenticationController аuthentication;
    
    /** The wac. */
    @Autowired
    private WebApplicationContext wac;

    /**
     * Gets the wac.
     *
     * @return the wac
     */
    protected WebApplicationContext getWac() {
        return wac;
    }
    
    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @BeforeAll
	public void setUp() throws Exception {
    	LOGGER.info("Start setup");
		configureProperties();
		performLogin();
		buildHeader();
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }
    
	/**
	 * Builds the header.
	 *
	 * @throws Exception the exception
	 */
	public void buildHeader() throws Exception {
		headers.add("Authorization", "Bearer "  + createHttpAuthenticationHeaderValue());
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
	}
	
	/**
	 * Creates the http authentication header value.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
	private String createHttpAuthenticationHeaderValue() throws Exception {		
		return token;
	}

	
	/**
	 * Perform login.
	 *
	 * @throws Exception the exception
	 */
	protected void performLogin() throws Exception {
		mockMvc = standaloneSetup(this.аuthentication).build();
		
		LoginUser loginUser = new LoginUser();
		loginUser.setUsername(confProps.getProperty(TEST_USERNAME));
		loginUser.setPassword(confProps.getProperty(TEST_PASSWORD));
		
		ResultActions resultAction = mockMvc.perform(
		        postWithData(LOGIN, loginUser));
		
		MvcResult mvcResult = resultAction.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		
		JSONObject jsonObject = new JSONObject(response);
		token = jsonObject.getString("token");
	 
		LOGGER.info("Token: " + token);
        System.out.println("Token: " + token);
	}
	
    /**
     * Configure properties.
     */
    protected void configureProperties() {
        confProps = new Properties();
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(
            "application-test.properties")) {
            confProps.load(is);
            LOGGER.info("Finish loading test.properties");
        } catch (Exception e) {
            LOGGER.error("Error loading test.properties", e);
        }
    }
    
    /**
     * Load json.
     *
     * @param resourcePath the resource path
     * @return the JSON object
     */
    public JSONObject loadJson(String resourcePath) {
    	InputStream inputStream = null;
    	JSONObject json = null;
        try {
            inputStream = getClass().getClassLoader()
    			.getResourceAsStream(resourcePath);

            if (inputStream != null) {
                BufferedReader streamReader = new BufferedReader(
    				new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);
                json = new JSONObject(responseStrBuilder.toString());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
        	LOGGER.error("Exception", e);
        	System.out.println("Exception: " + e);
    	}
        return json;
    }

    /**
     * Post with data.
     *
     * @param url the url
     * @param body the body
     * @return the mock http servlet request builder
     * @throws JsonProcessingException the json processing exception
     */
    protected MockHttpServletRequestBuilder postWithData(String url, Object body)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(mapper.writeValueAsString(body));
    }
    
//    protected ResponseEntity<String> getWithDataEntity(String url) throws RestClientException, Exception{
//		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
//    	
//		ResponseEntity<String> response = restTemplate.exchange(
//				createURLWithPort(url),
//				HttpMethod.GET, entity, String.class);
//     
//		return response;
//    }
//    
//    protected ResponseEntity<String> postWithDataEntity(String url, String content) throws RestClientException, Exception{
//    	
//    	String category = loadJson("json/" + content).toString();	
//		HttpEntity<String> entity = new HttpEntity<String>(category, headers);
//
//		ResponseEntity<String> response = restTemplate.exchange(
//				createURLWithPort(url),
//				HttpMethod.POST, entity, String.class);
//     
//		return response;
//    }
    
    /**
 * Gets the with token.
 *
 * @param url the url
 * @return the with token
 * @throws JsonProcessingException the json processing exception
 */
protected MockHttpServletRequestBuilder getWithToken(String url)
            throws JsonProcessingException {
    	
    	MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url)
        		.headers(headers)
        		.contentType(MediaType.APPLICATION_JSON);
    	 LOGGER.info("URL: " + url);
         return builder;
    }
    
    /**
     * Post with data and token.
     *
     * @param url the url
     * @param content the content
     * @return the mock http servlet request builder
     * @throws JsonProcessingException the json processing exception
     */
    protected MockHttpServletRequestBuilder postWithDataAndToken(String url, String content)
            throws JsonProcessingException {
    	
    	String contentString = loadJson("json/" + content).toString();	    	
    	
    	return MockMvcRequestBuilders.post(url)
        		.headers(headers)
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(contentString);
    }
    
    /**
     * Delete with data and token.
     *
     * @param url the url
     * @return the mock http servlet request builder
     * @throws JsonProcessingException the json processing exception
     */
    protected MockHttpServletRequestBuilder deleteWithDataAndToken(String url)
            throws JsonProcessingException {  	
    	
    	MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete(url)
        		.headers(headers)
        		.contentType(MediaType.APPLICATION_JSON);
         LOGGER.info("URL: " + url);
         LOGGER.info("Token : " + token);
         LOGGER.info("Headers : " + headers);
         return builder;
    }

}