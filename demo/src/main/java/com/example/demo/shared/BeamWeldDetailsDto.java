package com.example.demo.shared;

import java.io.Serializable;

import lombok.Data;
@Data
public class BeamWeldDetailsDto implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String BeamAtFlangeWeldType;
	private Thickness BeamAtFlangeWeldSize;
	private String BeamAtWebWeldType;
	private Thickness  BeamAtWebWeldSize;
	private String allAroundaWeldType ;
	private Thickness allAroundaWeldSize;

}
