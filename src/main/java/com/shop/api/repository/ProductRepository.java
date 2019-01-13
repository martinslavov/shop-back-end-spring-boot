package com.shop.api.repository;

import com.shop.api.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Martin Slavov on 01/08/2018.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	 List<Product> findAll();
	 Product findById(long id); 
	 
	 @Transactional
	 @Modifying
	 @Query(nativeQuery = true, value = "UPDATE products c SET c.active = :enabled WHERE c.id = :id")
	 int disableEnableProduct(@Param("id") long id, @Param("enabled") boolean enabled);

	 @Query(nativeQuery = true, value = "SELECT * FROM products c \n" + 
//			 "JOIN i18n_categories ON c.id = i18n_categories.text_id\n" + 
//			 "JOIN i18n ON i18n_categories.i18n_id = i18n.id\n" + 
			 "WHERE c.active = 1")
	 List<Product> findAllActiveProduct();
	 
	 @Query(nativeQuery = true, value = "SELECT * FROM products p\n" + 
	 		"JOIN i18n_products ON p.id = i18n_products.text_id\n" + 
	 		"JOIN i18n ON i18n_products.i18n_id = i18n.id\n" + 
	 		"JOIN categories_products ON p.id = categories_products.product_id\n" + 
	 		"JOIN categories ON categories_products.category_id = categories.id\n" + 
	 		"WHERE p.active = 1 AND\n" +
	 		"categories.id = ?1 AND \n" + 
	 		"i18n.locale = \"en\""
	 	   )
	 List<Product> findAllActiveProductById(int id);
	 
	 @Query(nativeQuery = true, value = "SELECT * FROM products p\n" + 
		 		"JOIN i18n_products ON p.id = i18n_products.text_id\n" + 
		 		"JOIN i18n ON i18n_products.i18n_id = i18n.id\n" + 
		 		"JOIN categories_products ON p.id = categories_products.product_id\n" + 
		 		"JOIN categories ON categories_products.category_id = categories.id\n" + 
		 		"WHERE categories.id = ?1 AND \n" + 
		 		"i18n.locale = \"en\""
		 	   )
	List<Product> findAllProductById(int id);
	 
	 @Query(nativeQuery = true, value = "SELECT * FROM products p\n" + 
	 		"	 JOIN i18n_products ON p.id = i18n_products.text_id\n" + 
	 		"	 JOIN i18n ON i18n_products.i18n_id = i18n.id\n" + 
	 		"	 WHERE i18n.title = ?1")
	 Product findProductByName(String name);

}