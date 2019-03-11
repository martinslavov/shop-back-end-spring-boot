package com.shop.api.service.impl;

import com.shop.api.model.Category;
import com.shop.api.model.CategoryOnly;
import com.shop.api.repository.CategoryRepository;
import com.shop.api.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> findAll() {
		List<Category> list = new ArrayList<>();
		categoryRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public Category findById(Long id) {	
		return categoryRepository.findById(id).get();
	}

	@Override
    public Category save(Category category) {
		 return categoryRepository.save(category);
    }

	@Override
	public Category findOne(String categoryName) {
		return null;
	}

	@Override
	public List<Category> findAllActiveCategory() {
		return categoryRepository.findAllActiveCategory();
	}

	@Override
	public List<CategoryOnly> findAllCategoryOnly() {
		return categoryRepository.findAllCategoryOnly();
	}

	@Override
	public int disableEnableCategory(long id, boolean enabled) {
		return categoryRepository.disableEnableCategory(id, enabled);
	}

	@Override
	public Category findByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public Boolean existsById(Long id) {
		return categoryRepository.existsById(id);
	}
}
