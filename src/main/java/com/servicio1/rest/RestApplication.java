package com.servicio1.rest;

import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;

@ApplicationPath(value = "/")
@ApplicationScoped
public class RestApplication  extends Application{

	private String ID;

	@PostConstruct
	public void inicializar( ) {
		
	UUID uuid = UUID.randomUUID();
	ID = uuid.toString();
	}
	
	@Inject
	@ConfigProperty(name="server.port", defaultValue = "7074")
	private Integer puerto;
	
	@Inject
	@ConfigProperty(name="consul.server", defaultValue = "127.0.0.1")
	private String consulHost;
	
	@Inject
	@ConfigProperty(name="consul.port", defaultValue = "8500")
	private Integer consulPort;
	
	@Override
	public Set<Class<?>> getClasses() {
		return Set.of(ServicioSingerRest.class, PingRest.class);
	}
	
	public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
		System.out.println("Inicializando");
		
		//ConsulClient client = new ConsulClient(consulHost, consulPort);
		ConsulClient client = new ConsulClient();
		NewService i = new NewService();
		
		i.setName("singer-conf");
		i.setId( ID);
		i.setAddress( "127.0.0.1" );
		i.setPort( puerto );
		
		NewService.Check check = new NewService.Check();
		check.setMethod("GET");
		check.setHttp("http://127.0.0.1:"+puerto+"/ping");
		check.setInterval("10s");
		check.setDeregisterCriticalServiceAfter("20s");
		
		i.setCheck(check);
		
		client.agentServiceRegister(i);
	}


	public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init) {
		System.out.println("Finalizando");
		ConsulClient client = new ConsulClient();
		client.agentServiceDeregister( ID );
	}
	
}
