package com.myapi.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.myapi.beans.Customer;

public class CustomerResourceService implements CustomerResource {

	private Map<Integer, Customer> customerDb = new ConcurrentHashMap<Integer, Customer>();
	private AtomicInteger idCounter = new AtomicInteger();

	@Override
	public Response ping() {
		return Response.ok("ping'd").build();
	}

	@Override
	public Response createCustomer(UriInfo uri, InputStream is) {
		Customer customer = readCustomer(is);
		customer.setId(idCounter.incrementAndGet());
		customerDb.put(customer.getId(), customer);
		String location = uri.getBaseUri() + "customers/" + customer.getId();
		return Response.created(URI.create(location)).build();
	}

	@Override
	public StreamingOutput getCustomer(int id) {
		final Customer customer = customerDb.get(id);
		if (customer == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return new StreamingOutput() {
			@Override
			public void write(OutputStream output) throws IOException, WebApplicationException {
				outputCustomer(output, customer);
			}
		};
	}

	@Override
	public void updateCustomer(int id, InputStream is) {
		Customer update = readCustomer(is);
		Customer current = customerDb.get(id);
		if (current == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		current.setName(update.getName());
		current.setCity(update.getCity());
	}

	protected void outputCustomer(OutputStream os, Customer cust) throws IOException {
		PrintStream writer = new PrintStream(os);
		writer.println("<customer id=\"" + cust.getId() + "\">");
		writer.println("<name>" + cust.getName() + "</name>");
		writer.println("<city>" + cust.getCity() + "</city>");
		writer.println("</customer>");
	}

	protected Customer readCustomer(InputStream is) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(is);
			Element root = doc.getDocumentElement();
			Customer cust = new Customer();
			if (!StringUtils.isBlank(root.getAttribute("id"))) {
				cust.setId(Integer.parseInt(root.getAttribute("id")));
			}
			NodeList nodes = root.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) nodes.item(i);
					if (elem.getTagName().equals("name")) {
						cust.setName(elem.getTextContent());
					} else if (elem.getTagName().equals("city")) {
						cust.setCity(elem.getTextContent());
					}
				}
			}
			return cust;
		} catch (Exception e) {
			throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
		}
	}

}
