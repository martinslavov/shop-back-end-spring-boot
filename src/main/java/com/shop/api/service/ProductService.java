package com.shop.api.service;

import java.util.List;
import com.shop.api.model.Product;

/**
 * The Interface ProductService.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
public interface ProductService {

    /**
     * Save.
     *
     * @param the producct
     * @return the product
     */
    Product save(Product producct);
    
    /**
     * Find all.
     *
     * @return the list
     */
    List<Product> findAll();
    
    /**
     * Delete.
     *
     * @param the id
     */
    void delete(long id);
    
    /**
     * Find by id.
     *
     * @param the id
     * @return the product
     */
    Product findById(Long id);
	
	/**
	 * Disable enable product.
	 *
	 * @param the id
	 * @param enabled the enabled
	 * @return the int
	 */
	int disableEnableProduct(long id, boolean enabled);
	
	/**
	 * Find all active product.
	 *
	 * @return the list
	 */
	List<Product> findAllActiveProduct();
	
	/**
	 * Find all active product by id.
	 *
	 * @param the id
	 * @return the list
	 */
	List<Product> findAllActiveProductById(int id);
	
	/**
	 * Find all product by id.
	 *
	 * @param the id
	 * @return the list
	 */
	List<Product> findAllProductById(int id);
	
	/**
	 * Find product by name.
	 *
	 * @param the name
	 * @return the product
	 */
	Product findProductByName(String name);

}
