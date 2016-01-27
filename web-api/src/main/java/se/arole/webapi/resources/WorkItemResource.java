package se.arole.webapi.resources;

import java.net.URI;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
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

import se.arole.api.controller.WorkItemController;
import se.arole.api.resource.WorkItem;
import se.arole.webapi.config.Config;

@Path("work")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkItemResource {

	private WorkItemController workItemController;

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
	}

	@GET
	public Response getAllWorkItems() {
		Collection<WorkItem> all = workItemController.getAll();
		GenericEntity<Collection<WorkItem>> result = new GenericEntity<Collection<WorkItem>>(all) {
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
	public Response getUser(@PathParam("id") Integer id) {
		WorkItem work = workItemController.findByItemId(id);
		return Response.ok(work).build();
	}

	@PUT
	@Path("{id}")
	public Response changeStatusOnWorkItem(@PathParam("id") Integer id, @QueryParam("status") String status) {
		workItemController.changeStatusWorkItem(status, id);

		return Response.status(Status.ACCEPTED).header("status changed ", "work/" + id).build();
	}

	// @PUT
	// @Path("{id}")
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.TEXT_PLAIN)
	// public Response addWorkItemToUser(@PathParam("id") int workId, UserVO
	// userVO) {
	// WorkItem workItem = workItemController.findByItemId(workId);
	// workItemController.addWorkItemToUser( workItem, userVO);
	//
	// return Response.status(Status.ACCEPTED).header("workitem is updated",
	// "work/" + workId)
	// .build();
	// }
	//
	// @GET
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	// public Response workItemByUser(UserVO userVO) {
	//
	// List<WorkItem> result = workItemController.workItembyUser(userVO);
	//
	// return Response.ok(result).build();
	// }
	//
	// @GET
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	// public Response workItemsByTeam(Team team) {
	// List<WorkItem> result = workItemController.workItemsByTeam(team);
	//
	// return Response.ok(result).build();
	// }
	//
	// @GET
	// @Consumes(MediaType.TEXT_PLAIN)
	// @Produces(MediaType.APPLICATION_JSON)
	// public Response getWorkItemByStatus(@QueryParam("status")
	// @DefaultValue("TO_DO") String status) {
	// List<WorkItem> result = workItemController.workItemByStatus(status);
	//
	// return Response.ok(result).build();
	// }
	//
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response workItemByDescription(@QueryParam("description") @DefaultValue("") String description) {
		List<WorkItem> result = workItemController.workItemByDescription(description);

		return Response.ok(result).build();
	}
}