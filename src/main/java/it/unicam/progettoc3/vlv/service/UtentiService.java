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
import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;
import it.unicam.progettoc3.vlv.entity.utenti.Corriere;
import it.unicam.progettoc3.vlv.repository.ClienteRepository;
import it.unicam.progettoc3.vlv.repository.CommercianteRepository;
import it.unicam.progettoc3.vlv.repository.CorriereRepository;

@Service
public class UtentiService implements IUtenti {

	@Autowired
	CommercianteRepository commercianteRepository;
	
	@Autowired
	CorriereRepository corriereRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	

	@Override
	public ResponseEntity<String> setStatoIscrizioneCommerciante(Long idCommerciante, boolean stato) {
		// TODO Auto-generated method stub
		
		
			Optional<Commerciante> optionalCommerciante = commercianteRepository.findById(idCommerciante);
		if(	optionalCommerciante.isPresent()){
			Commerciante commerciante = optionalCommerciante.get();
			if(commerciante.isStatoIscrizione()!=stato){
			commerciante.setStatoIscrizione(stato);
			commercianteRepository.save(commerciante);
			
			return new ResponseEntity<String>(" STATO COMMERCIANTE MODIFICATO" , HttpStatus.OK);
			}
			else 
			{
				return new ResponseEntity<String>(" IL COMMERCIANTE E GIA IN QUESTO STATO" , HttpStatus.OK);
			}
			 
		}
		else
		{
			return new ResponseEntity<String>("COMMERCIANTE NON TROVATO , IMPOSSIBILE MODIFICARE STATO" , HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> setStatoIscrizioneCorriere(Long idCorriere, boolean stato) {
		// TODO Auto-generated method stub
		Optional<Corriere> optionalCorriere = corriereRepository.findById(idCorriere);
		if(	optionalCorriere.isPresent()){
			Corriere corriere = optionalCorriere.get();
			if(corriere.isStatoIscrizione()!=stato){
			corriere.setStatoIscrizione(stato);
			corriereRepository.save(corriere);
			
			return new ResponseEntity<String>(" STATO CORRIERE MODIFICATO" , HttpStatus.OK);
			}
			else 
			{
				return new ResponseEntity<String>(" IL CORRIERE E GIA IN QUESTO STATO" , HttpStatus.OK);
			}
		}
		else
		{
			return new ResponseEntity<String>("CORRIERE NON TROVATO , IMPOSSIBILE MODIFICARE STATO" , HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> addCommerciante(Commerciante commerciante) {
		// TODO Auto-generated method stub
		if(commercianteRepository.existsByEmail(commerciante.getEmail()))
		{
			return new ResponseEntity<String>("EMAIL GIA ASSOCIATA AD UN ALTRO COMMERCIANTE" , HttpStatus.NOT_ACCEPTABLE);
		}
		else{
			commercianteRepository.save(commerciante);
		return new ResponseEntity<String>("COMMERCIANTE AGGIUNTO" , HttpStatus.OK);
		}
		
	}

	@Override
	public ResponseEntity<String> addCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		
		if(clienteRepository.existsByEmail(cliente.getEmail()))
		{
			return new ResponseEntity<String>("EMAIL GIA ASSOCIATA AD UN ALTRO CLIENTE" , HttpStatus.NOT_ACCEPTABLE);
		}
	
			
			clienteRepository.save(cliente);
			return new ResponseEntity<String>("CLIENTE AGGIUNTO" , HttpStatus.OK);
		
		
	}

	@Override
	public ResponseEntity<String> addCorriere(Corriere corriere) {
		// TODO Auto-generated method stub
		if(corriereRepository.existsByEmail(corriere.getEmail()))
		{
			return new ResponseEntity<String>("EMAIL GIA ASSOCIATA AD UN ALTRO CORRIERE" , HttpStatus.NOT_ACCEPTABLE);
		}
			corriereRepository.save(corriere);
			return new ResponseEntity<String>("CORRIERE AGGIUNTO" , HttpStatus.OK);
		
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
