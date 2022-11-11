package com.example.demo.shared;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Thickness implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1404262444006441596L;
	private String inch;
	private String mm;
	private String fraction;

}
