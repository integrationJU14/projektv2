package se.arole.webapi.resources;

import java.net.URI;
import java.util.List;

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

import se.arole.api.controller.TeamController;
import se.arole.api.resource.Team;
import se.arole.webapi.config.Config;

@Path("team")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamResource {

	static {
		SPRINGCONTEXT = new AnnotationConfigApplicationContext(Config.class);
	}

	private final static ApplicationContext SPRINGCONTEXT;
	private TeamController teamController;

	@Context
	private UriInfo uriInfo;

	public TeamResource() {
		teamController = SPRINGCONTEXT.getBean(TeamController.class);
	}

	@GET
	public Response getAll() {
		List<Team> all = teamController.getAll();
		GenericEntity<List<Team>> result = new GenericEntity<List<Team>>(all) {
		};
		return Response.ok(result).build();
	}

	@POST
	public Response addTeam(Team team) {
		try {
			Team add = teamController.add(team);
			URI location = uriInfo.getAbsolutePathBuilder().path("" + add.getTeamId()).build();
			return Response.created(location).build();
		} catch (IllegalArgumentException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("{id}")
	public Response getTeam(@PathParam("id") Integer teamId) {
		Team team = teamController.getTeam(teamId);
		if (team == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.ok(team).build();
	}

	@PUT
	@Path("{id}")
	public Response updateTeam(@PathParam("id") Integer teamId, Team team) {
		Team updateTeam = teamController.updateTeam(teamId, team);

		return Response.ok(updateTeam).build();
	}

	@POST
	@Path("{id}/add_user")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response addUser(@PathParam("id") Integer teamId, @QueryParam("userId")Integer userId) {
		teamController.addUser(teamId, userId);
		Team team = teamController.getTeam(teamId);
		return Response.ok(team).build();
	}
	@DELETE
	@Path("{id}")
	public Response deleteTeam(@PathParam("id") Integer teamId) {
		teamController.deleteTeam(teamId);
		
		return Response.ok("deleted "+teamId).build();
	}
}
