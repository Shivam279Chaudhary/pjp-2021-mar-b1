package com.project.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.dao.CommentDAO;
import com.project.entity.Comment;

class CommentDAOTest {

	CommentDAO commentDao;

	@BeforeEach
	void initialize() {
		this.commentDao = new CommentDAO();
	}

	@Test
	void CRUDTestPass() {
		// given
		Comment comment = new Comment(14, "Test Comment", "2020-01-01 10:10:10", 2, 1, 10);

		// Create Test

		boolean inserted = commentDao.insertComment(comment);

		assertTrue(inserted);

		// Read Test

		int id = commentDao.getLatestCommentID();

		Comment ans = commentDao.getCommentByCommentID(id);

		assertEquals(ans.getDescription(), "Test Comment");

		// Update test

		ans.setDescription("Updated Test comment");

		assertTrue(commentDao.updateCommentByCommentID(ans));

		// Delete test

		assertTrue(commentDao.deleteComment(id));

	}

	@Test
	void CreateFail() {

		Comment comment = new Comment(14, "Test Comment", "ahaha", 2, 1, 10);
		assertFalse(commentDao.insertComment(comment));

	}

	@Test
	void ReadFail() {

		assertNull(commentDao.getCommentByCommentID(150));

	}

	@Test
	void UpdateFail() {

		Comment comment = new Comment(155, "Test Comment", "2020-01-01 10:10:10", 2, 1, 10);
		assertFalse(commentDao.updateCommentByCommentID(comment));

	}

	@Test
	void DeleteFail() {

		// Comment comment= new Comment(155,"Test Comment",4,"2020-01-01
		// 10:10:10",2,1,10);
		assertFalse(commentDao.deleteComment(155));

	}

}
