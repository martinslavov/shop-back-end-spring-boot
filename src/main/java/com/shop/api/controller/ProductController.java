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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

/**
 * The Class ProductController.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/products")
public class ProductController {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger("ProductController");

	/** The product service. */
	@Autowired
	private ProductService productService;
	
	/** The category service. */
	@Autowired
	private CategoryService categoryService;
		
	/**
	 * Find the products.
	 *
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@ApiOperation(value = "Find all products",
			  notes = "getProducts()",
			  authorizations = { @Authorization(value="token") 
	})
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
	
	/**
	 * Find all active product.
	 *
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@ApiOperation(value = "Find all active products",
			  notes = "findAllActiveProduct()",
			  authorizations = { @Authorization(value="token") 
	})
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

	/**
	 * Find the product by id.
	 *
	 * @param id
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@ApiOperation(value = "Find product by id",
			  notes = "getProduct(@PathVariable long id)",
			  authorizations = { @Authorization(value="token") 
	})
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProductById(@PathVariable long id) throws EntityNotFoundException {
		logger.info("Start get product by id");
		try{
			logger.info("End get product by id");
			return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get product by id");
			throw new EntityNotFoundException(Product.class, "id", Long.toString(id));
		}
	}
	
	/**
	 * Adds the product.
	 *
	 * @param the product
	 * @param the category name
	 * @return the response entity
	 * @throws BadRequestException the bad request exception
	 */
	@ApiOperation(value = "Add product",
			  notes = "addProduct(@RequestBody Product product, @RequestParam String categoryName)",
			  authorizations = { @Authorization(value="token") 
	})
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
	
	/**
	 * Update product.
	 *
	 * @param the product
	 * @param the category name
	 * @return the response entity
	 * @throws BadRequestException the bad request exception
	 */
	@ApiOperation(value = "Update product",
			  notes = "updateProduct(@RequestBody Product product)",
			  authorizations = { @Authorization(value="token") 
	})
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @RequestParam String categoryName) throws BadRequestException {
		
		if (Long.valueOf(product.getId()) == null || Long.valueOf(product.getId()) == 0) 
			throw new BadRequestException(Category.class, "params", "missing product id");
		
		logger.info("Start inserting Product");
		try{
			String parseCategoryName = categoryName.replaceAll("-", " ").replaceAll("and", "&"); 
			Product productSaved = productService.save(product);		
			productSaved.getId();
			System.out.println(parseCategoryName);

			Category category = categoryService.findByName(parseCategoryName);
			category.getProduct().add(productSaved);
			categoryService.save(category);
			logger.info("End inserting Product");
			
			return new ResponseEntity<Product>(productSaved, HttpStatus.OK); 
    	}catch(Exception ex){
		    ex.printStackTrace();
		    logger.error("Can't insert Product" + ex);
			logger.fatal("Can't insert Product" + ex);
			throw new BadRequestException(Product.class, "params", product.toString());
		}
	}

	/**
	 * Delete product.
	 *
	 * @param the id
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@ApiOperation(value = "Delete product by id",
			  notes = "deleteProduct(@PathVariable long id)",
			  authorizations = { @Authorization(value="token") 
	})
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
	
	/**
	 * Disable-enable product by category id.
	 *
	 * @param product the product
	 * @return the int
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws BadRequestException the bad request exception
	 */
	@ApiOperation(value = "Disable-enable product by category id",
			  notes = "disableEnableProduct(@RequestBody Product product)",
			  authorizations = { @Authorization(value="token") 
	})
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
	
	/**
	 * Find the product categories.
	 *
	 * @param  the id
	 * @return the product categories
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@ApiOperation(value = "Find all the categories to which a product belongs",
			  notes = "getProductCategories(@PathVariable long id)",
			  authorizations = { @Authorization(value="token") 
	})
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
	
	/**
	 * Find products by category name.
	 *
	 * @param the category name
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@ApiOperation(value = "Find products by category name.",
			  notes = "findProductsByCategoryName(@PathVariable String categoryName)",
			  authorizations = { @Authorization(value="token") 
	})
	@RequestMapping(value = "/category-name/{categoryName}", method = RequestMethod.GET)
	public ResponseEntity<Collection<Product>> findProductsByCategoryName(@PathVariable String categoryName) throws EntityNotFoundException {
		
		Category category = categoryService.findByName(categoryName);

		logger.info("Start get all active products");
		try{
			logger.info("End get all active products");
			return new ResponseEntity<>(productService.findAllActiveProductById((int)category.getId()), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get all active products");
			throw new EntityNotFoundException(Product.class, "", "Can't get all active products");
		}
	}
	
	/**
	 * Find all products by category name.
	 *
	 * @param the category name
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@ApiOperation(value = "Get all products by category name",
			  notes = "findAllProductsByCategoryName(@PathVariable String categoryName) ",
			  authorizations = { @Authorization(value="token") 
	})
	@RequestMapping(value = "/category-name/all/{categoryName}", method = RequestMethod.GET)
	public ResponseEntity<Collection<Product>> findAllProductsByCategoryName(@PathVariable String categoryName) throws EntityNotFoundException {
		
		Category category = categoryService.findByName(categoryName);

		logger.info("Start get all active products");
		try{
			logger.info("End get all active products");
			return new ResponseEntity<>(productService.findAllProductById((int)category.getId()), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get all active products");
			throw new EntityNotFoundException(Product.class, "", "Can't get all active products");
		}
	}
	
	/**
	 * Find by product name.
	 *
	 * @param the product name
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@ApiOperation(value = "Find product by product name",
			  notes = "findByProductName(@PathVariable String productName)",
			  authorizations = { @Authorization(value="token") 
	})
	@RequestMapping(value = "/product-name/{productName}", method = RequestMethod.GET)
	public ResponseEntity<Product> findByProductName(@PathVariable String productName) throws EntityNotFoundException {
		

		logger.info("Start get all active products");
		try{
			logger.info("End get all active products");
			return new ResponseEntity<Product>(productService.findProductByName(productName), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get all active products");
			throw new EntityNotFoundException(Product.class, "", "Can't get all active products");
		}
	}

}
