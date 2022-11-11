package com.example.demo.shared;

import java.io.Serializable;

import lombok.Data;

@Data
public class FreeEndWeldDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String freeEndAtFlangeWeldType;
	private String freeEndAtFlangeWeldSize;
	private String freeEndAtWebWeldType;
	private String freeEndAtWebWeldSize;
	

}
