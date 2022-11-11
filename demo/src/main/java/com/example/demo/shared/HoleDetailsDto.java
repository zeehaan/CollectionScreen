package com.example.demo.shared;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class HoleDetailsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -583716240170923744L;

	private Thickness holeDia;
	private String noOfHoles;
	private String ParallelToWeb;

}
