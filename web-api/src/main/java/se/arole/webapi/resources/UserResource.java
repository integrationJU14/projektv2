package se.arole.webapi.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.arole.api.controller.UserController;
import se.arole.api.resource.User;
import se.arole.webapi.config.Config;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class UserResource {

	private UserController userController;

	static {
		SPRINGCONTEXT = new AnnotationConfigApplicationContext(Config.class);
	}

	@Context
	private UriInfo uriInfo;
	private final static ApplicationContext SPRINGCONTEXT;

	public UserResource(UserController userController2) {
		this.userController = userController2;
	}

	public UserResource() {
		userController = SPRINGCONTEXT.getBean(UserController.class);
	}

	@GET
	public Response getAll(@QueryParam("userName") String userName, @QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName) {

		if (userName != null) {
			User user = userController.getUserByUserName(userName);
			return Response.ok(user).build();
		}
		Collection<User> user;
		if (firstName != null) {
			user = userController.getUserByFirstName(firstName);
		} else if (lastName != null) {
			user = userController.getUserByLastName(lastName);
		} else {
			user = userController.getAll();
		}
		GenericEntity<Collection<User>> result = new GenericEntity<Collection<User>>(user) {
		};
		return Response.ok(result).build();

	}

	@POST
	public Response createUser(User user) {
		User createdUser = userController.create(user);
		URI location = uriInfo.getAbsolutePathBuilder().path("" + createdUser.getUserId()).build();

		return Response.created(location).build();
	}

	@PUT
	@Path("{id}")
	public Response updateUser(@PathParam("id") Integer id, User user) {

		User updatedUser = userController.update(id, user);

		return Response.ok(updatedUser).build();
	}

	@GET
	@Path("{id}")
	public Response getUser(@PathParam("id") Integer id) {
		User user = userController.getUser(id);

		return Response.ok(user).build();
	}

	@DELETE
	@Path("{id}")
	public Response deleteUser(@PathParam("id") Integer id) {
		User user = userController.getUser(id);
		user.setActive(false);
		userController.update(id, user);
		return Response.status(Status.NO_CONTENT).build();
	}

}