package com.myapi.services.subresources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class OkSubResource {

	@GET
	@Path("{howru}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getOk() {
		return Response.accepted("I'm always ok").build();
	}

}
