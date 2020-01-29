package com.servicio1.dto;

import java.io.Serializable;
import java.util.Date;


import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
//@XmlRootElement(name = "singers")
public class Singer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private Integer version;
	
	public Singer() {
	}

	public Singer(String firstName, String lastName, Date  birthDate, Integer version) {
		this.firstName = firstName;
		this.lastName =  lastName;
		this.birthDate =  birthDate;
		this.version = version;
		
	}

	

}
