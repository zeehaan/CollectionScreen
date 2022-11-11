package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.ConnectionScreen;
import com.example.demo.repositories.ConnectionScreenRepositories;
import com.example.demo.service.ConnectionScreenService;

@RestController
public class UserController {
	@Autowired
	private ConnectionScreenRepositories connectionScreenRepositories; 
	@Autowired
	private ConnectionScreenService connectionScreenService; 

	@GetMapping("/getall")
	public List<HashMap<String, Object>> getStarted() {
		return  connectionScreenService.getConnection();
	}
	@PostMapping("/add")
	public ConnectionScreen addConnectionScreen(@RequestBody ConnectionScreen connectionScreen)
	{
		return connectionScreenRepositories.save(connectionScreen);
		
	}
//	@GetMapping("/get-value-from-key/{address}")
//	public String getValuefromkey(@PathVariable(value="address") String address,@RequestBody JSONObject jsonObject) {
////		ConnectionScreen connectionScreen = getStarted(1l);
////		JSONObject OBJ=connectionScreen.getScreenDetails();
//		return  connectionScreenService.getValueFromKey(jsonObject, address);
//		
//	}
//	
}
