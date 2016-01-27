package se.arole.datalayer.serviceImp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import se.arole.datalayer.config.Config;
import se.arole.datalayer.entity.User;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { Config.class })
//@TestExecutionListeners(listeners= DependencyInjectionTestExecutionListener.class)
public class ServiceImpTest {

//	@Autowired
	UserServiceImp userService;
	
//	@Test
//	public void springConfigTest() {
//		User user = new User("Lina", 3, true);
//		userService.createUser(user);
//		
//	}

}
