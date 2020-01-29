package com.servicio1.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path(value="")
@ApplicationScoped
public class PingRest {
	
	
	@GET
	@Path("/ping")
	public String hola() {
		System.out.println("check");
		return "ok";
	}
}
