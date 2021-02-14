package it.unicam.progettoc3.vlv.service;

import java.util.Date;
import java.util.ArrayList;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unicam.progettoc3.vlv.entity.dto.NuovoOrdine;
import it.unicam.progettoc3.vlv.entity.elementi.Ordine;
import it.unicam.progettoc3.vlv.entity.elementi.PuntoDiRitiro;
import it.unicam.progettoc3.vlv.entity.enumeratori.NomiRuoli;
import it.unicam.progettoc3.vlv.entity.enumeratori.StatiOrdine;
import it.unicam.progettoc3.vlv.entity.utenti.Cliente;
import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;
import it.unicam.progettoc3.vlv.entity.utenti.Corriere;
import it.unicam.progettoc3.vlv.entity.utenti.Utente;
import it.unicam.progettoc3.vlv.repository.ClienteRepository;
import it.unicam.progettoc3.vlv.repository.CommercianteRepository;
import it.unicam.progettoc3.vlv.repository.CorriereRepository;
import it.unicam.progettoc3.vlv.repository.OrdineRepository;
import it.unicam.progettoc3.vlv.repository.PuntoDiRitiroRepository;
import it.unicam.progettoc3.vlv.repository.UtenteRepository;
import javassist.NotFoundException;

@Service
@Transactional
public class OrdiniService {
	
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
	
	@Autowired
	UtenteRepository utenteRepository;

	public List<Ordine> getOrdiniCliente(String emailCliente) throws IllegalArgumentException,NotFoundException{
		// TODO Auto-generated method stub
 
		
		Utente utente = utenteRepository.findByEmail(emailCliente).orElseThrow(()->new  NotFoundException("cliente non trovato"));
		if(utente.getNomeRuolo() != NomiRuoli.ROLE_CLIENTE) throw new IllegalArgumentException("utente non è cliente");
		Cliente cliente = (Cliente) utente.getAssociato(); 
		return cliente.getOrdini();

	}

	
	public void addOrdine(NuovoOrdine ordine ,String emailCommerciante)  throws IllegalArgumentException ,NotFoundException{
		// TODO Auto-generated method stub
		
			//controllo validita idCommerciante
		Utente utente = utenteRepository.findByEmail(emailCommerciante).orElseThrow(()->new  NotFoundException("commerciante non trovato"));
			Commerciante commerciante = (Commerciante) utente.getAssociato();
			
			//controllo validita idCliente
		
			Cliente cliente = clienteRepository.findById(ordine.getIdCliente()).orElseThrow(()->new  NotFoundException("cliente non trovato"));
			
		
			PuntoDiRitiro puntoDiRitiro = null;
			if(ordine.getIdPuntoDiRitiro()!=null){
		
				puntoDiRitiro = puntoDiRitiroRepository.findById(ordine.getIdPuntoDiRitiro()).orElseThrow(()->new  NotFoundException("punto di ritiro  non trovato"));
			}
			Ordine ordineSave ;
			if(puntoDiRitiro==null){
			ordineSave= new Ordine( ordine.getDescrizione(), commerciante, cliente);
			}
			else
			{
				ordineSave= new Ordine(ordine.getCodiceRitiro(), ordine.getDescrizione(), commerciante, cliente, puntoDiRitiro);
			}
			ordineRepository.save(ordineSave);
		
		
		
		
	}

	
	public void ritiraOrdine(Long idOrdine , String codiceRitiro)  throws IllegalArgumentException ,NotFoundException{
		// TODO Auto-generated method stub
		
		Ordine ordine = ordineRepository.findById(idOrdine).orElseThrow(()->new  NotFoundException("ordine non trovato"));
		
	
		
		
	
		if (ordine.getStato()!= StatiOrdine.PRONTO_PER_IL_RITIRO)
		{
			
			
			throw	new IllegalArgumentException("L'ORDINE NON E' NEL PUNTO DI RITIRO , IMPOSSIBILE RITIRARE");
		}
		
		else if(ordine.getPuntoDiRitiro()==null)
		{
			throw	new IllegalArgumentException("L'ORDINE NON HA UN PUNTO DI RITIRO , IMPOSSIBILE RITIRARE");
		}
		
		else if(   ordine.getCodiceRitiro().equals(codiceRitiro))
		{
			
			ordine.setStato(StatiOrdine.CONSEGNATO);
			ordineRepository.save(ordine);
			
		}
		else
		{
			

			 throw new IllegalArgumentException("CODICE RITIRO ERRATO , IMPOSSIBILE RITIRARE");
			
		}
		
		
	}

	
	public List<Ordine> getOrdiniLiberi() {
		// TODO Auto-generated method stub
		List<Ordine> ordini= new ArrayList<>();
		
		Iterable<Ordine> iteratore = ordineRepository.findAll();
		iteratore.forEach(Ordine -> ordini.add(Ordine));
		
		if( ordini.isEmpty()){return null;}

		
		List<Ordine> ordiniLiberi = new ArrayList<>();
		 
		
		
		ordini.forEach(Ordine ->
		{
			
			if(Ordine.getStato()==StatiOrdine.IN_ACCETTAZIONE){

				ordiniLiberi.add(Ordine);
			}
		});	
	
		if(ordiniLiberi.isEmpty()){ return null;}
		else{return ordiniLiberi;}
	}

	
	public List<Ordine> getOrdiniDaRitirare(String emailCorriere)  throws IllegalArgumentException,NotFoundException{
		// TODO Auto-generated method stub
		List<Ordine> ordini= new ArrayList<>();
		
				Utente utente = utenteRepository.findByEmail(emailCorriere).orElseThrow(()->new  NotFoundException("utente non trovato"));
				if(utente.getNomeRuolo()!=NomiRuoli.ROLE_CORRIERE) throw new IllegalArgumentException("utente non è corriere");
				
				
				Corriere corriere = (Corriere) utente.getAssociato();
				ordini =corriere.getOrdini();
if( ordini.isEmpty()){return null;}

		
		List<Ordine> ordiniDaRitirare = new ArrayList<>();
		 
		
		
		ordini.forEach(Ordine ->
		{
			
			if(Ordine.getStato()==StatiOrdine.IN_RITIRO ){

			
		
				ordiniDaRitirare.add(Ordine);
			}
		});	
	
		if(ordiniDaRitirare.isEmpty()){ return null;}
		else{return ordiniDaRitirare;}
	}

	
	public List<Ordine> getOrdiniInTransito(String emailCorriere)  throws IllegalArgumentException ,NotFoundException{
		// TODO Auto-generated method stub
		List<Ordine> ordini= new ArrayList<>();
		
		Utente utente = utenteRepository.findByEmail(emailCorriere).orElseThrow(()->new  NotFoundException("utente non trovato"));
		if(utente.getNomeRuolo()!=NomiRuoli.ROLE_CORRIERE) throw new IllegalArgumentException("utente non è corriere");
		
		
		Corriere corriere = (Corriere) utente.getAssociato();
		ordini =corriere.getOrdini();
if( ordini.isEmpty()){return null;}


List<Ordine> ordiniInTransito = new ArrayList<>();
 


ordini.forEach(Ordine ->
{
	
	if(Ordine.getStato()==StatiOrdine.IN_TRANSITO ){

	

		ordiniInTransito.add(Ordine);
	}
});	

if(ordiniInTransito.isEmpty()){ return null;}
else{return ordiniInTransito;}
	}

	
	public void setPresaInCaricoOrdine(Long idOrdine, Date dataPrevistaRitiro,String emailCorriere) throws IllegalArgumentException , NotFoundException{
		// TODO Auto-generated method stub
		
		Utente utente = utenteRepository.findByEmail(emailCorriere).orElseThrow(()->new  NotFoundException("utente non trovato") );
		Corriere corriere = (Corriere) utente.getAssociato();
		
		Ordine ordine = ordineRepository.findById(idOrdine).orElseThrow(() -> new NotFoundException("ordine non trovato"));
	
		if(dataPrevistaRitiro == null){throw new IllegalArgumentException("IMPOSSIBILE IMPOSTARE DATA RITIRO PREVISTA , CONTROLLARE DATA INSERITA");}
		
		if(ordine.getDataCreazione().after(dataPrevistaRitiro)){throw new IllegalArgumentException("LA DATA DI RITIRO PREVISTA DEVE ESSERE DOPO QUELLA DI CREAZIONE");}
		 if(ordine.getStato()==StatiOrdine.IN_ACCETTAZIONE && dataPrevistaRitiro!=null)
		{
			 
			ordine.setStato(StatiOrdine.IN_RITIRO);
			ordine.setDataRitiro(dataPrevistaRitiro);
			ordine.setCorriere(corriere);
			ordineRepository.save(ordine);
			
		}
		else
		{
			
			throw new IllegalArgumentException("Ordine non puo essere preso in carico");
		}
	}

	
	public void setDataConsegnaPrevista(Long idOrdine, Date dataConsegnaPrevista)  throws IllegalArgumentException,NotFoundException{
		// TODO Auto-generated method stub
		
		Ordine ordine = ordineRepository.findById(idOrdine).orElseThrow(() -> new NotFoundException("Ordine non trovato "));
		
		if(dataConsegnaPrevista == null){throw new IllegalArgumentException("IMPOSSIBILE IMPOSTARE DATA CONSEGNA PREVISTA , CONTROLLARE DATA INSERITA");}
		if(ordine.getDataCreazione().after(dataConsegnaPrevista)){throw new IllegalArgumentException("LA DATA DI CONSEGNA PREVISTA DEVE ESSERE DOPO QUELLA DI RITIRO");}
		if(ordine.getStato()==StatiOrdine.IN_RITIRO && dataConsegnaPrevista!=null)
		{
			ordine.setStato(StatiOrdine.IN_TRANSITO);
			ordine.setDataConsegnaPrevista(dataConsegnaPrevista);
			ordineRepository.save(ordine);
			
		}
		else
		{
			throw new IllegalArgumentException("IMPOSSIBILE IMPOSTARE ORDINE IN TRANSITO");
		}
	}

	
	public void setOrdineConsegnato(Long idOrdine) throws IllegalArgumentException , NotFoundException{
		// TODO Auto-generated method stub
		
		Ordine ordine = ordineRepository.findById(idOrdine).orElseThrow(() -> new NotFoundException("Ordine non trovato "));
		
		if(ordine.getStato()!=StatiOrdine.IN_TRANSITO ){throw new IllegalArgumentException("impossibile consegnare ordine , l'ordine non è in transito");}
		if(ordine.getPuntoDiRitiro()!=null){throw new IllegalArgumentException("impossibile consegnare ordine , l'ordine deve essere depositato presso un punto di ritiro");}
		if(ordine.getStato()==StatiOrdine.IN_TRANSITO && ordine.getPuntoDiRitiro() == null)
		{
			
			ordine.setStato(StatiOrdine.CONSEGNATO);
			
			ordineRepository.save(ordine);
			
		}
		else
		{
			throw new IllegalArgumentException("impossibile consegnare ordine ");
			
		}
	}

	
	public void setOrdineProntoPerIlRitiro(Long idOrdine)  throws IllegalArgumentException , NotFoundException{
		// TODO Auto-generated method stub
		
		Ordine ordine = ordineRepository.findById(idOrdine).orElseThrow(() -> new NotFoundException("Ordine non trovato "));
		
		
		if(ordine.getStato()==StatiOrdine.IN_TRANSITO && ordine.getPuntoDiRitiro() != null)
		{
			
			ordine.setStato(StatiOrdine.PRONTO_PER_IL_RITIRO);
			
			ordineRepository.save(ordine);
			
		}
		else
		{
			throw new IllegalArgumentException("impossibile depositare ordine ");
			
		}
	}


	public Commerciante getCommercianteById(Long idCommerciante) throws NotFoundException{
		// TODO Auto-generated method stub
		Commerciante commerciante=commercianteRepository.findById(idCommerciante).orElseThrow(() -> new NotFoundException("COMMERCIANTE NON TROVATO"));
		return commerciante;
	}


	public PuntoDiRitiro getPuntoDiRitiroById(Long idPuntoDiRitiro) throws  NotFoundException{
		// TODO Auto-generated method stub
		PuntoDiRitiro puntoDiRitiro=puntoDiRitiroRepository.findById(idPuntoDiRitiro).orElseThrow(() -> new NotFoundException("PUNTO DI RITIRO NON TROVATO"));
		return puntoDiRitiro;
	}


	public Cliente getClienteById(Long idCliente) throws NotFoundException{
		// TODO Auto-generated method stub
		Cliente cliente=clienteRepository.findById(idCliente).orElseThrow(() -> new NotFoundException("CLIENTE NON TROVATO"));
		return cliente;
	}



}
