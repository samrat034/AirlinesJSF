package cs545.airline.service;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cs545.airline.model.HelloWorld;

@Path("/hello")
@Produces({ MediaType.APPLICATION_JSON })
@Stateless
public class HelloWorldService {

	@GET
	public HelloWorld getHelloWorld() {
		return new HelloWorld();
	}

}
