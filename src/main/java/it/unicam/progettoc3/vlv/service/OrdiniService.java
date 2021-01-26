package it.unicam.progettoc3.vlv.service;

import java.util.ArrayList;

import java.util.List;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.unicam.progettoc3.vlv.controller.IOrdini;
import it.unicam.progettoc3.vlv.entity.elementi.Ordine;
import it.unicam.progettoc3.vlv.entity.elementi.OrdineRepository;
import it.unicam.progettoc3.vlv.entity.enumeratori.StatoOrdine;
import it.unicam.progettoc3.vlv.entity.utenti.Cliente;
import it.unicam.progettoc3.vlv.entity.utenti.ClienteRepository;
import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;
import it.unicam.progettoc3.vlv.entity.utenti.CommercianteRepository;

@Service
public class OrdiniService implements IOrdini{
	
	@Autowired
	CommercianteRepository commercianteRepository;

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	OrdineRepository ordineRepository;
	
	@Override
	public ResponseEntity<String> removeOrdine(Ordine ordine) {
		// TODO Auto-generated method stub
		List<Ordine> ordini= new ArrayList<>();
		ordini = getOrdiniCliente(ordine.getCliente());
		if(ordini.isEmpty())
		{
			return new ResponseEntity<String>("Ordine Non Trovato , Impossibile rimuovere" , HttpStatus.NOT_ACCEPTABLE);
		}
		else if(ordini.contains(ordine))
		{
			ordini.remove(ordine.getID());
			return new ResponseEntity<String>("Ordine Rimosso" , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Ordine Non Trovato , Impossibile rimuovere" , HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public List<Ordine> getOrdiniCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		Long idCliente = cliente.getID();
		List<Ordine> ordini= new ArrayList<>();
		Iterable<Ordine> iteratore = ordineRepository.findAll();
		iteratore.forEach(Ordine -> ordini.add(Ordine));
		if(ordini.isEmpty()) {return null;}
		
		
		ordini.forEach(Ordine ->
		{
			if(Ordine.getCliente().getID()!=idCliente){ordini.remove(Ordine.getID());}
		});
	if( ordini.isEmpty()){return null;}	
	return ordini;
	}

	@Override
	public ResponseEntity<String> addOrdine(Ordine ordine) {
		// TODO Auto-generated method stub
		
			//controllo validita idCommerciante
			Optional<Commerciante> optionalCommerciante = commercianteRepository.findById(ordine.getCommerciante().getID());
			if(!optionalCommerciante.isPresent()) {  return new ResponseEntity<String>("Commerciante non trovato" , HttpStatus.BAD_REQUEST);}
			
			//controllo validita idCliente
			Optional<Cliente> optionalCliente = clienteRepository.findById(ordine.getCliente().getID());
			if(!optionalCliente.isPresent()) { return new ResponseEntity<String>("Cliente non trovato" , HttpStatus.BAD_REQUEST);}
		
		
		
		
		try {
			ordineRepository.save(ordine);
			return new ResponseEntity<String>("ORDINE AGGIUNTO" , HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return new ResponseEntity<String>("CONTROLLARE CAMPI ORDINE" , HttpStatus.NOT_ACCEPTABLE);
		}
		
		
	}

	@Override
	public ResponseEntity<String> ritiraOrdine(Ordine ordine , String codiceRitiro) {
		// TODO Auto-generated method stub
		
		
		
	Optional<Ordine> optionalOrdine= 	ordineRepository.findById(ordine.getID());
		if(!optionalOrdine.isPresent()){	
			return new ResponseEntity<String>("ORDINE NON TROVATO , IMPOSSIBILE RITIRARE" , HttpStatus.BAD_REQUEST);
		}
		else	if(ordine.getCodiceRitiro() == codiceRitiro)
		{
			
		
			Ordine ordineConsegnato = optionalOrdine.get();
			
			ordineConsegnato.setStato(StatoOrdine.CONSEGNATO);
			ordineRepository.save(ordineConsegnato);
			return new ResponseEntity<String>("ORDINE RITIRATO" , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("CODICE RITIRO ERRATO , IMPOSSIBILE RITIRARE" , HttpStatus.NOT_ACCEPTABLE);
		}
		
		
	}



}
