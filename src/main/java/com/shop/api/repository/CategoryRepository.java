package com.shop.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.api.model.Category;
import com.shop.api.model.CategoryOnly;

/**
 * Created by Martin Slavov on 01/08/2018.
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	 List<Category> findAll();
	 Category findById(long id);
	 
	 @Query("SELECT c FROM Category AS c")
	 List<CategoryOnly> findAllCategoryOnly();
	
	 @Query("SELECT c FROM Category AS c")
	 List<Category> findAllCategory();
	 
	 @Query(nativeQuery = true, value = "SELECT * FROM categories c \n" + 
			 "JOIN i18n_categories ON c.id = i18n_categories.text_id\n" + 
			 "JOIN i18n ON i18n_categories.i18n_id = i18n.id\n" + 
			 "WHERE c.active = 1 AND \n" + 
			 "i18n.locale = \"en\"")
	 List<Category> findAllActiveCategory();

	 @Transactional
	 @Modifying
	 @Query(nativeQuery = true, value = "UPDATE categories c SET c.active = :enabled WHERE c.id = :id")
//	 @Query( value = "UPDATE Category c SET c.enabled = true WHERE c.id = 1" )
	 int disableEnableCategory(@Param("id") long id, @Param("enabled") boolean enabled);
	 
	 
	 @Query(nativeQuery = true, value = "SELECT * FROM categories c \n" + 
			 "JOIN i18n_categories ON c.id = i18n_categories.text_id\n" + 
			 "JOIN i18n ON i18n_categories.i18n_id = i18n.id\n" + 
			 "WHERE i18n.title = ?1")
	 Category findByName(String name);
}