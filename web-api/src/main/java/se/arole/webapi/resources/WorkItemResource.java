package se.arole.webapi.resources;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
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
import se.arole.api.resource.Team;
import se.arole.api.resource.UserVO;
import se.arole.api.resource.WorkItem;
import se.arole.webapi.config.Config;

@Path("work")
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
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllWorkItems() {

		Collection<WorkItem> all = workItemController.getAll();
		GenericEntity<Collection<WorkItem>> result = new GenericEntity<Collection<WorkItem>>(all) {
		};
		return Response.ok(result).build();
	}

//	@GET
//	@Path("{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getUser(@PathParam("id") Integer id) {
//
//		WorkItem work = workItemController.findByItemId(id);
//
//		return Response.ok(work).build();

//	}

//	@PUT
//	@Path("{id}")
//	@Consumes(MediaType.TEXT_PLAIN)
//	@Produces(MediaType.TEXT_PLAIN)
//	public Response changeStatusWorkItem(@QueryParam("status") @DefaultValue("TO_DO") String status,
//			@PathParam("id") Integer id) {
//		workItemController.changeStatusWorkItem(status, id);
//
//		return Response.status(Status.ACCEPTED).header("status changed  ", "work/" + id).build();
//	}
//
//	@PUT
//	@Path("{id}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.TEXT_PLAIN)
//	public Response addWorkItemToUser(@PathParam("id") int workId, UserVO userVO) {
//		WorkItem workItem = workItemController.findByItemId(workId);
//		workItemController.addWorkItemToUser( workItem, userVO);
//
//		return Response.status(Status.ACCEPTED).header("workitem is updated", "work/" + workId)
//				.build();
//	}
//
//	@GET
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response workItemByUser(UserVO userVO) {
//		
//		List<WorkItem> result = workItemController.workItembyUser(userVO);
//
//		return Response.ok(result).build();
//	}
//
//	@GET
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response workItemsByTeam(Team team) {
//		List<WorkItem> result = workItemController.workItemsByTeam(team);
//
//		return Response.ok(result).build();
//	}
//
//	@GET
//	@Consumes(MediaType.TEXT_PLAIN)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getWorkItemByStatus(@QueryParam("status") @DefaultValue("TO_DO") String status) {
//		List<WorkItem> result = workItemController.workItemByStatus(status);
//
//		return Response.ok(result).build();
//	}
//
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response workItemByDescription(@QueryParam("description") @DefaultValue("") String description) {
		List<WorkItem> result = workItemController.workItemByDescription(description);

		return Response.ok(result).build();
	}
}