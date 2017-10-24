package com.myapi.services;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.myapi.beans.Customer;

@Path("/content")
public class ContentResource {

	@GET
	@Path("/file")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public InputStream getFile() {
		String filename = "filesample.txt";
		InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
		return is;
	}

	@GET
	@Path("/stream")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public File getStream() {
		String filename = "filesample.txt";
		File f = new File(getClass().getClassLoader().getResource(filename).getPath());
		return f;
	}

	@GET
	@Path("/bytes")
	@Produces(MediaType.TEXT_PLAIN)
	public byte[] showBytes() {
		return "pure bytes".getBytes();
	}

	@POST
	@Path("/form")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public MultivaluedMap<String, String> getForm(MultivaluedMap<String, String> form) {
		return form;
	}

	@POST
	@Path("/bean")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getBean(Customer customer) {
		return customer;
	}

	@GET
	@Path("/cookie")
	public Response getCookie() {
		NewCookie cookie = new NewCookie("key1", "value1");
		return Response.status(Response.Status.OK).cookie(cookie).build();
	}

	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getList() {
		Customer c = new Customer();
		c.setCity("sdo");
		c.setName("daniel");
		c.setId(1);
		List<Customer> list = new ArrayList<>();
		list.add(c);
		list.add(c);
		GenericEntity<List<Customer>> entity = new GenericEntity<List<Customer>>(list) {
		};
		return Response.ok(entity).build();
	}
}
