package com.shop.api.service;

import java.util.List;

import com.shop.api.model.Category;
import com.shop.api.model.CategoryOnly;

/**
 * The Interface CategoryService.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
public interface CategoryService {

	/**
	 * Save.
	 *
	 * @param category the category
	 * @return the category
	 */
	Category save(Category category);
	
	/**
	 * Exists by id.
	 *
	 * @param the id
	 * @return the boolean
	 */
	Boolean existsById(Long id);
    
    /**
     * Find all.
     *
     * @return the list
     */
    List<Category> findAll();
    
    /**
     * Delete.
     *
     * @param the id
     */
    void delete(long id);
    
    /**
     * Find one.
     *
     * @param categoryName the category name
     * @return the category
     */
    Category findOne(String categoryName);
    
    /**
     * Find by id.
     *
     * @param the id
     * @return the category
     */
    Category findById(Long id);
    
    /**
     * Find all active category.
     *
     * @return the list
     */
    List<Category>findAllActiveCategory();
	
	/**
	 * Find all category only.
	 *
	 * @return the list
	 */
	List<CategoryOnly>findAllCategoryOnly();
	
	/**
	 * Disable enable category.
	 *
	 * @param the id
	 * @param enabled the enabled
	 * @return the int
	 */
	int disableEnableCategory(long id, boolean enabled);
	
	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the category
	 */
	Category findByName(String name);
	
}
