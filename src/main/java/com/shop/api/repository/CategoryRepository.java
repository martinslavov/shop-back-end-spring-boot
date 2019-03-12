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
 * The Interface CategoryRepository.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01 
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	 /* (non-Javadoc)
 	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
 	 */
 	List<Category> findAll();
	 
 	/**
 	 * Find by id.
 	 *
 	 * @param the id
 	 * @return the category
 	 */
 	Category findById(long id);
	 
	 /**
 	 * Find all category only.
 	 *
 	 * @return the list
 	 */
 	@Query("SELECT c FROM Category AS c")
	 List<CategoryOnly> findAllCategoryOnly();
	
	 /**
 	 * Find all category.
 	 *
 	 * @return the list
 	 */
 	@Query("SELECT c FROM Category AS c")
	 List<Category> findAllCategory();
	 
	 /**
 	 * Find all active category.
 	 *
 	 * @return the list
 	 */
 	@Query(nativeQuery = true, value = "SELECT * FROM categories c \n" + 
			 "JOIN i18n_categories ON c.id = i18n_categories.text_id\n" + 
			 "JOIN i18n ON i18n_categories.i18n_id = i18n.id\n" + 
			 "WHERE c.active = 1 AND \n" + 
			 "i18n.locale = \"en\"")
	 List<Category> findAllActiveCategory();

	 /**
 	 * Disable enable category.
 	 *
 	 * @param the id
 	 * @param the enabled
 	 * @return the int
 	 */
 	 @Transactional
	 @Modifying
	 @Query(nativeQuery = true, value = "UPDATE categories c SET c.active = :enabled WHERE c.id = :id")
	 int disableEnableCategory(@Param("id") long id, @Param("enabled") boolean enabled);
	 
	 
	 /**
 	 * Find by name.
 	 *
 	 * @param the name
 	 * @return the category
 	 */
 	@Query(nativeQuery = true, value = "SELECT * FROM categories c \n" + 
			 "JOIN i18n_categories ON c.id = i18n_categories.text_id\n" + 
			 "JOIN i18n ON i18n_categories.i18n_id = i18n.id\n" + 
			 "WHERE i18n.title = ?1")
	 Category findByName(String name);
}