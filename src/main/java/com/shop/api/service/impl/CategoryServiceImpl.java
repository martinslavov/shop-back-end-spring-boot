package com.shop.api.service.impl;

import com.shop.api.model.Category;
import com.shop.api.model.CategoryOnly;
import com.shop.api.repository.CategoryRepository;
import com.shop.api.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * The Class CategoryServiceImpl.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {
	
	/** The category repository. */
	@Autowired
	private CategoryRepository categoryRepository;

	/* (non-Javadoc)
	 * @see com.shop.api.service.CategoryService#findAll()
	 */
	public List<Category> findAll() {
		List<Category> list = new ArrayList<>();
		categoryRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.shop.api.service.CategoryService#delete(long)
	 */
	@Override
	public void delete(long id) {
		categoryRepository.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.shop.api.service.CategoryService#findById(java.lang.Long)
	 */
	@Override
	public Category findById(Long id) {	
		return categoryRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.shop.api.service.CategoryService#save(com.shop.api.model.Category)
	 */
	@Override
    public Category save(Category category) {
		 return categoryRepository.save(category);
    }

	/* (non-Javadoc)
	 * @see com.shop.api.service.CategoryService#findOne(java.lang.String)
	 */
	@Override
	public Category findOne(String categoryName) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shop.api.service.CategoryService#findAllActiveCategory()
	 */
	@Override
	public List<Category> findAllActiveCategory() {
		return categoryRepository.findAllActiveCategory();
	}

	/* (non-Javadoc)
	 * @see com.shop.api.service.CategoryService#findAllCategoryOnly()
	 */
	@Override
	public List<CategoryOnly> findAllCategoryOnly() {
		return categoryRepository.findAllCategoryOnly();
	}

	/* (non-Javadoc)
	 * @see com.shop.api.service.CategoryService#disableEnableCategory(long, boolean)
	 */
	@Override
	public int disableEnableCategory(long id, boolean enabled) {
		return categoryRepository.disableEnableCategory(id, enabled);
	}

	/* (non-Javadoc)
	 * @see com.shop.api.service.CategoryService#findByName(java.lang.String)
	 */
	@Override
	public Category findByName(String name) {
		return categoryRepository.findByName(name);
	}

	/* (non-Javadoc)
	 * @see com.shop.api.service.CategoryService#existsById(java.lang.Long)
	 */
	@Override
	public Boolean existsById(Long id) {
		return categoryRepository.existsById(id);
	}
}
