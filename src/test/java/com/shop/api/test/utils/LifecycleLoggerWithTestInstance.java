package com.shop.api.test.utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * Created by Martin Slavov on 11/03/2019.
 */

@TestInstance(Lifecycle.PER_CLASS)
public interface LifecycleLoggerWithTestInstance {
    @BeforeAll
    default void initAll() {
    	System.out.println("---Inside initAll---");
    }
    @BeforeEach
    default void init(TestInfo testInfo) {
    	System.out.println("Start..." + testInfo.getDisplayName());
    }	
    @AfterEach
    default void tearDown(TestInfo testInfo) {
    	System.out.println("Finished..." + testInfo.getDisplayName());
    } 
    @AfterAll
    default void tearDownAll() {
    	System.out.println("---Inside tearDownAll---");
    }    
} 
