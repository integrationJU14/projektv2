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

import se.arole.api.controller.IssueController;
import se.arole.api.resource.Issue;
import se.arole.webapi.config.Config;

@Path("issue")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class IssueResource {

	static {
		SPRINGCONTEXT = new AnnotationConfigApplicationContext(Config.class);
	}

	// @Autowired
	private IssueController issueController;

	@Context
	private UriInfo uriInfo;

	private final static ApplicationContext SPRINGCONTEXT;

	public IssueResource(IssueController issueController) {
		this.issueController = issueController;
	}

	public IssueResource() {
		issueController = SPRINGCONTEXT.getBean(IssueController.class);
	}

	@GET
	public Response getAll() {
		Collection<Issue> all = issueController.getAll();
		GenericEntity<Collection<Issue>> result = new GenericEntity<Collection<Issue>>(all) {
		};
		return Response.ok(result).build();
	}

	@POST
	public Response createIssue(Issue Issue) {
		Issue createdIssue = issueController.create(Issue);
		URI location = uriInfo.getAbsolutePathBuilder().path("" + createdIssue.getIssueId()).build();

		return Response.created(location).build();
	}

	//
	//// @PUT
	//// @Path("{id}")
	//// public Response updateIssue(@PathParam("id") Integer id, Issue Issue) {
	////
	//// IssueController.getIssue(id);
	//// Issue updatedIssue = IssueController.update(id, Issue);
	////
	//// return Response.ok(updatedIssue).build();
	//// }
	//
	@GET
	@Path("{id}")
	public Response getIssue(@PathParam("id") Integer id) {
		Issue Issue = issueController.getIssue(id);

		return Response.ok(Issue).build();
	}

	// @GET
	// @QueryParam("{IssueName}")
	// public Response getIssue(@QueryParam("IssueName") String IssueName) {
	// Issue Issue = IssueController.getIssue(IssueName);
	// // TODO: POssibility to add a mapper for an exception if no customer is
	// // found
	// return Response.ok(Issue).build();
	// }

}