package com.example.demo.shared;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BeamDetailsDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2800948743348773347L;
	private Thickness beamBoltDia;
	private String beamBoltGrade;
	private String beamBoltSpacing;
}
