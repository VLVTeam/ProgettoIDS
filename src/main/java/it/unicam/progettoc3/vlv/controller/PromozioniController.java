package it.unicam.progettoc3.vlv.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.progettoc3.vlv.entity.elementi.Promozione;
import it.unicam.progettoc3.vlv.entity.enumeratori.CategorieMerceologiche;
import it.unicam.progettoc3.vlv.service.PromozioniService;
@RestController
public class PromozioniController implements IPromozioni {

	@Autowired 
	PromozioniService promozioniService;
	
	@GetMapping(value = "/getPromozioni")
	@Override
	public List<Promozione> getPromozioni() {
		// TODO Auto-generated method stub
		return promozioniService.getPromozioni();
	}

	@PutMapping(value = "/addPromozione")
	@Override
	public ResponseEntity<String> addPromozione(@RequestParam Promozione promozione) {
		// TODO Auto-generated method stub
		return promozioniService.addPromozione(promozione);
	}

	@DeleteMapping(value = "/removePromozione")
	@Override
	public ResponseEntity<String> removePromozione(@RequestParam Promozione promozione) {
		// TODO Auto-generated method stub
		return  promozioniService.removePromozione(promozione);
	}

	@PostMapping(value = "/getPromozioniFiltrate")
	@Override
	public List<Promozione> getPromozioniFiltrate(@RequestParam CategorieMerceologiche categoriaMerceologica) {
		// TODO Auto-generated method stub
		return promozioniService.getPromozioniFiltrate(categoriaMerceologica);
	}

	

}
