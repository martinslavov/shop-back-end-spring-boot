package com.shop.api.service.impl;

import com.shop.api.model.Product;
import com.shop.api.repository.ProductRepository;
import com.shop.api.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * The Class ProductServiceImpl.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
@Service(value = "productService")
public class ProductServiceImpl implements ProductService {
	
	/** The product repository. */
	@Autowired
	private ProductRepository productRepository;

	/* (non-Javadoc)
	 * @see com.shop.api.service.ProductService#findAll()
	 */
	public List<Product> findAll() {
		List<Product> list = new ArrayList<>();
		productRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.shop.api.service.ProductService#delete(long)
	 */
	@Override
	public void delete(long id) {
		productRepository.deleteById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.shop.api.service.ProductService#save(com.shop.api.model.Product)
	 */
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

	/* (non-Javadoc)
	 * @see com.shop.api.service.ProductService#findById(java.lang.Long)
	 */
	@Override
	public Product findById(Long id) {
		return productRepository.findById(id).get();
	}
	
	/* (non-Javadoc)
	 * @see com.shop.api.service.ProductService#disableEnableProduct(long, boolean)
	 */
	@Override
	public int disableEnableProduct(long id, boolean enabled) {
		return productRepository.disableEnableProduct(id, enabled);
	}

	/* (non-Javadoc)
	 * @see com.shop.api.service.ProductService#findAllActiveProduct()
	 */
	@Override
	public List<Product> findAllActiveProduct() {
		return productRepository.findAllActiveProduct();
	}

	/* (non-Javadoc)
	 * @see com.shop.api.service.ProductService#findAllActiveProductById(int)
	 */
	@Override
	public List<Product> findAllActiveProductById(int id) {
		return productRepository.findAllActiveProductById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.shop.api.service.ProductService#findAllProductById(int)
	 */
	@Override
	public List<Product> findAllProductById(int id) {
		return productRepository.findAllProductById(id);
	}

	/* (non-Javadoc)
	 * @see com.shop.api.service.ProductService#findProductByName(java.lang.String)
	 */
	@Override
	public Product findProductByName(String name) {
		return productRepository.findProductByName(name);
	}
}
