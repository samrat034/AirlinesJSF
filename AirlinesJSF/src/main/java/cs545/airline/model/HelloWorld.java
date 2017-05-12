package cs545.airline.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HelloWorld implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;

	public HelloWorld() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = 1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = "Samrat";
	}

}
