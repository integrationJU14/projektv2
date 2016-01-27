package se.arole.datalayer.serviceImp;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import se.arole.datalayer.config.Config;
import se.arole.datalayer.entity.UserJPA;

/**
 * @author Holstad
 *
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class })
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class UserServiceImpTest {

	private UserJPA user;
	@Autowired
	private UserServiceImp userServiceImp;

	@Before
	public void initUser() {
		user = new UserJPA("MrTest","firstNameTest","lastNameTest", 1000000000, true);
	}

	@Test
	public void saveUserTest() {
		userServiceImp.createUser(user);
	}
	
	@Test
	public void updateUserTest(){
		UserJPA updatedUser = new UserJPA("MrTestUpdate","firstNameTest","firstNameTest",user.getUserId(),true);
		userServiceImp.updateUser(updatedUser, user.getUserId());
	}
	
	@Test
	public void getUserByNameTest(){
		UserJPA userTemp = userServiceImp.getUserByUserName("MrTest");
		System.out.println(userTemp.toString());
	}
	@Test
	public void getUserById(){
		userServiceImp.createUser(new UserJPA("getMe","Ola","Holstad", 746357, true));
		UserJPA userTemp = userServiceImp.getUser(746357);
		System.out.println(userTemp.toString());
	
	}
	
	
}