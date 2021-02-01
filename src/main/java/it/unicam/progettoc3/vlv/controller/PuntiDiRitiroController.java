package it.unicam.progettoc3.vlv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.progettoc3.vlv.entity.elementi.PuntoDiRitiro;
import it.unicam.progettoc3.vlv.service.PuntiDiRitiroService;

@RestController
@RequestMapping("/gestorePuntiDiRitiro")
public class PuntiDiRitiroController implements IPuntiDiRitiro {

	@Autowired
	PuntiDiRitiroService puntiDiRitiroService;
	@PostMapping(value="/addPuntoDiRitiro")
	@Override
	public ResponseEntity<String> addPuntoDiRitiro(@RequestBody PuntoDiRitiro puntoDiRitiro) {
		// TODO Auto-generated method stub
		return puntiDiRitiroService.addPuntoDiRitiro(puntoDiRitiro);
	}

	@DeleteMapping(value="/removePuntoDiRitiro")
	@Override
	public ResponseEntity<String> removePuntoDiRitiro(@RequestParam Long idPuntoDiRitiro) {
		// TODO Auto-generated method stub
		return puntiDiRitiroService.removePuntoDiRitiro(idPuntoDiRitiro);
	}

	@GetMapping(value="/getPuntiDiRitiro")
	@Override
	public List<PuntoDiRitiro> getPuntiDiRitiro() {
		// TODO Auto-generated method stub
		return puntiDiRitiroService.getPuntiDiRitiro();
	}

}
