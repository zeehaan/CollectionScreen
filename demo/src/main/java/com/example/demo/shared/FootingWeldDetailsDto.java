package com.example.demo.shared;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FootingWeldDetailsDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 403641245166995067L;
	
	private String atFlangeWeldType;
	private Thickness atFlangeWeldSize;
	private String atWebWeldType;
	private Thickness atWebWeldSize;
	

}
