package it.unicam.progettoc3.vlv.controller;

import java.util.Date;
import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import it.unicam.progettoc3.vlv.entity.enumeratori.CategorieMerceologiche;
import it.unicam.progettoc3.vlv.entity.riferimenti.PromozioneRiferimento;

public interface IPromozioni {
	public List<PromozioneRiferimento> getPromozioni();
	public List<PromozioneRiferimento> getPromozioniNonScadute();
	public ResponseEntity<String> addPromozione(@RequestBody PromozioneRiferimento promozione);
	public ResponseEntity<String> modificaPromozione(@RequestParam Long idPromozione , @RequestParam(required=false) String descrizione , @RequestParam(required=false) Date dataInizio , @RequestParam(required=false)Date dataFine );
	public List<PromozioneRiferimento> getPromozioniFiltrate(@RequestParam CategorieMerceologiche categoriaMerceologica);
	public List<PromozioneRiferimento> getPromozioniCommerciante(@RequestParam Long idCommerciante);
}
