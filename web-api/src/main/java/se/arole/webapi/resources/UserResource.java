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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.arole.api.controller.UserController;
import se.arole.api.resource.UserVO;
import se.arole.webapi.config.Config;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class UserResource {

	static {
		SPRINGCONTEXT = new AnnotationConfigApplicationContext(Config.class);
	}

	@Autowired
	private UserController userController;
	private final static ApplicationContext SPRINGCONTEXT;
	@Context
	private UriInfo uriInfo;

	public UserResource(UserController userController2) {
		this.userController = userController2;
	}

	public UserResource() {
		userController = SPRINGCONTEXT.getBean(UserController.class);
	}

	@GET
	public Response getAll() {
		Collection<UserVO> all = userController.getAll();
		GenericEntity<Collection<UserVO>> result = new GenericEntity<Collection<UserVO>>(all) {
		};
		return Response.ok(result).build();
	}

	@POST
	public Response createUser(UserVO user) {
		UserVO createdUser = userController.create(user);
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
		UserVO user = userController.getUser(id);

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