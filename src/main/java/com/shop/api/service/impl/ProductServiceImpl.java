package com.shop.api.service.impl;

import com.shop.api.model.Product;
import com.shop.api.repository.ProductRepository;
import com.shop.api.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Martin Slavov on 01/08/2018.
 */

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		List<Product> list = new ArrayList<>();
		productRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		productRepository.deleteById(id);
	}
	
	@Override
    public Product save(Product product) {
				
//		Map<String, LocalizedTextEntity> text = new HashMap<>();	
//		Product productNew = new Product();
//		productNew.setQuantity(product.getQuantity());
//		productNew.setEnabled(product.getEnabled());
//		text.putAll( product.getText());
//		return productRepository.save(productNew);
		
		return productRepository.save(product);
    }

	@Override
	public Product findById(Long id) {
		return productRepository.findById(id).get();
	}
	
	@Override
	public int disableEnableProduct(long id, boolean enabled) {
		return productRepository.disableEnableProduct(id, enabled);
	}

	@Override
	public List<Product> findAllActiveProduct() {
		return productRepository.findAllActiveProduct();
	}

	@Override
	public List<Product> findAllActiveProductById(int id) {
		return productRepository.findAllActiveProductById(id);
	}
	
	@Override
	public List<Product> findAllProductById(int id) {
		return productRepository.findAllProductById(id);
	}

	@Override
	public Product findProductByName(String name) {
		return productRepository.findProductByName(name);
	}
}
