package eu.ubis.fiimdb.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import eu.ubis.fiimdb.db.dao.UserDao;
import eu.ubis.fiimdb.model.User;

public class UserService {
	
	private static User user;
	
	@SuppressWarnings("unchecked")
	public User getUserByUsername(String username) {
		List<UserDao> userDao = new ArrayList<UserDao>();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		userDao = entityManager.createQuery("select u from UserDao u where u.username LIKE '" + username + "'").getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		emf.close();
		user = mapUserDaoToUser(userDao.get(0));
		return user;
	}
	
	public void updateUserInfo(User user, String username) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		UserDao userDao = new UserDao();
		
		entityManager.getTransaction().begin();

		int id = UserService.user.getId();
		userDao = entityManager.find(UserDao.class, id);
		userDao.setUsername(user.getUsername());
		userDao.setFirstname(user.getFirstname());
		userDao.setLastname(user.getLastname());
		userDao.setEmail(user.getEmail());
		
		entityManager.getTransaction().commit();
		entityManager.close();
		emf.close();
	}
	
	public void resetPassword(String oldPassword, String newPassword) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		UserDao userDao = new UserDao();
		
		entityManager.getTransaction().begin();
		
		int id = UserService.user.getId();
		userDao = entityManager.find(UserDao.class, id);
		if(oldPassword.equals(userDao.getPassword()))
			userDao.setPassword(newPassword);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		emf.close();	
	}
	
	private User mapUserDaoToUser(UserDao userDao) {
		User user = new User();
		user.setId(userDao.getId());
		user.setFirstname(userDao.getFirstname());
		user.setLastname(userDao.getLastname());
		user.setPassword(userDao.getPassword());
		user.setEmail(userDao.getEmail());
		user.setUsername(userDao.getUsername());		
		return user;
	}
}