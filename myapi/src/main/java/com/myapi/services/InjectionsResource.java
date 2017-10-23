package com.myapi.services;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Encoded;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.myapi.beans.Customer;

@Path("/injections")
public class InjectionsResource {

	@POST
	@Path("/form")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getForm(@DefaultValue("santil") @FormParam("name") String name,
			@FormParam("age") int age) {
		return Response.ok(name + " " + age).build();
	}

	@GET
	@Path("/bean")
	public Response createCustomerBean(@BeanParam Customer cust) {
		return Response.ok(cust.getCity() + "-" + cust.getName()).build();
	}

	@GET
	@Path("/cookie")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCookie(@CookieParam("token") String cookie) {
		return Response.ok(cookie).build();
	}

	@GET
	@Path("/cookie2")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCookie(@CookieParam("token") Cookie cookie) {
		return Response.ok(cookie.toString()).build();
	}

	@GET
	@Path("/cookies")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCookies(@Context HttpHeaders cookies) {
		return Response.ok(cookies.getCookies().toString()).build();
	}

	@GET
	@Path("/header")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getHeader(@HeaderParam("token") String header) {
		return Response.ok(header).build();
	}

	@GET
	@Path("/headers")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getHeaders(@Context HttpHeaders headers) {
		String header = headers.getRequestHeaders().toString();
		return Response.ok(header).build();
	}

	@GET
	@Path("/queries")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getQueries(@QueryParam("num1") List<Integer> num1,
			@QueryParam("bool") boolean isTrue,
			@Encoded @QueryParam("enc") String enc) {
		return Response.ok(num1 + " " + isTrue + enc).build();
	}

	@GET
	@Path("/{name}/{age}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getDudeMatrix(@PathParam("name") PathSegment dude, @PathParam("age") int age) {
		String y = dude.getMatrixParameters().toString();
		return Response.ok(y).build();
	}

	@GET
	@Path("/matrix/{name}/{age}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getDudeMatrix(@PathParam("name") String name, @PathParam("age") int age,
			@MatrixParam("color") Color color) {
		String y = String.valueOf(color);
		return Response.ok(y).build();
	}

	@GET
	@Path("/paths")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getUriInfo(@Context UriInfo info) {
		String x = info.getPath();
		x = info.getPath(true);
		x = info.getPath(false);
		x = info.getAbsolutePath().toString();
		x = info.getBaseUri().toString();
		x = info.getPathParameters().toString();
		x = info.getPathSegments().toString();
		x = info.getQueryParameters().toString();
		x = info.getRequestUri().toString();
		return Response.ok(x).build();
	}
}
