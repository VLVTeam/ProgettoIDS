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
/**
 * La classe OrdiniService definisce precisamente il funzionamento
 * dei metodi presenti anche nella classe controller, fornendoli appunto alla classe OrdiniController.
 */
public class OrdiniService {
	
	// collegamenti con tutte le repository
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
		/*
		 * se l'email fornita non esiste, o se non corrisponde all'email di un cliente, stampa errore
		 */
		Utente utente = utenteRepository.findByEmail(emailCliente).orElseThrow(()->new  NotFoundException("cliente non trovato"));
		if(utente.getNomeRuolo() != NomiRuoli.ROLE_CLIENTE) throw new IllegalArgumentException("utente non corrisponde a cliente");
		Cliente cliente = (Cliente) utente.getAssociato(); 
		return cliente.getOrdini();

	}

	
	public void addOrdine(NuovoOrdine ordine ,String emailCommerciante)  throws IllegalArgumentException ,NotFoundException{
		// TODO Auto-generated method stub
		
			// controllo validita' idCommerciante
			Utente utente = utenteRepository.findByEmail(emailCommerciante).orElseThrow(()->new  NotFoundException("commerciante non trovato"));
			Commerciante commerciante = (Commerciante) utente.getAssociato();
			
			// controllo validita' idCliente
		
			Cliente cliente = clienteRepository.findById(ordine.getIdCliente()).orElseThrow(()->new  NotFoundException("cliente non trovato"));
		
			PuntoDiRitiro puntoDiRitiro = null;
			if(ordine.getIdPuntoDiRitiro()!=null){
				puntoDiRitiro = puntoDiRitiroRepository.findById(ordine.getIdPuntoDiRitiro()).orElseThrow(()->new  NotFoundException("punto di ritiro non trovato"));
			}
			Ordine ordineSave ;
			if(puntoDiRitiro==null){
			ordineSave= new Ordine( ordine.getDescrizione(), commerciante, cliente);
			}
			else
			{
				if(ordine.getIdPuntoDiRitiro().equals("")) {throw new IllegalArgumentException("CODICE RITIRO BLANK");}
				if(ordine.getIdPuntoDiRitiro()==null) {throw new IllegalArgumentException("CODICE RITIRO NULLO");}
				// assegna commerciante, cliente ed eventualmente punto di ritiro all'ordine che deve essere aggiunto
				ordineSave= new Ordine(ordine.getCodiceRitiro(), ordine.getDescrizione(), commerciante, cliente, puntoDiRitiro);
			}
			// aggiunge l'ordine alla repository
			ordineRepository.save(ordineSave);
			
	}

	
	public void ritiraOrdine(Long idOrdine , String codiceRitiro)  throws IllegalArgumentException ,NotFoundException{
		// TODO Auto-generated method stub
		
		// se non esiste nessun ordine con l'id fornito, stampa errore
		Ordine ordine = ordineRepository.findById(idOrdine).orElseThrow(()->new  NotFoundException("ordine non trovato"));
	
		// se l'ordine e' presente ma il suo stato non corrisponde a 'PRONTO_PER_IL_RITIRO', stampa errore
		if (ordine.getStato()!= StatiOrdine.PRONTO_PER_IL_RITIRO)
		{
			throw	new IllegalArgumentException("L'ORDINE NON E' NEL PUNTO DI RITIRO , IMPOSSIBILE RITIRARE");
		}
		
		// se l'ordine e' pronto per il ritiro, ma non ha effettivamente un punto di ritiro, stampa errore
		else if(ordine.getPuntoDiRitiro()==null)
		{
			throw	new IllegalArgumentException("L'ORDINE NON HA UN PUNTO DI RITIRO , IMPOSSIBILE RITIRARE");
		}
		
		/*
		 * se l'ordine esiste, e' pronto per il ritiro, ha un punto di ritiro, e il codiceRitiro fornito corrisponde a quello 
		 * dell'ordine, l'ordine e' considerato consegnato
		 */
		else if(ordine.getCodiceRitiro().equals(codiceRitiro))
		{
			ordine.setStato(StatiOrdine.CONSEGNATO);
			ordineRepository.save(ordine);
		}
		// se il codice ritiro invece non corrisponde, stampa errore
		else
		{
			 throw new IllegalArgumentException("CODICE RITIRO ERRATO , IMPOSSIBILE RITIRARE");
		}
		
		
	}

	
	public List<Ordine> getOrdiniLiberi() {
		// TODO Auto-generated method stub
		
		// crea lista ordini e la riempie con tutti gli ordini presenti
		List<Ordine> ordini= new ArrayList<>();
		
		Iterable<Ordine> iteratore = ordineRepository.findAll();
		iteratore.forEach(Ordine -> ordini.add(Ordine));
		
		// se la lista e' vuota, ritorna null
		if( ordini.isEmpty()){return null;}

		List<Ordine> ordiniLiberi = new ArrayList<>();
		 
		// inserisce nella nuova lista tutti gli ordini con stato 'IN_ACCETTAZIONE'
		ordini.forEach(Ordine ->
		{
			if(Ordine.getStato()==StatiOrdine.IN_ACCETTAZIONE){

				ordiniLiberi.add(Ordine);
			}
		});	
	
		// se non e' stato aggiunto nulla, restituisce null
		if(ordiniLiberi.isEmpty()){ return null;}
		// altrimenti restituisce il risultato richiesto
		else{return ordiniLiberi;}
	}

	
	public List<Ordine> getOrdiniDaRitirare(String emailCorriere)  throws IllegalArgumentException,NotFoundException{
		// TODO Auto-generated method stub
		List<Ordine> ordini= new ArrayList<>();
		
		/*
		 * se l'email fornita non esiste, o se non corrisponde all'email di un corriere, stampa errore
		 */
		Utente utente = utenteRepository.findByEmail(emailCorriere).orElseThrow(()->new  NotFoundException("utente non trovato"));
		if(utente.getNomeRuolo()!=NomiRuoli.ROLE_CORRIERE) throw new IllegalArgumentException("utente non corrisponde ad un corriere");

		// se trova il corriere, riempie la lista di ordini con la lista di tutti gli ordini relativi al corriere
		Corriere corriere = (Corriere) utente.getAssociato();
		ordini = corriere.getOrdini();
			
		// se la lista e' vuote restituisce null
		if( ordini.isEmpty()){return null;}
		
		// crea un'altra lista di ordini
		List<Ordine> ordiniDaRitirare = new ArrayList<>();
		 
		/*
		 * inserisce in questa lista tutti i componenti della lista relativa a tutti gli ordini del corriere trovato, escludendo gli ordini
		 * nei quali lo stato non e' 'IN_RITIRO'
		 */
		ordini.forEach(Ordine ->
		{
			
			if(Ordine.getStato()==StatiOrdine.IN_RITIRO ){
				ordiniDaRitirare.add(Ordine);
			}
			
		});	
	
		// se la nuova lista e' ancora vuota, restituisce null
		// altrimenti restituisce il risultato
		if(ordiniDaRitirare.isEmpty()){ return null;}
		else{return ordiniDaRitirare;}
		
	}

	
	public List<Ordine> getOrdiniInTransito(String emailCorriere)  throws IllegalArgumentException ,NotFoundException{
		// TODO Auto-generated method stub
		List<Ordine> ordini= new ArrayList<>();
		
		/*
		 * se l'email fornita non esiste, o se non corrisponde all'email di un corriere, stampa errore
		 */
		Utente utente = utenteRepository.findByEmail(emailCorriere).orElseThrow(()->new  NotFoundException("utente non trovato"));
		if(utente.getNomeRuolo()!=NomiRuoli.ROLE_CORRIERE) throw new IllegalArgumentException("utente non corrisponde ad un corriere");
		
		// se trova il corriere, riempie la lista di ordini con la lista di tutti gli ordini relativi al corriere
		Corriere corriere = (Corriere) utente.getAssociato();
		ordini =corriere.getOrdini();
		// se la lista e' vuote restituisce null
		if( ordini.isEmpty()){return null;}
		
		// crea un'altra lista di ordini
		List<Ordine> ordiniInTransito = new ArrayList<>();

		/*
		 * inserisce in questa lista tutti i componenti della lista relativa a tutti gli ordini del corriere trovato, escludendo gli ordini
		 * nei quali lo stato non e' 'IN_TRANSITO'
		 */
		ordini.forEach(Ordine ->
		{
			if(Ordine.getStato()==StatiOrdine.IN_TRANSITO ){
				ordiniInTransito.add(Ordine);
			}
		});	
	
		// se la nuova lista e' ancora vuota, restituisce null
				// altrimenti restituisce il risultato
		if(ordiniInTransito.isEmpty()){ return null;}
		else{return ordiniInTransito;}
		
	}

	
	public List<Ordine> getOrdiniConsegnatiInPuntoRitiro(String emailCorriere)  throws IllegalArgumentException ,NotFoundException{
		// TODO Auto-generated method stub
		List<Ordine> ordini= new ArrayList<>();
		
		/*
		 * se l'email fornita non esiste, o se non corrisponde all'email di un corriere, stampa errore
		 */
		Utente utente = utenteRepository.findByEmail(emailCorriere).orElseThrow(()->new  NotFoundException("utente non trovato"));
		if(utente.getNomeRuolo()!=NomiRuoli.ROLE_CORRIERE) throw new IllegalArgumentException("utente non corrisponde ad un corriere");
		
		// se trova il corriere, riempie la lista di ordini con la lista di tutti gli ordini relativi al corriere
		Corriere corriere = (Corriere) utente.getAssociato();
		ordini =corriere.getOrdini();
		// se la lista e' vuota restituisce null
		if( ordini.isEmpty()){return null;}
		
		// crea un'altra lista di ordini
		List<Ordine> ordiniConsegnatiPunto = new ArrayList<>();

		/*
		 * inserisce in questa lista tutti i componenti della lista relativa a tutti gli ordini del corriere trovato, escludendo gli ordini
		 * nei quali lo stato non e' 'IN_TRANSITO'
		 */
		ordini.forEach(Ordine ->
		{
			if(Ordine.getStato()==StatiOrdine.PRONTO_PER_IL_RITIRO ){
				ordiniConsegnatiPunto.add(Ordine);
			}
		});	
	
		// se la nuova lista e' ancora vuota, restituisce null
				// altrimenti restituisce il risultato
		if(ordiniConsegnatiPunto.isEmpty()){ return null;}
		else{return ordiniConsegnatiPunto;}
		
	}
	
	
	public List<Ordine> getOrdiniConsegnati(String emailCorriere)  throws IllegalArgumentException ,NotFoundException{
		// TODO Auto-generated method stub
		List<Ordine> ordini= new ArrayList<>();
		
		/*
		 * se l'email fornita non esiste, o se non corrisponde all'email di un corriere, stampa errore
		 */
		Utente utente = utenteRepository.findByEmail(emailCorriere).orElseThrow(()->new  NotFoundException("utente non trovato"));
		if(utente.getNomeRuolo()!=NomiRuoli.ROLE_CORRIERE) throw new IllegalArgumentException("utente non corrisponde ad un corriere");
		
		// se trova il corriere, riempie la lista di ordini con la lista di tutti gli ordini relativi al corriere
		Corriere corriere = (Corriere) utente.getAssociato();
		ordini =corriere.getOrdini();
		// se la lista e' vuota restituisce null
		if( ordini.isEmpty()){return null;}
		
		// crea un'altra lista di ordini
		List<Ordine> ordiniConsegnati = new ArrayList<>();

		/*
		 * inserisce in questa lista tutti i componenti della lista relativa a tutti gli ordini del corriere trovato, escludendo gli ordini
		 * nei quali lo stato non e' 'IN_TRANSITO'
		 */
		ordini.forEach(Ordine ->
		{
			if(Ordine.getStato()==StatiOrdine.CONSEGNATO ){
				ordiniConsegnati.add(Ordine);
			}
		});	
	
		// se la nuova lista e' ancora vuota, restituisce null
				// altrimenti restituisce il risultato
		if(ordiniConsegnati.isEmpty()){ return null;}
		else{return ordiniConsegnati;}
		
	}
	
	
	public void setPresaInCaricoOrdine(Long idOrdine, Date dataPrevistaRitiro,String emailCorriere) throws IllegalArgumentException , NotFoundException{
		// TODO Auto-generated method stub
		
		/*
		 * se l'email fornita non esiste, o se non corrisponde all'email di un corriere, stampa errore
		 */
		Utente utente = utenteRepository.findByEmail(emailCorriere).orElseThrow(()->new  NotFoundException("utente non trovato") );
		Corriere corriere = (Corriere) utente.getAssociato();
		
		/*
		 * se l'idOrdine fornito non esiste stampa errore
		 */
		Ordine ordine = ordineRepository.findById(idOrdine).orElseThrow(() -> new NotFoundException("ordine non trovato"));
	
		if(dataPrevistaRitiro == null){throw new IllegalArgumentException("IMPOSSIBILE IMPOSTARE DATA RITIRO PREVISTA , CONTROLLARE DATA INSERITA");}
		
		if(ordine.getDataCreazione().after(dataPrevistaRitiro)){throw new IllegalArgumentException("LA DATA DI RITIRO PREVISTA DEVE ESSERE DOPO QUELLA DI CREAZIONE");}
		
		/*
		 * se l'ordine con lo stesso idOrdine e' stato trovato, il suo stato equivale
		 * a 'IN_ACCETTAZIONE' e la dataPrevistaRitiro non corrisponde a null, cambia il suo stato in 'IN_RITIRO',
		 * collega il corriere con l'idCorriere fornito all'ordine e aggiunge la data prevista per il ritiro
		 * all'ordine
		 */
		if(ordine.getStato()==StatiOrdine.IN_ACCETTAZIONE && dataPrevistaRitiro!=null)
		{
			 
			ordine.setStato(StatiOrdine.IN_RITIRO);
			ordine.setDataRitiro(dataPrevistaRitiro);
			ordine.setCorriere(corriere);
			ordineRepository.save(ordine);
			
		}
		/*
		 * se l'ordine con lo stesso idOrdine e' stato trovato, ma il suo stato non e' 'IN_ACCETTAZIONE' oppure
		 * la sua dataPrevistaRitiro e' null, allora stampa errore
		 */
		else
		{
			throw new IllegalArgumentException("Ordine non puo essere preso in carico");
		}
	}

	
	public void setDataConsegnaPrevista(Long idOrdine, Date dataConsegnaPrevista)  throws IllegalArgumentException,NotFoundException{
		// TODO Auto-generated method stub
		// cerca l'ordine con l'idOrdine uguale a quello fornito al metodo, se non esiste stampa errore
		Ordine ordine = ordineRepository.findById(idOrdine).orElseThrow(() -> new NotFoundException("Ordine non trovato "));
		
		/*
		 * se lo trova, il suo stato e' 'IN_RITIRO' e la dataConsegnaPrevista non e' null, imposta lo stato in
		 * 'IN_TRANSITO' e imposta la dataConsegnaPrevista come quella fornita
		 */
		if(dataConsegnaPrevista == null){throw new IllegalArgumentException("IMPOSSIBILE IMPOSTARE DATA CONSEGNA PREVISTA , CONTROLLARE DATA INSERITA");}
		if(ordine.getDataCreazione().after(dataConsegnaPrevista)){throw new IllegalArgumentException("LA DATA DI CONSEGNA PREVISTA DEVE ESSERE DOPO QUELLA DI RITIRO");}
		if(ordine.getStato()==StatiOrdine.IN_RITIRO && dataConsegnaPrevista!=null)
		{
			ordine.setStato(StatiOrdine.IN_TRANSITO);
			ordine.setDataConsegnaPrevista(dataConsegnaPrevista);
			ordineRepository.save(ordine);
		}
		// se lo trova ma il suo stato non e' 'IN_RITIRO' oppure la sua dataConsegnaPrevista e' null, stampa errore
		else
		{
			throw new IllegalArgumentException("IMPOSSIBILE IMPOSTARE ORDINE IN TRANSITO");
		}
	}

	
	public void setOrdineConsegnato(Long idOrdine) throws IllegalArgumentException , NotFoundException{
		// TODO Auto-generated method stub
		
		// cerca l'ordine con l'idOrdine uguale a quello fornito al metodo, se non esiste stampa errore
		Ordine ordine = ordineRepository.findById(idOrdine).orElseThrow(() -> new NotFoundException("Ordine non trovato "));
		
		/*
		 * se l'ordine viene trovato, il suo stato e' 'IN_TRANSITO' e il suo puntoDiRitiro e' null (il punto 
		 * di ritiro e' null quando l'ordine deve essere consegnato direttamente al domicilio
		 * del cliente), allora e' possibile stabilire che l'ordine e' stato consegnato
		 */
		if(ordine.getStato()!=StatiOrdine.IN_TRANSITO ){throw new IllegalArgumentException("impossibile consegnare ordine , l'ordine non e' in transito");}
		if(ordine.getPuntoDiRitiro()!=null){throw new IllegalArgumentException("impossibile consegnare ordine , l'ordine deve essere depositato presso un punto di ritiro");}
		if(ordine.getStato()==StatiOrdine.IN_TRANSITO && ordine.getPuntoDiRitiro() == null)
		{
			
			ordine.setStato(StatiOrdine.CONSEGNATO);
			
			ordineRepository.save(ordine);
			
		}
		// se l'ordine viene trovato ma non e' 'IN_TRANSITO' oppure il suo puntoDiRitiro non e' null, stampa errore
		else
		{
			throw new IllegalArgumentException("impossibile consegnare ordine ");
			
		}
	}

	
	public void setOrdineProntoPerIlRitiro(Long idOrdine)  throws IllegalArgumentException , NotFoundException{
		// TODO Auto-generated method stub
		
		// cerca l'ordine con l'idOrdine uguale a quello fornito al metodo, se non esiste stampa errore
		Ordine ordine = ordineRepository.findById(idOrdine).orElseThrow(() -> new NotFoundException("Ordine non trovato "));
		
		/*
		 * se l'ordine viene trovato, il suo stato e' 'IN_TRANSITO' e il suo puntoDiRitiro non e' null (il punto 
		 * di ritiro e' diverso da null quando l'ordine deve essere consegnato non direttamente al domicilio
		 * del cliente ma in un punto di ritiro esterno), allora e' possibile stabilire che l'ordine e' in stato 
		 * 'PRONTO_PER_IL_RITIRO'
		 */
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
	
	
	public List<Ordine> getOrdiniCommerciante(String emailCommerciante) throws IllegalArgumentException,NotFoundException{
        // TODO Auto-generated method stub
 
        Utente utente = utenteRepository.findByEmail(emailCommerciante).orElseThrow(()->new  NotFoundException("commerciante non trovato"));
        if(utente.getNomeRuolo() != NomiRuoli.ROLE_COMMERCIANTE) throw new IllegalArgumentException("utente non è commerciante");
        Commerciante commerciante = (Commerciante) utente.getAssociato(); 
        return commerciante.getOrdini();

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