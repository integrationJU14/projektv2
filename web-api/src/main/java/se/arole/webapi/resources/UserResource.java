package se.arole.webapi.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.gson.JsonObject;
import javax.ws.rs.QueryParam;
import se.arole.api.controller.UserController;
import se.arole.api.resource.User;
import se.arole.webapi.config.Config;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class UserResource {

	// @Autowired
	private UserController userController;

	@Context
	private UriInfo uriInfo;

	public UserResource(UserController userController2) {
		this.userController = userController2;
	}

	public UserResource() {
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		userController = context.getBean(UserController.class);
	}

	@GET
	public Response getAll(@QueryParam("userName") String userName, @QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName) {

		if (userName == null && firstName == null && lastName == null) {
			Collection<User> all = userController.getAll();
			GenericEntity<Collection<User>> result = new GenericEntity<Collection<User>>(all) {
			};
			return Response.ok(result).build();
		}
		if (userName != null) {
			User user = userController.getUserByUsername(userName);
			return Response.ok(user).build();
		}
		if (firstName != null) {
			User user = userController.getUserByFirstname(firstName);
			return Response.ok(user).build();
		}
		if (lastName != null) {
			User user = userController.getUserByLastname(lastName);
			return Response.ok(user).build();
		}

		return Response.noContent().build();

	}

	@POST
	public Response createUser(User user) {
		User createdUser = userController.create(user);
		URI location = uriInfo.getAbsolutePathBuilder().path("" + createdUser.getUserId()).build();

		return Response.created(location).build();
	}

	//
	//// @PUT
	//// @Path("{id}")
	//// public Response updateUser(@PathParam("id") Integer id, UserVO user) {
	////
	//// userController.getUser(id);
	//// UserVO updatedUser = userController.update(id, user);
	////
	//// return Response.ok(updatedUser).build();
	//// }
	//
	@GET
	@Path("{id}")
	public Response getUser(@PathParam("id") Integer id) {
		User user = userController.getUser(id);

		return Response.ok(user).build();
	}

	// @GET
	// @QueryParam("{userName}")
	// public Response getUser(@QueryParam("userName") String userName) {
	// UserVO user = userController.getUser(userName);
	// // TODO: POssibility to add a mapper for an exception if no customer is
	// // found
	// return Response.ok(user).build();
	// }

}