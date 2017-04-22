package eu.ubis.fiimdb.controller;

import java.util.List;

import eu.ubis.fiimdb.model.Comment;
import eu.ubis.fiimdb.service.CommentService;
import eu.ubis.fiimdb.service.ServiceFactory;

public class CommentBean {
	private static CommentService commentService = ServiceFactory.getCommentService();
	private static List<Comment> comments;
	
	public List<Comment> getCommentsList() {
		return comments;
	}
	
	public void saveComment(Comment comment, String username, int movieId) {
		commentService.insertComment(comment, username, movieId);
	}
	
	public void loadComments(int movieId) {
		comments = commentService.getCommentsByMovieId(movieId);
	}
	
	public void deleteComment(int commentId) {
		commentService.deleteComment(commentId);
	}
}
