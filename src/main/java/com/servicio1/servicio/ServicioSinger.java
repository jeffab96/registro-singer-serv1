package com.servicio1.servicio;

import java.util.List;

import com.servicio1.dto.Singer;

public interface ServicioSinger  {
	public List<Singer> listar();
	Singer buscarId(Integer idSinger);
}
