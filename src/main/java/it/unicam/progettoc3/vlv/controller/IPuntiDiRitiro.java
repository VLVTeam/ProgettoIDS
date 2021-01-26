package it.unicam.progettoc3.vlv.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import it.unicam.progettoc3.vlv.entity.elementi.PuntoDiRitiro;

public interface IPuntiDiRitiro {
	
	
	public ResponseEntity<String> addPuntoDiRitiro(PuntoDiRitiro puntoDiRitiro);
	
	public ResponseEntity<String> removePuntoDiRitiro(PuntoDiRitiro puntoDiRitiro);
	
	public List<PuntoDiRitiro> getPuntiDiRitiro();
}
