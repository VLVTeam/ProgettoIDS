package it.unicam.progettoc3.vlv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unicam.progettoc3.vlv.entity.utenti.Amministratore;
import it.unicam.progettoc3.vlv.repository.AmministratoreRepository;
import it.unicam.progettoc3.vlv.repository.UtenteRepository;

@Service
@Transactional
/**
 * La classe AmministratoreService definisce precisamente il funzionamento
 * dei metodi per salvare l'amministratore all'interno della repository e per verificare se esiste un admin
 */
public class AmministratoreService {
	
	// collegamenti alle repository
		@Autowired
		UtenteRepository utenteRepository;

		@Autowired
		AmministratoreRepository amministratoreRepository;
	
	/** metodo per verificare se l'amministratore esiste, se esiste restituisce true, altrimenti restituisce false */
	public boolean existAdmin()
	{
		if(amministratoreRepository.count() == 0)
			return false;
		else
			return true;
	}
	
	/** metodo per salvare il cliente nella repository */
	public void save(Amministratore amministratore)
	{
		amministratoreRepository.save(amministratore);
	}
	
	/** metodo per ottenere l'oggetto amministratore presente */
	public Amministratore getAmministratore()
	{
		if(amministratoreRepository.count() == 0)
			return null;
		else
			return amministratoreRepository.getOne(1L);
	}
	
	
}