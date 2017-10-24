package com.myapi.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;

import com.myapi.services.ContentResource;
import com.myapi.services.CustomerResourceService;
import com.myapi.services.InjectionsResource;
import com.myapi.services.RootResource;

@ApplicationPath("/*")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		// Jackson providers to parse JSON formats passed as resource's parameters
		classes.add(JacksonFeature.class);
		classes.add(JacksonCustomProvider.class);
		// Resources
		classes.add(InjectionsResource.class);
		classes.add(RootResource.class);
		classes.add(ContentResource.class);
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<>();
		singletons.add(new CustomerResourceService());
		return singletons;
	}

}
