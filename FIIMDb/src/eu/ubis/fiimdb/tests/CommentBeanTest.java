package eu.ubis.fiimdb.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import eu.ubis.fiimdb.controller.CommentBean;
import eu.ubis.fiimdb.model.Comment;

public class CommentBeanTest extends CommentBean {

	@Test
	public void testGetCommentsList() {
		List<Comment> comments = new ArrayList<Comment>();
		CommentBean commentBean = new CommentBean();
		int movieId = 14;
		commentBean.loadComments(movieId);
		comments = commentBean.getCommentsList();
		assertNotNull(comments);
	}
	
	public void testDeleteComment() {
		int dbCommentsCounter = 2;
		CommentBean commentBean = new CommentBean();
		commentBean.deleteComment(12);
		int actualCommentsCounter = 1;
		assertEquals(dbCommentsCounter,actualCommentsCounter);
	}
}
