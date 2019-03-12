package com.shop.api.controller;

import java.util.Collection;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.api.exception.BadRequestException;
import com.shop.api.exception.EntityNotFoundException;
import com.shop.api.model.Category;
import com.shop.api.model.CategoryOnly;
import com.shop.api.service.CategoryService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

/**
 * The Class CategoryController.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/categories")
public class CategoryController {

	/** The logger. */
	private static Logger logger = Logger.getLogger("CategoryController");
	
	/** The category service. */
	@Autowired
	private CategoryService categoryService;

	/**
	 * Find the categories.
	 *
	 * @return the categories
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@ApiOperation(  value = "Find all categories",
			notes = "getCategories()",
			authorizations = { @Authorization(value="token") 
	})
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getCategories() throws EntityNotFoundException {
		logger.info("Start get all category");
		try{
			logger.info("End get all category");
			return new ResponseEntity<List<Category>>(categoryService.findAll(), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get all category");
			throw new EntityNotFoundException(Category.class, "", "Can't get all category");
		}	
	}
	
	/**
	 * Find the all categories only without auth.
	 *
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@ApiOperation(  value = "Find the all categories only without auth",
			notes = "getCategoriesOnlyWithoutAuth()")
	@RequestMapping(value = "/only-noauth", method = RequestMethod.GET)
	public ResponseEntity<Collection<CategoryOnly>> getCategoriesOnlyWithoutAuth() throws EntityNotFoundException {
		logger.info("Start get all active category only without auth");
		try{
			logger.info("End get all active category only without auth");
			return new ResponseEntity<>(categoryService.findAllCategoryOnly(), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get all active category only without auth");
			throw new EntityNotFoundException(Category.class, "", "Can't get all active category without auth");
		}
	}
	
	/**
	 * Find all active category only
	 *
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@ApiOperation(  value = "Find all active category only",
			notes = "getCategoriesOnly()",
			authorizations = { @Authorization(value="token") 
	})
	@RequestMapping(value = "/only", method = RequestMethod.GET)
	public ResponseEntity<Collection<CategoryOnly>> getCategoriesOnly() throws EntityNotFoundException {
		logger.info("Start get all active category only");
		try{
			logger.info("End get all active category only");
			return new ResponseEntity<>(categoryService.findAllCategoryOnly(), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get all active category only");
			throw new EntityNotFoundException(Category.class, "", "Can't get all active category");
		}
	}
	
	/**
	 * Find all active categories.
	 *
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@ApiOperation(  value = "Find all active categories",
			notes = "findAllActiveCategory()",
			authorizations = { @Authorization(value="token") 
	})
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public ResponseEntity<Collection<Category>> findAllActiveCategory() throws EntityNotFoundException {
		logger.info("Start get all active categories");
		try{
			logger.info("End get all active categories");
			return new ResponseEntity<>(categoryService.findAllActiveCategory(), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get all active categories");
			throw new EntityNotFoundException(Category.class, "", "Can't get all active categories");
		}
	}

	/**
	 * Find the category by id.
	 *
	 * @param category id
	 * @return the category
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@ApiOperation(value = "Find the category by id",
			notes = "getCategoryById(@PathVariable long id)",
			authorizations = { @Authorization(value="token") 
	})
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> getCategoryById(@PathVariable long id) throws EntityNotFoundException {
		logger.info("Start get category by id");
		try{
			logger.info("End get category by id");
			Category category = categoryService.findById(id);
			if(categoryService.findById(id) == null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			return new ResponseEntity<>(category, HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get category by id");
			throw new EntityNotFoundException(Category.class, "id", Long.toString(id));
		}
	}

	/**
	 * Adds the category.
	 *
	 * @param category
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws BadRequestException the bad request exception
	 */
	@ApiOperation(value = "Adds the category",
			  notes = "addCategory(@RequestBody Category category)",
			  authorizations = { @Authorization(value="token") 
	})
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addCategory(@RequestBody Category category) throws EntityNotFoundException, BadRequestException {
		logger.info("Start inserting Category");
		try{
			logger.info("End inserting Category");
    		return new ResponseEntity<Category>(categoryService.save(category), HttpStatus.CREATED);
    	}catch(Exception ex){
		    ex.printStackTrace();
		    logger.error("Can't insert Category" + ex);
			logger.fatal("Can't insert Category" + ex);
			throw new BadRequestException(Category.class, "params", category.toString());
		}
	}

	/**
	 * Delete category.
	 *
	 * @param category id
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@ApiOperation(value = "Delete category by id",
			  notes = "deleteCategory(@PathVariable long id)",
			  authorizations = { @Authorization(value="token") 
	})
    @PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCategory(@PathVariable long id) throws EntityNotFoundException {
		logger.info("Start removing category by id: " + id);
		if(categoryService.findById(id) == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		try{
			categoryService.delete(id);
			logger.info("End removing category by id: " + id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(Exception ex){
			logger.info("Can't removing category by id: " + id);
			throw new EntityNotFoundException(Category.class, "id", Long.toString(id));
		}
	}
		
	/**
	 * Update category.
	 *
	 * @param category
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws BadRequestException the bad request exception
	 */
	@ApiOperation(value = "Update category",
			  notes = "updateCategory(@RequestBody Category category)",
			  authorizations = { @Authorization(value="token") 
	})
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updateCategory(@RequestBody Category category) throws EntityNotFoundException, BadRequestException {
		
		if (Long.valueOf(category.getId()) == null || Long.valueOf(category.getId()) == 0) 
			throw new BadRequestException(Category.class, "params", "missing category id " + category.getId());
		
		if(!categoryService.existsById(Long.valueOf(category.getId())))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
				
		logger.info("Start update Category");
		try{
			logger.info("End update Category");
    		return new ResponseEntity<Category>(categoryService.save(category), HttpStatus.OK);
    	}catch(Exception ex){
		    ex.printStackTrace();
		    logger.error("Can't update Category" + ex);
			logger.fatal("Can't update Category" + ex);
			throw new BadRequestException(Category.class, "params", category.toString());
		}
	}
	
	/**
	 * Disable and enable category.
	 *
	 * @param category
	 * @return the int
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws BadRequestException the bad request exception
	 */
	@ApiOperation(value = "Update category by id",
			  notes = "updateCategory(@RequestBody Category category)",
			  authorizations = { @Authorization(value="token") 
	})
	@RequestMapping(value = "/{id}/disable-enable", method = RequestMethod.PUT)
	public int disableEnableCategory(@RequestBody Category category) throws EntityNotFoundException, BadRequestException {
		logger.info("Start update Category");
		if (Long.valueOf(category.getId()) == null || Long.valueOf(category.getId()) == 0) 
			throw new BadRequestException(Category.class, "params", "missing category id");	
		
		try{
			logger.info("End update Category");
			return categoryService.disableEnableCategory(category.getId(), category.getEnabled());
		}catch(Exception ex){
		    logger.error("Can't update Category" + ex);
			logger.fatal("Can't update Category" + ex);
			throw new BadRequestException(Category.class, "params", "missing category id");	
		}
	}

	/**
	 *  Find the products by category id.
	 *
	 * @param category id
	 * @return the response entity - products
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@ApiOperation(value = "Find the products by category id",
			  notes = "getProductsByCategoryId(@PathVariable long id)",
			  authorizations = { @Authorization(value="token") 
	})
	@RequestMapping(value = "/{id}/products", method = RequestMethod.GET)
	public ResponseEntity<?> getProductsByCategoryId(@PathVariable long id) throws EntityNotFoundException {		
		Category category = categoryService.findById(id);

		logger.info("Start find all products by category id: " + id);
		try{
			logger.info("End find all products by category id: " + id);
			return new ResponseEntity<>(category.getProduct(), HttpStatus.OK);
		}catch(Exception ex){
			logger.info("Can't find all products by category id: " + id);
			throw new EntityNotFoundException(Category.class, "id", Long.toString(id));
		}
	}
	
	/**
	 * Find category by name.
	 *
	 * @param name of the category
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@ApiOperation(value = "Find category by name",
			  notes = "getCategoryByName(@PathVariable String name)",
			  authorizations = { @Authorization(value="token") 
	})
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<Category> getCategoryByName(@PathVariable String name) throws EntityNotFoundException {
		logger.info("Start get category");
		try{
			logger.info("End get category");
			return new ResponseEntity<>(categoryService.findByName(name), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get category");
			throw new EntityNotFoundException(Category.class, "", "Can't get category");
		}
	}
		
	/**
	 * Find the category by id without authentication.
	 *
	 * @param category id
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@ApiOperation(  value = "Find the category by id without authentication",
			notes = "getCategoryWithoutAuth()")
	@RequestMapping(value = "/no-auth/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCategoryWithoutAuth(@PathVariable long id) throws EntityNotFoundException {
		logger.info("Start get category by id");
		try{
			logger.info("End get category by id");
			return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get category by id");
			throw new EntityNotFoundException(Category.class, "id", Long.toString(id));
		}
	}	
}