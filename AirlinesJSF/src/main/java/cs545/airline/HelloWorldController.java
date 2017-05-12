package cs545.airline;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.HelloWorld;

@Named
@RequestScoped

public class HelloWorldController {

	@Inject
	private HelloWorld hello;

	public HelloWorldController() {

	}

	public HelloWorld getHello() {
		return hello;
	}

	public void setHello(HelloWorld hello) {
		this.hello = hello;
	}

	public HelloWorld getAllHelloWorld() {
		return hello;
	}
}
