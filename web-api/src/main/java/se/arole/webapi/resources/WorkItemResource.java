package se.arole.webapi.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("work")
public class WorkItemResource {
	
	@GET
	public Response getAllWork() {
		return Response.ok("Not implemented get WorkItem method").build();
	}
}
