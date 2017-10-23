package com.myapi.services;

import java.io.File;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

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
}
