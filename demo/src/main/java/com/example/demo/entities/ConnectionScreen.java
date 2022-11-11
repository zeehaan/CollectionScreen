package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionScreen {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long slNo;
	@Column(columnDefinition = "json")
	private String screenDetails;

}
