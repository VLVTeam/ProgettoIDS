package it.unicam.progettoc3.vlv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.progettoc3.vlv.service.VlvService;

@RestController
public class VlvController {
	


@Autowired
VlvService vlvService;

@GetMapping(value= "/hello")
public String getHello()
{
	
	return vlvService.getHello();
}
	
	@PostMapping(value= "/hello")
	public String setHello(@RequestParam String name)
	{
		
		 return vlvService.setHello(name);
	}
}
