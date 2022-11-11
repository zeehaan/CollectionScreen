package com.example.demo.shared;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class AnchorDetailsDto implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 403641245166995067L;
		
		private String type;
		private Thickness anchorRodDia;
		private String anchorRodGrade;
}
