package com.myapi.config;

import javax.ws.rs.ext.ContextResolver;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

public class JacksonCustomProvider implements ContextResolver<ObjectMapper> {

	final ObjectMapper defaultObjectMapper;

	public JacksonCustomProvider() {
		defaultObjectMapper = createDefaultMapper();
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return defaultObjectMapper;
	}

	private static ObjectMapper createDefaultMapper() {
		ObjectMapper mapper = new ObjectMapper();
		// Ignores unknown properties, same as @JsonIgnoreProperties(ignoreUnknown=true)
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// Makes object mapper recognize standard JAXB annotations
		AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(
				mapper.getTypeFactory());
		mapper.setAnnotationIntrospector(introspector);

		return mapper;
	}
}
