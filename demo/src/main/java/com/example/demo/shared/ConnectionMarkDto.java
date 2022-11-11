package com.example.demo.shared;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConnectionMarkDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -583716240170923744L;

	private String basePlateOrCapPlate;
	private Thickness thickness;
	private String location;
	private String grade;
	
	
}
