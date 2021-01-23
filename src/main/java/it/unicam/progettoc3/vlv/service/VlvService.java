package it.unicam.progettoc3.vlv.service;

import org.springframework.stereotype.Service;

@Service
public class VlvService {
	
	String saluto = "hello sir ";
	String salutoNome= null;

	public String getHello() {
		// TODO Auto-generated method stub
		if(salutoNome!=null) return salutoNome;
		else return saluto;
	}

	public String setHello( String name) {
		// TODO Auto-generated method stub
		
				salutoNome = saluto + name;
				return salutoNome;
	}
	
	
	
}
