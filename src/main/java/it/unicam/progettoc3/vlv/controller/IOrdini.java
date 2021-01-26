package it.unicam.progettoc3.vlv.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import it.unicam.progettoc3.vlv.entity.elementi.Ordine;
import it.unicam.progettoc3.vlv.entity.utenti.Cliente;

public interface IOrdini {
	
	public ResponseEntity<String> addOrdine(Ordine ordine);
	public ResponseEntity<String> removeOrdine(Ordine ordine);
	
	public List<Ordine> getOrdiniCliente(Cliente cliente);
	
	public ResponseEntity<String> ritiraOrdine(Ordine ordine ,String codiceRitiro);	
	
}
