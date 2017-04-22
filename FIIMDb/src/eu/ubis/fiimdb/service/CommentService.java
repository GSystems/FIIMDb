package eu.ubis.fiimdb.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import eu.ubis.fiimdb.db.dao.CommentDao;
import eu.ubis.fiimdb.db.dao.MovieDao;
import eu.ubis.fiimdb.db.dao.UserDao;
import eu.ubis.fiimdb.model.Comment;

public class CommentService {
	
	public void insertComment(Comment comment, String username, int movieId) {
		CommentDao commentDao = new CommentDao();
		UserDao user  = new UserDao();
		MovieDao movie = new MovieDao();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		
		entityManager.getTransaction().begin();
		user = (UserDao) entityManager.createQuery("select u from UserDao u where u.username LIKE '" + username + "'").getSingleResult();
		movie = (MovieDao) entityManager.createQuery("select m from MovieDao m where m.id="+ movieId).getSingleResult();
		
		commentDao.setComment(comment.getComment());
		commentDao.setUserId(user.getId());
		commentDao.setMovie(movie);
		
		entityManager.persist(commentDao);
		entityManager.getTransaction().commit();
		entityManager.close();
		emf.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getCommentsByMovieId(int movieId) {
		List<Comment> comments = new ArrayList<Comment>();
		List<CommentDao> commentsDao = new ArrayList<CommentDao>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();

		entityManager.getTransaction().begin();
		commentsDao = entityManager.createQuery("SELECT c FROM CommentDao c JOIN c.movie movie WHERE movie.id=" + movieId).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		emf.close();
		
		for(CommentDao commentDao:commentsDao) {
			comments.add(mapCommentDaoToComment(commentDao));
		}
		return comments;
	}
	
	private Comment mapCommentDaoToComment(CommentDao commentDao) {
		Comment comment = new Comment();
		comment.setId(commentDao.getId());
		comment.setComment(commentDao.getComment());
		comment.setUserId(commentDao.getUserId());
		return comment;
	}
}
