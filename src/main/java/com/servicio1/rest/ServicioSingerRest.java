package com.servicio1.rest;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.servicio1.dto.Singer;
import com.servicio1.servicio.ServicioSinger;

@Path("/singers")
@ApplicationScoped
public class ServicioSingerRest implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ServicioSinger servicio;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Singer> listar() {
		return servicio.listar();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Singer buscarId(@PathParam("id") Integer id) {
		System.out.println("asdasdd");
		return servicio.buscarId(id);
	}
}
