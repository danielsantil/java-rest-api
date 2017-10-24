package com.myapi.services;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.myapi.subresources.AcceptSubResource;
import com.myapi.subresources.OkSubResource;

@Path("/root")
public class RootResource {

	@Path("{res}")
	public Object getResource(@PathParam("res") String res) {
		if ("ok".equals(res)) {
			return new OkSubResource();
		} else if ("accept".equals(res)) {
			return new AcceptSubResource();
		} else {
			return Response.serverError();
		}
	}
}
