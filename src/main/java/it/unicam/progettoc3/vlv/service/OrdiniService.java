package it.unicam.progettoc3.vlv.service;

import java.util.Date;
import java.util.ArrayList;

import java.util.List;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.unicam.progettoc3.vlv.controller.IOrdini;
import it.unicam.progettoc3.vlv.entity.elementi.Ordine;
import it.unicam.progettoc3.vlv.entity.elementi.PuntoDiRitiro;
import it.unicam.progettoc3.vlv.entity.enumeratori.StatiOrdine;
import it.unicam.progettoc3.vlv.entity.riferimenti.OrdineRiferimento;
import it.unicam.progettoc3.vlv.entity.utenti.Cliente;
import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;
import it.unicam.progettoc3.vlv.entity.utenti.Corriere;
import it.unicam.progettoc3.vlv.repository.ClienteRepository;
import it.unicam.progettoc3.vlv.repository.CommercianteRepository;
import it.unicam.progettoc3.vlv.repository.CorriereRepository;
import it.unicam.progettoc3.vlv.repository.OrdineRepository;
import it.unicam.progettoc3.vlv.repository.PuntoDiRitiroRepository;

@Service
public class OrdiniService implements IOrdini{
	
	@Autowired
	CommercianteRepository commercianteRepository;

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	OrdineRepository ordineRepository;
	
	@Autowired
	CorriereRepository corriereRepository;
	
	@Autowired
	PuntoDiRitiroRepository puntoDiRitiroRepository;
	
	

	@Override
	public List<OrdineRiferimento> getOrdiniCliente(Long idCliente) {
		// TODO Auto-generated method stub

		
		List<Ordine> ordini= new ArrayList<>();
		Iterable<Ordine> iteratore = ordineRepository.findAll();
		iteratore.forEach(Ordine -> ordini.add(Ordine));
		
	if( ordini.isEmpty()){return null;}

		
			List<OrdineRiferimento> ordiniRiferimento = new ArrayList<>();
			 
			
			
			ordini.forEach(Ordine ->
			{
				
				if(Ordine.getCliente().getID()==idCliente){

				
				Long idCorriere;
				Long idPuntoDiRitiro;
				if(Ordine.getCorriere()==null){idCorriere =null;}
				else{	idCorriere =Ordine.getCorriere().getID();}
				
				if(Ordine.getPuntoDiRitiro()==null){idPuntoDiRitiro=null;}
				else{idPuntoDiRitiro=Ordine.getPuntoDiRitiro().getID();}
				
				OrdineRiferimento ordineRiferimento =  new OrdineRiferimento(Ordine.getID(), Ordine.getCodiceRitiro(), Ordine.getDescrizione(), Ordine.getStato(), Ordine.getCommerciante().getID(), Ordine.getCliente().getID(), idCorriere, idPuntoDiRitiro, Ordine.getDataCreazione(), Ordine.getDataRitiro(), Ordine.getDataConsegnaPrevista());
				ordiniRiferimento.add(ordineRiferimento);
				}
			});	
		
			if(ordiniRiferimento.isEmpty()){ return null;}
			else{return ordiniRiferimento;}

	}

	@Override
	public ResponseEntity<String> addOrdine(OrdineRiferimento ordine) {
		// TODO Auto-generated method stub
		
			//controllo validita idCommerciante
			Optional<Commerciante> optionalCommerciante = commercianteRepository.findById(ordine.getIdCommerciante());
			if(!optionalCommerciante.isPresent()) {  return new ResponseEntity<String>("Commerciante non trovato" , HttpStatus.BAD_REQUEST);}
			
			//controllo validita idCliente
			Optional<Cliente> optionalCliente = clienteRepository.findById(ordine.getIdCliente());
			if(!optionalCliente.isPresent()) { return new ResponseEntity<String>("Cliente non trovato" , HttpStatus.BAD_REQUEST);}
		
		
			Commerciante commerciante = optionalCommerciante.get();
			Cliente cliente = optionalCliente.get();
			PuntoDiRitiro puntoDiRitiro = null;
			if(ordine.getIdPuntoDiRitiro()!=null){
			Optional<PuntoDiRitiro> optinalPuntoDiRitiro = puntoDiRitiroRepository.findById(ordine.getIdPuntoDiRitiro());
				if(optinalPuntoDiRitiro.isPresent())
				{
					puntoDiRitiro=optinalPuntoDiRitiro.get();
				}
			}
			Ordine ordineSave ;
			if(puntoDiRitiro==null){
			ordineSave= new Ordine(ordine.getCodiceRitiro(), ordine.getDescrizione(), commerciante, cliente);
			}
			else
			{
				ordineSave= new Ordine(ordine.getCodiceRitiro(), ordine.getDescrizione(), commerciante, cliente, puntoDiRitiro);
			}
			ordineRepository.save(ordineSave);
			return new ResponseEntity<String>("ORDINE AGGIUNTO" , HttpStatus.OK);
		
		
		
	}

	@Override
	public ResponseEntity<String> ritiraOrdine(Long idOrdine , String codiceRitiro) {
		// TODO Auto-generated method stub
		
		
		
	Optional<Ordine> optionalOrdine= 	ordineRepository.findById(idOrdine);
		if(!optionalOrdine.isPresent()){	
			return new ResponseEntity<String>("ORDINE NON TROVATO , IMPOSSIBILE RITIRARE" , HttpStatus.BAD_REQUEST);
		}
	
		else if (optionalOrdine.get().getStato()!= StatiOrdine.PRONTO_PER_IL_RITIRO)
		{
			return new ResponseEntity<String>("L'ORDINE NON E' NEL PUNTO DI RITIRO , IMPOSSIBILE RITIRARE" , HttpStatus.NOT_ACCEPTABLE);
		}
		else	if(optionalOrdine.get().getCodiceRitiro() == codiceRitiro)
		{
			
		
			Ordine ordine = optionalOrdine.get();
			
			ordine.setStato(StatiOrdine.CONSEGNATO);
			ordineRepository.save(ordine);
			return new ResponseEntity<String>("ORDINE RITIRATO" , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("CODICE RITIRO ERRATO , IMPOSSIBILE RITIRARE" , HttpStatus.NOT_ACCEPTABLE);
		}
		
		
	}

	@Override
	public List<OrdineRiferimento> getOrdiniLiberi() {
		// TODO Auto-generated method stub
		List<Ordine> ordini= new ArrayList<>();
		Iterable<Ordine> iteratore = ordineRepository.findAll();
		iteratore.forEach(Ordine -> ordini.add(Ordine));
		
		
		if( ordini.isEmpty()){return null;}

		
		List<OrdineRiferimento> ordiniRiferimento = new ArrayList<>();
		 
		
		
		ordini.forEach(Ordine ->
		{
			
			if(Ordine.getStato()==StatiOrdine.IN_ACCETTAZIONE){

			
			Long idCorriere;
			Long idPuntoDiRitiro;
			if(Ordine.getCorriere()==null){idCorriere =null;}
			else{	idCorriere =Ordine.getCorriere().getID();}
			
			if(Ordine.getPuntoDiRitiro()==null){idPuntoDiRitiro=null;}
			else{idPuntoDiRitiro=Ordine.getPuntoDiRitiro().getID();}
			
			OrdineRiferimento ordineRiferimento =  new OrdineRiferimento(Ordine.getID(), Ordine.getCodiceRitiro(), Ordine.getDescrizione(), Ordine.getStato(), Ordine.getCommerciante().getID(), Ordine.getCliente().getID(), idCorriere, idPuntoDiRitiro, Ordine.getDataCreazione(), Ordine.getDataRitiro(), Ordine.getDataConsegnaPrevista());
			ordiniRiferimento.add(ordineRiferimento);
			}
		});	
	
		if(ordiniRiferimento.isEmpty()){ return null;}
		else{return ordiniRiferimento;}
	}

	@Override
	public List<OrdineRiferimento> getOrdiniDaRitirare(Long idCorriere) {
		// TODO Auto-generated method stub
		List<Ordine> ordini= new ArrayList<>();
		Iterable<Ordine> iteratore = ordineRepository.findAll();
		iteratore.forEach(Ordine -> ordini.add(Ordine));
				
if( ordini.isEmpty()){return null;}

		
		List<OrdineRiferimento> ordiniRiferimento = new ArrayList<>();
		 
		
		
		ordini.forEach(Ordine ->
		{
			
			if(Ordine.getStato()==StatiOrdine.IN_RITIRO && Ordine.getCorriere().getID()==idCorriere){

			
			
			Long idPuntoDiRitiro;		
			
			if(Ordine.getPuntoDiRitiro()==null){idPuntoDiRitiro=null;}
			else{idPuntoDiRitiro=Ordine.getPuntoDiRitiro().getID();}
			
			OrdineRiferimento ordineRiferimento =  new OrdineRiferimento(Ordine.getID(), Ordine.getCodiceRitiro(), Ordine.getDescrizione(), Ordine.getStato(), Ordine.getCommerciante().getID(), Ordine.getCliente().getID(), idCorriere, idPuntoDiRitiro, Ordine.getDataCreazione(), Ordine.getDataRitiro(), Ordine.getDataConsegnaPrevista());
			ordiniRiferimento.add(ordineRiferimento);
			}
		});	
	
		if(ordiniRiferimento.isEmpty()){ return null;}
		else{return ordiniRiferimento;}
	}

	@Override
	public List<OrdineRiferimento> getOrdiniInTransito(Long idCorriere) {
		// TODO Auto-generated method stub
		List<Ordine> ordini= new ArrayList<>();
		Iterable<Ordine> iteratore = ordineRepository.findAll();
		iteratore.forEach(Ordine -> ordini.add(Ordine));
		
		
if( ordini.isEmpty()){return null;}

		
		List<OrdineRiferimento> ordiniRiferimento = new ArrayList<>();
		 
		
		
		ordini.forEach(Ordine ->
		{
			
			if(Ordine.getStato()==StatiOrdine.IN_TRANSITO && Ordine.getCorriere().getID()==idCorriere){

			
			
			Long idPuntoDiRitiro;		
			
			if(Ordine.getPuntoDiRitiro()==null){idPuntoDiRitiro=null;}
			else{idPuntoDiRitiro=Ordine.getPuntoDiRitiro().getID();}
			
			OrdineRiferimento ordineRiferimento =  new OrdineRiferimento(Ordine.getID(), Ordine.getCodiceRitiro(), Ordine.getDescrizione(), Ordine.getStato(), Ordine.getCommerciante().getID(), Ordine.getCliente().getID(), idCorriere, idPuntoDiRitiro, Ordine.getDataCreazione(), Ordine.getDataRitiro(), Ordine.getDataConsegnaPrevista());
			ordiniRiferimento.add(ordineRiferimento);
			}
		});	
	
		if(ordiniRiferimento.isEmpty()){ return null;}
		else{return ordiniRiferimento;}
	}

	@Override
	public ResponseEntity<String> setPresaInCaricoOrdine(Long idOrdine,Long idCorriere , Date dataPrevistaRitiro) {
		// TODO Auto-generated method stub
		
		Optional<Ordine> optionalOrdine= 	ordineRepository.findById(idOrdine);
		if(!optionalOrdine.isPresent()){
			return new ResponseEntity<String>("ORDINE NON TROVATO , IMPOSSIBILE RITIRARE" , HttpStatus.BAD_REQUEST);
		}
		
		Optional<Corriere> optionalCorriere= 	corriereRepository.findById(idCorriere);
		 if(!optionalCorriere.isPresent())
		{
				return new ResponseEntity<String>("CORRIERE NON TROVATO , IMPOSSIBILE RITIRARE" , HttpStatus.BAD_REQUEST);

		}
		 if(optionalOrdine.get().getStato()==StatiOrdine.IN_ACCETTAZIONE && dataPrevistaRitiro!=null)
		{
			 Ordine ordine = optionalOrdine.get();
			ordine.setStato(StatiOrdine.IN_RITIRO);
			ordine.setDataRitiro(dataPrevistaRitiro);
			Corriere corriere = optionalCorriere.get();
			ordine.setCorriere(corriere);
			ordineRepository.save(ordine);
			return new ResponseEntity<String>("ORDINE PRESO IN CARICO" , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("IMPOSSIBILE PRENDERE IN CARICO" , HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> setDataConsegnaPrevista(Long idOrdine, Date dataConsegnaPrevista) {
		// TODO Auto-generated method stub
		Optional<Ordine> optionalOrdine= 	ordineRepository.findById(idOrdine);
		if(!optionalOrdine.isPresent()){
			return new ResponseEntity<String>("ORDINE NON TROVATO , IMPOSSIBILE RITIRARE" , HttpStatus.BAD_REQUEST);
		}
		if(optionalOrdine.get().getStato()==StatiOrdine.IN_RITIRO && dataConsegnaPrevista!=null)
		{
			Ordine ordine = optionalOrdine.get();
			ordine.setStato(StatiOrdine.IN_TRANSITO);
			ordine.setDataConsegnaPrevista(dataConsegnaPrevista);
			ordineRepository.save(ordine);
			return new ResponseEntity<String>("DATA CONSEGNA PREVISTA IMPOSTATA" , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("IMPOSSIBILE IMPOSTARE DATA CONSEGNA PREVISTA" , HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> setOrdineConsegnato(Long idOrdine) {
		// TODO Auto-generated method stub
		
		Optional<Ordine> optionalOrdine= 	ordineRepository.findById(idOrdine);
		if(!optionalOrdine.isPresent()){
			return new ResponseEntity<String>("ORDINE NON TROVATO , IMPOSSIBILE RITIRARE" , HttpStatus.BAD_REQUEST);
		}
		
		if(optionalOrdine.get().getStato()==StatiOrdine.IN_TRANSITO && optionalOrdine.get().getPuntoDiRitiro() == null)
		{
			Ordine ordine = optionalOrdine.get();
			ordine.setStato(StatiOrdine.CONSEGNATO);
			
			ordineRepository.save(ordine);
			return new ResponseEntity<String>("ORDINE CONSEGNATO" , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("IMPOSSIBILE CONSEGNARE ORDINE" , HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> setOrdineProntoPerIlRitiro(Long idOrdine) {
		// TODO Auto-generated method stub
		
		Optional<Ordine> optionalOrdine= 	ordineRepository.findById(idOrdine);
		if(!optionalOrdine.isPresent()){
			return new ResponseEntity<String>("ORDINE NON TROVATO , IMPOSSIBILE RITIRARE" , HttpStatus.BAD_REQUEST);
		}
		
		if(optionalOrdine.get().getStato()==StatiOrdine.IN_TRANSITO && optionalOrdine.get().getPuntoDiRitiro()!=null)
		{
			Ordine ordine = optionalOrdine.get();
			ordine.setStato(StatiOrdine.PRONTO_PER_IL_RITIRO);
			ordineRepository.save(ordine);
			
			return new ResponseEntity<String>("ORDINE DEPOSITATO PRESSO IL PUNTO DI RITIRO" , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("IMPOSSIBILE DEPOSITARE  ORDINE PRESSO IL PUNTO DI RITIRO" , HttpStatus.NOT_ACCEPTABLE);
		}
	}



}
