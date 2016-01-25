package se.arole.webapi;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.arole.api.adapter.UserAdapter;
import se.arole.api.controller.UserController;
import se.arole.webapi.adapter.UserMapper;
import se.arole.webapi.config.Config;
import se.arole.webapi.resources.UserResource;

@ApplicationPath("/*")
public class Loader extends Application{

//	public Loader() {
//		register(UserResource.class);
//		register(UserMapper.class);
//		register(UserAdapter.class);
//	}
}
