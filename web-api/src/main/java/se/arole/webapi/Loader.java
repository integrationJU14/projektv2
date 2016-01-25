package se.arole.webapi;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;

import se.arole.webapi.resources.UserResource;

@ApplicationPath("/*")
public class Loader extends ResourceConfig{
	
	public Loader() {
		register(UserResource.class);
	}
}
