package com.shop.api.service;

import java.util.List;

import com.shop.api.model.Category;
import com.shop.api.model.CategoryOnly;

/**
 * Created by Martin Slavov on 01/08/2018.
 */

public interface CategoryService {

	Category save(Category category);
	Boolean existsById(Long id);
    List<Category> findAll();
    void delete(long id);
    Category findOne(String categoryName);
    Category findById(Long id);
    List<Category>findAllActiveCategory();
	List<CategoryOnly>findAllCategoryOnly();
	int disableEnableCategory(long id, boolean enabled);
	
	Category findByName(String name);
	
}
