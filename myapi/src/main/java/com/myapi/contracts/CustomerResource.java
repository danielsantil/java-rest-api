package com.myapi.contracts;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;

import com.myapi.annotations.PING;

@Path("/customers")
public interface CustomerResource {

	@PING
	public Response ping();

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createCustomer(@Context UriInfo uri, InputStream is);

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public StreamingOutput getCustomer(@PathParam("id") int id);

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public void updateCustomer(@PathParam("id") int id, InputStream is);
}
