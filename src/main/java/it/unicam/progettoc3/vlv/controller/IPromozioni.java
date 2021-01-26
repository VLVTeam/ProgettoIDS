package it.unicam.progettoc3.vlv.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;

import it.unicam.progettoc3.vlv.entity.elementi.Promozione;
import it.unicam.progettoc3.vlv.entity.enumeratori.CategorieMerceologiche;

public interface IPromozioni {
	public List<Promozione> getPromozioni();
	public ResponseEntity<String> addPromozione(Promozione promozione);
	public ResponseEntity<String> removePromozione(Promozione promozione);
	public List<Promozione> getPromozioniFiltrate(CategorieMerceologiche categoriaMerceologica);
}
