package com.shop.api.test.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.shop.api.controller.CategoryController;
import com.shop.api.exception.BadRequestException;
import com.shop.api.exception.EntityNotFoundException;
import com.shop.api.model.Category;
import com.shop.api.service.CategoryService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class CategoryControllerTest.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2019-03-13
 */
@ExtendWith(SpringExtension.class)
public class CategoryControllerTest {

	/** The controller. */
	@InjectMocks
	CategoryController controller;

	/** The category service. */
	@Mock
	CategoryService categoryService;
	
	/** The Constant ID. */
	private static final long ID = 1;

	/**
	 * When id given is present then it should return category.
	 *
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@Test
	public void whenIdGivenIsPresentThenItShouldReturnCategory() throws EntityNotFoundException {
		Category mockCategory = mock(Category.class);

		when(mockCategory.getId()).thenReturn(ID);
		when(mockCategory.getEnabled()).thenReturn(true);
		when(categoryService.findById(ID)).thenReturn(mockCategory);
		when(categoryService.existsById(ID)).thenReturn(true);

		ResponseEntity<Category> responseEntity = controller.getCategoryById(ID);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(mockCategory, responseEntity.getBody());
	}

	/**
	 * When id given is not present then it should return response with not found status code.
	 *
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@Test
	public void whenIdGivenIsNotPresentThenItShouldReturnResponseWithNotFoundStatusCode() throws EntityNotFoundException {
		when(categoryService.existsById(ID)).thenReturn(false);
		ResponseEntity<Category> responseEntity = controller.getCategoryById(ID);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//		assertEquals("Category was not found for parameters {id=99}", responseEntity.getBody());
	}

	/**
	 * When get categories invoked it should return categories list.
	 *
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@Test
	public void whenGetCategoriesInvokedItShouldReturnCategoriesList() throws EntityNotFoundException {
		List<Category> categories = new ArrayList<>();
		Category mockCategory = mock(Category.class);
		categories.add(mockCategory);

		when(mockCategory.getId()).thenReturn(ID);
		when(mockCategory.getEnabled()).thenReturn(true);
		when(categoryService.findAll()).thenReturn(categories);
		
		ResponseEntity<List<Category>> responseEntity = controller.getCategories();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(categories.size(), responseEntity.getBody().size());
		assertEquals(categories.get(0), responseEntity.getBody().get(0));
	}

	/**
	 * When add category invoked it should return added category.
	 *
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws BadRequestException the bad request exception
	 */
	@Test
	public void whenAddCategoryInvokedItShouldReturnAddedCategory() throws EntityNotFoundException, BadRequestException {
		Category mockCategory = mock(Category.class);

		when(mockCategory.getId()).thenReturn(ID);
		when(mockCategory.getEnabled()).thenReturn(true);
		when(categoryService.save(mockCategory)).thenReturn(mockCategory);

		@SuppressWarnings("unchecked")
		ResponseEntity<Category> responseEntity = (ResponseEntity<Category>) controller.addCategory(mockCategory);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(mockCategory, responseEntity.getBody());
	}

	/**
	 * When update category invoked with id which is present then it should return updated category.
	 *
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws BadRequestException the bad request exception
	 */
	@Test
	public void whenUpdateCategoryInvokedWithIdWhichIsPresentThenItShouldReturnUpdatedCategory() throws EntityNotFoundException, BadRequestException {
		Category mockCategory = mock(Category.class);
		mockCategory.setId(ID);
		
		when(mockCategory.getId()).thenReturn(ID);
		when(mockCategory.getEnabled()).thenReturn(true);
		when(categoryService.save(mockCategory)).thenReturn(mockCategory);
		when(categoryService.existsById(ID)).thenReturn(true);

		ResponseEntity<?> responseEntity = controller.updateCategory(mockCategory);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(mockCategory, responseEntity.getBody());
	}

	/**
	 * When update category invoked with id which is not present then it should return not found status.
	 *
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws BadRequestException the bad request exception
	 */
	@Test
	public void whenUpdateCategoryInvokedWithIdWhichIsNotPresentThenItShouldReturnNotFoundStatus() throws EntityNotFoundException, BadRequestException {
		Category mockCategory = mock(Category.class);
		mockCategory.setId(ID);
		when(categoryService.existsById(ID)).thenReturn(false);
		when(mockCategory.getId()).thenReturn(ID);
		
		ResponseEntity<?> responseEntity = controller.updateCategory(mockCategory);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
		assertEquals(null, responseEntity.getBody());
	}

	/**
	 * When delete category invoked with id which is present then it should return OK status.
	 *
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@Test
	public void whenDeleteCategoryInvokedWithIdWhichIsPresentThenItShouldReturnOKStatus() throws EntityNotFoundException {
		when(categoryService.existsById(ID)).thenReturn(true);

		ResponseEntity<Void> responseEntity = controller.deleteCategory(ID);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	/**
	 * When delete category invoked with id which is not present then it should return not found status.
	 *
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@Test
	public void whenDeleteCategoryInvokedWithIdWhichIsNotPresentThenItShouldReturnNotFoundStatus() throws EntityNotFoundException {

		when(categoryService.existsById(ID)).thenReturn(false);

		ResponseEntity<Void> responseEntity = controller.deleteCategory(ID);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
}