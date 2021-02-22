package it.unicam.progettoc3.vlv.entity.utenti;

import javax.persistence.Entity;

@Entity
/**
 * classe model relativa all'amministratore, si limita a estendere la classe padre astratta 'Ruolo',
 */
public class Amministratore extends Ruolo  {

	public Amministratore(  ) {
		
		super();
		
	}
	
}
