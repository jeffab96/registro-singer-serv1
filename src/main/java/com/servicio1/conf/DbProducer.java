package com.servicio1.conf;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

@ApplicationScoped
public class DbProducer {

	private static final String DRIVER_CLASS = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/examen01";
	private static final String USER = "distribuida";
	private static final String PASS = "distribuida";

	@ApplicationScoped
	@Produces
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(DRIVER_CLASS);
		ds.setUrl(URL);
		ds.setUsername(USER);
		ds.setPassword(PASS);
		return ds;
	}
}
