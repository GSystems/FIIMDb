package eu.ubis.fiimdb.controller;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import eu.ubis.fiimdb.model.User;
import eu.ubis.fiimdb.service.ServiceFactory;
import eu.ubis.fiimdb.service.UserService;

public class UserBean {
	
	private UserService userService = ServiceFactory.getUserService();
	private static User user = new User();
	
	public User getUser() {
		return user;
	}
	
	public void getUserByUsername(String username) {
		user = userService.getUserByUsername(username);
	}
	
	public void updateUserInfo(User user, String username) {
		userService.updateUserInfo(user, username);
	}
	
	public void resetPassword(String oldPassword, String newPassword) throws NoSuchAlgorithmException {
		userService.resetPassword(oldPassword, newPassword);
	}
	
	public void insertNewUser(User user) throws SQLException {
		userService.insertNewUser(user);
	}
	
	public void getUserById(int id) {
		user = userService.getUserById(id);
	}
}