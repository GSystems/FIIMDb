package eu.ubis.fiimdb.service;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

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
		if(userDao.get(0) != null)
			user = mapUserDaoToUser(userDao.get(0));
		return user;
	}
	
	@SuppressWarnings("unchecked")
	private boolean checkIfUserExists(String type, String value) throws SQLException {
		List<UserDao> userDao = new ArrayList<UserDao>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		userDao = entityManager.createQuery("select u from UserDao u where u." + type + " LIKE '" + value + "'").getResultList();
		entityManager.close();
		emf.close();
		
		if(!userDao.isEmpty() && "username".equals(type))
			throw new SQLException("this username already exists");
		else if(!userDao.isEmpty() && "email".equals(type))
			throw new SQLException("this email already exists");
		else
			return false;
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
	
	public void resetPassword(String oldPassword, String newPassword) throws NoSuchAlgorithmException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		UserDao userDao = new UserDao();
		
		entityManager.getTransaction().begin();
		
		int id = UserService.user.getId();
		userDao = entityManager.find(UserDao.class, id);

		String a = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			a = new HexBinaryAdapter().marshal(md.digest(oldPassword.getBytes(Charset.forName("UTF-8"))));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		if(a.equals(userDao.getPassword())) {
		    try {
		        MessageDigest md = MessageDigest.getInstance("MD5");
		        a = new HexBinaryAdapter().marshal(md.digest(newPassword.getBytes(Charset.forName("UTF-8"))));
		    	userDao.setPassword(a);
		    } catch (NoSuchAlgorithmException e) {
		    	e.printStackTrace();
		    }
		}
	
		entityManager.getTransaction().commit();
		entityManager.close();
		emf.close();	
	}
	
	public void insertNewUser(User newUser) throws SQLException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		UserDao userDao = new UserDao();
		userDao = mapUserToUserDao(newUser);
		
		boolean usernameExists = checkIfUserExists("username", newUser.getUsername());
		boolean emailExists = checkIfUserExists("email", newUser.getEmail());
		
		if(usernameExists == false && emailExists == false) {
			entityManager.getTransaction().begin();
			entityManager.persist(userDao);
			entityManager.getTransaction().commit();
		}
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
	
	private UserDao mapUserToUserDao(User user) {
		UserDao userDao = new UserDao();
		userDao.setId(user.getId());
		userDao.setFirstname(user.getFirstname());
		userDao.setLastname(user.getLastname());
		String passwordHash = user.getPassword();
	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        String a = new HexBinaryAdapter().marshal(md.digest(passwordHash.getBytes(Charset.forName("UTF-8"))));
	    	userDao.setPassword(a);
	    } catch (NoSuchAlgorithmException e) {
	    	e.printStackTrace();
	    }
		userDao.setEmail(user.getEmail());
		userDao.setUsername(user.getUsername());		
		return userDao;
	}
}