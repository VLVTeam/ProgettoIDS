package it.unicam.progettoc3.vlv.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import it.unicam.progettoc3.vlv.entity.elementi.PuntoDiRitiro;

public interface IPuntiDiRitiro {
	
	
	public ResponseEntity<String> addPuntoDiRitiro(@RequestBody PuntoDiRitiro puntoDiRitiro);
	
	public ResponseEntity<String> removePuntoDiRitiro(@RequestParam Long idPuntoDiRitiro);
	
	public List<PuntoDiRitiro> getPuntiDiRitiro();
}
