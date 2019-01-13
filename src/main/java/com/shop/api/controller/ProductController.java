package com.shop.api.controller;

import java.util.Collection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.api.exception.BadRequestException;
import com.shop.api.exception.EntityNotFoundException;
import com.shop.api.model.Category;
import com.shop.api.model.Product;
import com.shop.api.service.CategoryService;
import com.shop.api.service.ProductService;

/**
 * Created by Martin Slavov on 01/08/2018.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/products")
public class ProductController {
	
	private static Logger logger = Logger.getLogger("ProductController");

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
		
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Product>> getProducts() throws EntityNotFoundException {
		logger.info("Start get all products");
		try{
			logger.info("End get all products");
			return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get all products");
			throw new EntityNotFoundException(Product.class, "", "Can't get all products");
		}	
	}
	
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public ResponseEntity<Collection<Product>> findAllActiveProduct() throws EntityNotFoundException {
		logger.info("Start get all active product");
		try{
			logger.info("End get all active product");
			return new ResponseEntity<>(productService.findAllActiveProduct(), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get all active product");
			throw new EntityNotFoundException(Product.class, "", "Can't get all active product");
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProduct(@PathVariable long id) throws EntityNotFoundException {
		logger.info("Start get product by id");
		try{
			logger.info("End get product by id");
			return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get product by id");
			throw new EntityNotFoundException(Product.class, "id", Long.toString(id));
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Product> addProduct(@RequestBody Product product, @RequestParam String categoryName) throws BadRequestException {   
		logger.info("Start inserting Product");
		try{
			logger.info("End inserting Product");
			
			
			String parseCategoryName = categoryName.replaceAll("-", " ").replaceAll("and", "&"); 
			Product productSaved = productService.save(product);		
			productSaved.getId();
			System.out.println(parseCategoryName);

			Category category = categoryService.findByName(parseCategoryName);
			category.getProduct().add(productSaved);
			categoryService.save(category);
			
			
			return new ResponseEntity<Product>(productSaved, HttpStatus.CREATED);
    	}catch(Exception ex){
		    ex.printStackTrace();
		    logger.error("Can't insert Product" + ex);
			logger.fatal("Can't insert Product" + ex);
			throw new BadRequestException(Product.class, "params", product.toString());
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @RequestParam String categoryName) throws BadRequestException {
		
		if (Long.valueOf(product.getId()) == null || Long.valueOf(product.getId()) == 0) 
			throw new BadRequestException(Category.class, "params", "missing product id");
		
		logger.info("Start inserting Product");
		try{
			logger.info("End inserting Product");
			
			String parseCategoryName = categoryName.replaceAll("-", " ").replaceAll("and", "&"); 
			Product productSaved = productService.save(product);		
			productSaved.getId();
			System.out.println(parseCategoryName);

			Category category = categoryService.findByName(parseCategoryName);
			category.getProduct().add(productSaved);
			categoryService.save(category);
			
			return new ResponseEntity<Product>(productSaved, HttpStatus.OK); 
    	}catch(Exception ex){
		    ex.printStackTrace();
		    logger.error("Can't insert Product" + ex);
			logger.fatal("Can't insert Product" + ex);
			throw new BadRequestException(Product.class, "params", product.toString());
		}
	}

    @PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProduct(@PathVariable long id) throws EntityNotFoundException {
		logger.info("Start removing product by id: " + id);
		try{
			productService.delete(id);
			logger.info("End removing product by id: " + id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(Exception ex){
			logger.info("Can't removing product by id: " + id);
			throw new EntityNotFoundException(Product.class, "id", Long.toString(id));
		}
	}
	
	@RequestMapping(value = "/{id}/disable-enable", method = RequestMethod.PUT)
	public int disableEnableCategory(@RequestBody Product product) throws EntityNotFoundException, BadRequestException {
		logger.info("Start update Product");
		if (Long.valueOf(product.getId()) == null || Long.valueOf(product.getId()) == 0) 
			throw new BadRequestException(Category.class, "params", "missing category id");	
		
		try{
			logger.info("End update Product");
			return productService.disableEnableProduct(product.getId(), product.getEnabled());
		}catch(Exception ex){
		    logger.error("Can't update Product" + ex);
			logger.fatal("Can't update Product" + ex);
			throw new BadRequestException(Product.class, "params", "missing product id or s");	
		}
	}
	
	@RequestMapping(value = "/{id}/categories", method = RequestMethod.GET)
	public ResponseEntity<?> getProductCategories(@PathVariable long id) throws EntityNotFoundException {
		Product product = productService.findById(id);

		logger.info("Start find all categories by product id: " + id);
		try{
			logger.info("End find all categories by product id: " + id);
			return new ResponseEntity<>(product.getCategories(), HttpStatus.OK);
		}catch(Exception ex){
			logger.info("Can't find all categories by product id: " + id);
			throw new EntityNotFoundException(Product.class, "id", Long.toString(id));
		}
	}
	
	@RequestMapping(value = "/category-name/{categoryName}", method = RequestMethod.GET)
	public ResponseEntity<Collection<Product>> findByCategoryName(@PathVariable String categoryName) throws EntityNotFoundException {
		
		Category category = categoryService.findByName(categoryName);

		logger.info("Start get all active product");
		try{
			logger.info("End get all active product");
			return new ResponseEntity<>(productService.findAllActiveProductById((int)category.getId()), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get all active product");
			throw new EntityNotFoundException(Product.class, "", "Can't get all active product");
		}
	}
	
	@RequestMapping(value = "/category-name/all/{categoryName}", method = RequestMethod.GET)
	public ResponseEntity<Collection<Product>> findAllByCategoryName(@PathVariable String categoryName) throws EntityNotFoundException {
		
		Category category = categoryService.findByName(categoryName);

		logger.info("Start get all active product");
		try{
			logger.info("End get all active product");
			return new ResponseEntity<>(productService.findAllProductById((int)category.getId()), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get all active product");
			throw new EntityNotFoundException(Product.class, "", "Can't get all active product");
		}
	}
	
	@RequestMapping(value = "/product-name/{productName}", method = RequestMethod.GET)
	public ResponseEntity<Product> findByProductName(@PathVariable String productName) throws EntityNotFoundException {
		

		logger.info("Start get all active product");
		try{
			logger.info("End get all active product");
			return new ResponseEntity<Product>(productService.findProductByName(productName), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get all active product");
			throw new EntityNotFoundException(Product.class, "", "Can't get all active product");
		}
	}

}
