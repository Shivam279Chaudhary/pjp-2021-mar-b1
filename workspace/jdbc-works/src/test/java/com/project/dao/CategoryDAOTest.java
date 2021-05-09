package com.project.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.project.dao.CategoryDAO;
import com.project.entity.Category;
import com.project.interfaces.ICategoryDAO;

class CategoryDAOTest {

	@Test
	void test() {
//		fail("Not yet implemented");
	}

	void CRUDCheckingWhenCorrectInput() {

		Category cat = new Category();
		String catName = "Sample Test Value";
		cat.setCategoryName(catName);
		ICategoryDAO dao = new CategoryDAO();

		// CRUD operation
		// insert
		dao.insertCategory(cat);
		int catid = dao.getCategoryIDByCategoryName(catName);
		String resString = dao.getCategoryById(catid).toString();
		String expected = "Category [categoryID=" + catid + ", category=" + catName + "]";

		assertEquals(resString, expected);

		// update
		String catName2 = "Sample Test Value 2";
		cat.setCategoryName(catName2);
		cat.setCategoryID(catid);

		dao.updateCategory(cat);
		resString = dao.getCategoryById(catid).toString();
		expected = "Category [categoryID=" + catid + ", category=" + catName2 + "]";

		assertEquals(resString, expected);

	}

	@Test
	void removeCategoryWhenInvalidCategoryID() {
		ICategoryDAO dao = new CategoryDAO();
		assertFalse(dao.removeCategory(1100));
	}

	@Test
	void updateCategoryWhenInvalidCategoryID() {
		ICategoryDAO dao = new CategoryDAO();
		Category cat = new Category();
		cat.setCategoryID(100);
		cat.setCategoryName("something random");
		assertFalse(dao.updateCategory(cat));
	}

	@Test
	void insertCategoryWhenInvalidCategoryName() {
		ICategoryDAO dao = new CategoryDAO();
		Category cat = new Category();
		cat.setCategoryName("diet");
		assertFalse(dao.insertCategory(cat));
	}

	@Test
	void getCategoryByIDWhenInvalidCategoryID() {
		ICategoryDAO dao = new CategoryDAO();
		assertNull(dao.getCategoryById(100));
	}

	@Test
	void getCategoryIDByCategoryNameWhenInvalidCategoryName() {
		ICategoryDAO dao = new CategoryDAO();
		assertEquals(-1, dao.getCategoryIDByCategoryName("alpha"));
	}

}
