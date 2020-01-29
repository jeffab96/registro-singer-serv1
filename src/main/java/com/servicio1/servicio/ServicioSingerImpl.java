package com.servicio1.servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import com.servicio1.dto.Singer;

@ApplicationScoped
public class ServicioSingerImpl implements ServicioSinger {
@Inject
private DataSource dataSource;
		
@Inject
private Singer sing;

	public List<Singer> listar() {

		String sql = "select * from singer";

		List<Singer> listSinger = new ArrayList<Singer>();
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement sentencia = connection.prepareStatement(sql);
			ResultSet datos = sentencia.executeQuery();
			while (datos.next()) {
				sing = new Singer();
				sing.setFirstName(datos.getString(1));
				sing.setLastName(datos.getString(2));
				sing.setBirthDate(datos.getDate(3));
				sing.setId(datos.getInt(4));
				sing.setVersion(datos.getInt(5));
				listSinger.add(sing);
			}
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return listSinger;

	}


	@Override
	public Singer buscarId(Integer id) {
		System.out.println("prueba1");
		Singer singer = new Singer();
		String query = "SELECT * FROM singer WHERE id= ?";
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet resultado = pst.executeQuery();
			while (resultado.next()) {
				singer.setFirstName(resultado.getString(1));
				singer.setLastName(resultado.getString(2));
				singer.setBirthDate(resultado.getDate(3));
				singer.setId(resultado.getInt(4));
				singer.setVersion(resultado.getInt(5));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return singer;
	}





}
