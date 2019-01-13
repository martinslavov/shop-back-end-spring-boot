package com.shop.api.service;

import java.util.List;
import com.shop.api.model.Product;

/**
 * Created by Martin Slavov on 01/08/2018.
 */

public interface ProductService {

    Product save(Product producct);
    List<Product> findAll();
    void delete(long id);
    Product findById(Long id);
	int disableEnableProduct(long id, boolean enabled);
	List<Product> findAllActiveProduct();
	
	List<Product> findAllActiveProductById(int id);
	List<Product> findAllProductById(int id);
	Product findProductByName(String name);

}
