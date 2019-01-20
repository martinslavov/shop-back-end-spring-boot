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
 * Created by Martin Slavov on 01/08/2018.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/categories")
public class CategoryController {

	private static Logger logger = Logger.getLogger("CategoryController");
	
	@Autowired
	private CategoryService categoryService;

	@ApiOperation(  value = "Get all categories",
			notes = "getCategories()",
			authorizations = { @Authorization(value="token") 
	})
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Category>> getCategories() throws EntityNotFoundException {
		logger.info("Start get all category");
		try{
			logger.info("End get all category");
			return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get all category");
			throw new EntityNotFoundException(Category.class, "", "Can't get all category");
		}	
	}
	
	@ApiOperation(  value = "Get only all categories without authentication",
			notes = "getCategoriesOnlyWithNoAuth()")
	@RequestMapping(value = "/only-noauth", method = RequestMethod.GET)
	public ResponseEntity<Collection<CategoryOnly>> getCategoriesOnlyWithNoAuth() throws EntityNotFoundException {
		logger.info("Start get all active category only no auth");
		try{
			logger.info("End get all active category only no auth");
			return new ResponseEntity<>(categoryService.findAllCategoryOnly(), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get all active category only no auth");
			throw new EntityNotFoundException(Category.class, "", "Can't get all active category no auth");
		}
	}
	
	@ApiOperation(  value = "Get all categories only",
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
	
	@ApiOperation(  value = "Get all active categories",
			notes = "findAllActiveCategory()",
			authorizations = { @Authorization(value="token") 
	})
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public ResponseEntity<Collection<Category>> findAllActiveCategory() throws EntityNotFoundException {
		logger.info("Start get all active category");
		try{
			logger.info("End get all active category");
			return new ResponseEntity<>(categoryService.findAllActiveCategory(), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get all active category");
			throw new EntityNotFoundException(Category.class, "", "Can't get all active category");
		}
	}

	@ApiOperation(value = "Get category by id",
			notes = "getCategory(@PathVariable long id)",
			authorizations = { @Authorization(value="token") 
	})
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> getCategory(@PathVariable long id) throws EntityNotFoundException {
		logger.info("Start get category by id");
		try{
			logger.info("End get category by id");
			return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get category by id");
			throw new EntityNotFoundException(Category.class, "id", Long.toString(id));
		}
	}

	@ApiOperation(value = "Add a new category",
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

	@ApiOperation(value = "Delete category by id",
			  notes = "deleteCategory(@PathVariable long id)",
			  authorizations = { @Authorization(value="token") 
	})
    @PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCategory(@PathVariable long id) throws EntityNotFoundException {
		logger.info("Start removing category by id: " + id);
		try{
			categoryService.delete(id);
			logger.info("End removing category by id: " + id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(Exception ex){
			logger.info("Can't removing category by id: " + id);
			throw new EntityNotFoundException(Category.class, "id", Long.toString(id));
		}
	}
	
	@ApiOperation(value = "Update category",
			  notes = "updateCategory(@RequestBody Category category)",
			  authorizations = { @Authorization(value="token") 
	})
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updateCategory(@RequestBody Category category) throws EntityNotFoundException, BadRequestException {
		
		if (Long.valueOf(category.getId()) == null || Long.valueOf(category.getId()) == 0) 
			throw new BadRequestException(Category.class, "params", "missing category id");
		
		logger.info("Start update Category");
		try{
			logger.info("End update Category");
    		return new ResponseEntity<Category>(categoryService.save(category), HttpStatus.CREATED);
    	}catch(Exception ex){
		    ex.printStackTrace();
		    logger.error("Can't update Category" + ex);
			logger.fatal("Can't update Category" + ex);
			throw new BadRequestException(Category.class, "params", category.toString());
		}
	}
	
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
			throw new BadRequestException(Category.class, "params", "missing category id or s");	
		}
	}

	@ApiOperation(value = "Get all products for specific category by category id",
			  notes = "getCategoryProducts(@PathVariable long id)",
			  authorizations = { @Authorization(value="token") 
	})
	@RequestMapping(value = "/{id}/products", method = RequestMethod.GET)
	public ResponseEntity<?> getCategoryProducts(@PathVariable long id) throws EntityNotFoundException {		
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
	
	@ApiOperation(value = "Get category by category name",
			  notes = "findByName(@PathVariable String name)",
			  authorizations = { @Authorization(value="token") 
	})
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<Category> findByName(@PathVariable String name) throws EntityNotFoundException {
		logger.info("Start get all active category");
		try{
			logger.info("End get all active category");
			return new ResponseEntity<>(categoryService.findByName(name), HttpStatus.OK);
		}catch(Exception ex){
			logger.error("Can't get all active category");
			throw new EntityNotFoundException(Category.class, "", "Can't get all active category");
		}
	}
	
}
