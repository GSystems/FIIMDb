package eu.ubis.fiimdb.controller;

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
	
	public void resetPassword(String oldPassword, String newPassword) {
		userService.resetPassword(oldPassword, newPassword);
	}
}