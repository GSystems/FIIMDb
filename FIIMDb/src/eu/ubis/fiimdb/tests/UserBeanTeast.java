package eu.ubis.fiimdb.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import eu.ubis.fiimdb.controller.UserBean;
import eu.ubis.fiimdb.model.User;

public class UserBeanTeast extends UserBean {

	@Test
	public void test() throws SQLException {
		String username="iulian";
		UserBean userBean = new UserBean();
		userBean.getUserByUsername(username);
		User user = new User();
		user = userBean.getUser();
		assertNotNull(user);
		
		//we should found admin at id=1
		int userId = 1;
		user = null;
		userBean.getUserById(userId);
		user = userBean.getUser();
		username = "admin";
		assertEquals(username, user.getUsername());
	}

}
