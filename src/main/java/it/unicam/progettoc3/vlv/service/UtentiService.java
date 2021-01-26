package it.unicam.progettoc3.vlv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.unicam.progettoc3.vlv.controller.IUtenti;
import it.unicam.progettoc3.vlv.entity.enumeratori.CategorieMerceologiche;
import it.unicam.progettoc3.vlv.entity.utenti.Cliente;
import it.unicam.progettoc3.vlv.entity.utenti.ClienteRepository;
import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;
import it.unicam.progettoc3.vlv.entity.utenti.CommercianteRepository;
import it.unicam.progettoc3.vlv.entity.utenti.Corriere;
import it.unicam.progettoc3.vlv.entity.utenti.CorriereRepository;

@Service
public class UtentiService implements IUtenti {

	@Autowired
	CommercianteRepository commercianteRepository;
	
	@Autowired
	CorriereRepository corriereRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	

	@Override
	public ResponseEntity<String> setStatoIscrizioneCommerciante(Commerciante commerciante, boolean stato) {
		// TODO Auto-generated method stub
		
		
			Optional<Commerciante> optionalCommerciante = commercianteRepository.findById(commerciante.getID());
		if(	optionalCommerciante.isPresent()){
			Commerciante commercianteModificato = optionalCommerciante.get();
			
			commercianteModificato.setStatoIscrizione(stato);
			commercianteRepository.save(commercianteModificato);
			return new ResponseEntity<String>(" STATO COMMERCIANTE MODIFICATO" , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("COMMERCIANTE NON TROVATO , IMPOSSIBILE MODIFICARE STATO" , HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> setStatoIscrizioneCorriere(Corriere corriere, boolean stato) {
		// TODO Auto-generated method stub
		Optional<Corriere> optionalCorriere = corriereRepository.findById(corriere.getID());
		if(	optionalCorriere.isPresent()){
			Corriere corriereModificato = optionalCorriere.get();
			
			corriereModificato.setStatoIscrizione(stato);
			corriereRepository.save(corriereModificato);
			return new ResponseEntity<String>(" STATO CORRIERE MODIFICATO" , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("CORRIERE NON TROVATO , IMPOSSIBILE MODIFICARE STATO" , HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> addCommerciante(Commerciante commerciante) {
		// TODO Auto-generated method stub
	
	try {
		commercianteRepository.save(commerciante);
		return new ResponseEntity<String>("COMMERCIANTE AGGIUNTO" , HttpStatus.OK);
	} catch (IllegalArgumentException e) {
		// TODO: handle exception
		return new ResponseEntity<String>("CONTROLLARE CAMPI COMMERCIANTE" , HttpStatus.NOT_ACCEPTABLE);
	}
	
	}

	@Override
	public ResponseEntity<String> addCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		try {
			clienteRepository.save(cliente);
			return new ResponseEntity<String>("CLIENTE AGGIUNTO" , HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return new ResponseEntity<String>("CONTROLLARE CAMPI CLIENTE" , HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> addCorriere(Corriere corriere) {
		// TODO Auto-generated method stub

		try {
			corriereRepository.save(corriere);
			return new ResponseEntity<String>("CORRIERE AGGIUNTO" , HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return new ResponseEntity<String>("CONTROLLARE CAMPI CORRIERE" , HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public List<Commerciante> getPuntiVenditaFiltrati(CategorieMerceologiche categoriaMerceologica) {
		// TODO Auto-generated method stub

		List<Commerciante> commercianti= getPuntiVendita();
		
		commercianti.forEach(Commerciante ->
		{
			if(Commerciante.getCategoriaMerceologica()!=categoriaMerceologica){commercianti.remove(Commerciante.getID());}
		});
		if(commercianti.isEmpty()){return null;}
		else{
			return commercianti;
			}
	}

	@Override
	public List<Commerciante> getPuntiVendita() {
		// TODO Auto-generated method stub
		List<Commerciante> commercianti= new ArrayList<>();
		Iterable<Commerciante> iteratore = commercianteRepository.findAll();
		
		iteratore.forEach(Commerciante -> commercianti.add(Commerciante));
		if (commercianti.isEmpty()){return null;}
		else{return commercianti;}
	}

	@Override
	public List<Commerciante> getCommerciantiDaAccettare() {
		// TODO Auto-generated method stub
		
		List<Commerciante> commercianti=getPuntiVendita();
		
		commercianti.forEach(Commerciante ->
		{
			if(Commerciante.isStatoIscrizione()){commercianti.remove(Commerciante.getID());}
		});
		if(commercianti.isEmpty()){return null;}
		else{
			return commercianti;
			}
	}

	@Override
	public List<Corriere> getCorrieri() {
		// TODO Auto-generated method stub
		List<Corriere> corrieri= new ArrayList<>();
		Iterable<Corriere> iteratore = corriereRepository.findAll();
		
		iteratore.forEach(Corriere -> corrieri.add(Corriere));
		if (corrieri.isEmpty()){return null;}
		else{return corrieri;}
	}

	@Override
	public List<Corriere> getCorrieriDaAccettare() {
		// TODO Auto-generated method stub
		List<Corriere> corrieri=getCorrieri();
		
		corrieri.forEach(Corriere ->
		{
			if(Corriere.isStatoIscrizione()){corrieri.remove(Corriere.getID());}
		});
		if(corrieri.isEmpty()){return null;}
		else{
			return corrieri;
			}
	}

	@Override
	public List<Cliente> getClienti() {
		// TODO Auto-generated method stub
		List<Cliente> clienti= new ArrayList<>();
		Iterable<Cliente> iteratore = clienteRepository.findAll();
		
		iteratore.forEach(Cliente -> clienti.add(Cliente));
		if (clienti.isEmpty()){return null;}
		else{return clienti;}
	}

}
