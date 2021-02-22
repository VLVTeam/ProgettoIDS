package it.unicam.progettoc3.vlv;

import com.fasterxml.jackson.databind.ObjectMapper;

public class C3Test {
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}