package it.unicam.progettoc3.vlv.controller;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import it.unicam.progettoc3.vlv.entity.enumeratori.CategorieMerceologiche;
import it.unicam.progettoc3.vlv.entity.riferimenti.PromozioneRiferimento;
import it.unicam.progettoc3.vlv.service.PromozioniService;
@RestController
@RequestMapping("/gestorePromozioni")
public class PromozioniController implements IPromozioni {

	@Autowired 
	PromozioniService promozioniService;
	
	
	@Override
	public List<PromozioneRiferimento> getPromozioni() {
		// TODO Auto-generated method stub
		return promozioniService.getPromozioni();
	}

	@PostMapping(value = "/addPromozione")
	@Override
	public ResponseEntity<String> addPromozione(@RequestBody PromozioneRiferimento promozione) {
		// TODO Auto-generated method stub
		return promozioniService.addPromozione(promozione);
	}

	

	@GetMapping(value = "/getPromozioniFiltrate")
	@Override
	public List<PromozioneRiferimento> getPromozioniFiltrate(@RequestParam CategorieMerceologiche categoriaMerceologica) {
		// TODO Auto-generated method stub
		return promozioniService.getPromozioniFiltrate(categoriaMerceologica);
	}

	@GetMapping(value = "/getPromozioniCommerciante")
	@Override
	public List<PromozioneRiferimento> getPromozioniCommerciante(@RequestParam Long idCommerciante) {
		// TODO Auto-generated method stub
		return promozioniService.getPromozioniCommerciante(idCommerciante);
	}

	@GetMapping(value = "/getPromozioniNonScadute")
	@Override
	public List<PromozioneRiferimento> getPromozioniNonScadute() {
		// TODO Auto-generated method stub
		return promozioniService.getPromozioniNonScadute();
	}



	@PutMapping(value = "/modificaPromozione")
	@Override
	public ResponseEntity<String> modificaPromozione(@RequestParam Long idPromozione, @RequestParam(required=false)String descrizione,@RequestParam(required=false) Date dataInizio,@RequestParam(required=false) Date dataFine) {
		// TODO Auto-generated method stub
		return promozioniService.modificaPromozione(idPromozione, descrizione, dataInizio, dataFine);
	}

	

}
