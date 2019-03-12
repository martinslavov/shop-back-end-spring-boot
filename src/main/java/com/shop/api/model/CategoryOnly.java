package com.shop.api.model;

import java.util.Map;

/**
 * The Interface CategoryOnly.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
public interface CategoryOnly {
	  
  	/**
  	 * Gets the id.
  	 *
  	 * @return the id
  	 */
  	long getId();
	  
  	/**
  	 * Gets the category id.
  	 *
  	 * @return the category id
  	 */
  	int getCategoryId();
	  
  	/**
  	 * Gets the enabled.
  	 *
  	 * @return the enabled
  	 */
  	Boolean getEnabled();
	  
  	/**
  	 * Gets the text.
  	 *
  	 * @return the text
  	 */
  	Map<String, LocalizedTextEntity> getText();
}
