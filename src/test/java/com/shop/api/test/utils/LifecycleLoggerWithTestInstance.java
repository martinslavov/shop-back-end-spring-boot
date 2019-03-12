package com.shop.api.test.utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * The Class LifecycleLoggerWithTestInstance.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2019-03-13
 */
@TestInstance(Lifecycle.PER_CLASS)
public interface LifecycleLoggerWithTestInstance {
    
    /**
     * Inits the all.
     */
    @BeforeAll
    default void initAll() {
    	System.out.println("---Inside initAll---");
    }
    
    /**
     * Inits the.
     *
     * @param testInfo the test info
     */
    @BeforeEach
    default void init(TestInfo testInfo) {
    	System.out.println("Start..." + testInfo.getDisplayName());
    }	
    
    /**
     * Tear down.
     *
     * @param testInfo the test info
     */
    @AfterEach
    default void tearDown(TestInfo testInfo) {
    	System.out.println("Finished..." + testInfo.getDisplayName());
    } 
    
    /**
     * Tear down all.
     */
    @AfterAll
    default void tearDownAll() {
    	System.out.println("---Inside tearDownAll---");
    }    
} 
