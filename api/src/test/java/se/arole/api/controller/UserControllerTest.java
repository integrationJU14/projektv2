package se.arole.api.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.arole.api.resource.User;
import se.arole.datalayer.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = se.arole.datalayer.config.Config.class)
public class UserControllerTest {

	@Autowired
	UserService userService;

	@Test
	public void integrationTest() {
		UserController userController = new UserController(userService);
		User user = new User(5, true, "Ola", "", "");
		userController.create(user);
	}

}
