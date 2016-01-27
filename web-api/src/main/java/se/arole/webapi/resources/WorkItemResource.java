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
import se.arole.api.controller.WorkItemController;
import se.arole.api.resource.User;
import se.arole.api.resource.WorkItem;
import se.arole.webapi.config.Config;

@Path("work")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkItemResource {

	private WorkItemController workItemController;
	private UserController userController;

	static {
		context = new AnnotationConfigApplicationContext(Config.class);
	}

	@Context
	private UriInfo uriInfo;
	private final static ApplicationContext context;

	public WorkItemResource(WorkItemController workItemController) {
		this.workItemController = workItemController;
	}

	public WorkItemResource() {
		workItemController = context.getBean(WorkItemController.class);
		userController = context.getBean(UserController.class);
	}

	@GET
	public Response getAllWorkItems(@QueryParam("status") String status, @QueryParam("userId") Integer userId,
			@QueryParam("description") String description) {
		Collection<WorkItem> workItems;

		if (status != null) {
			workItems = workItemController.workItemByStatus(status);
		} else if (userId != null) {
			User user = userController.getUser(userId);
			workItems = workItemController.workItembyUser(user);
		} else if (description != null) {
			workItems = workItemController.workItemByDescription(description);
		} else {
			workItems = workItemController.getAll();
		}

		GenericEntity<Collection<WorkItem>> result = new GenericEntity<Collection<WorkItem>>(workItems) {
		};
		return Response.ok(result).build();
	}

	@POST
	public Response createWorkItem(WorkItem workItem) {
		WorkItem createWorkItem = workItemController.createWorkItem(workItem);
		URI location = uriInfo.getAbsolutePathBuilder().path(createWorkItem.getWorkItemId() + "").build();
		return Response.created(location).build();
	}

	@GET
	@Path("{id}")
	public Response getWorkItem(@PathParam("id") Integer id) {
		WorkItem work = workItemController.findByItemId(id);
		return Response.ok(work).build();
	}

	@PUT
	@Path("{id}")
	public Response changeStatusOnWorkItem(@PathParam("id") Integer id, @QueryParam("status") String status,
			@QueryParam("userName") String userName) {
		String message;
		if (userName != null) {
			WorkItem updateMe = workItemController.findByItemId(id);
			User user = userController.getUserByUserName(userName);
			workItemController.addWorkItemToUser(updateMe, user);
			message = "User added ";
		} else if (status != null) {
			workItemController.changeStatusWorkItem(status, id);
			message = "Status changed ";
		} else {
			message = "nothing changed";
		}
		return Response.status(Status.ACCEPTED).header(message, "work/" + id).build();
	}

	@DELETE
	@Path("{id}")
	public Response removeWorkItem(@PathParam("id") Integer id) {
		workItemController.remove(id);
		return Response.status(Status.NO_CONTENT).build();
	}
}