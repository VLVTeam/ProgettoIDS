package it.unicam.progettoc3.vlv.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.unicam.progettoc3.vlv.entity.elementi.PuntoDiRitiro;
/**
 * questa interfaccia permette l'interazione con il database per la gestione di un punto di ritiro, fornisce la definizione del metodo 
 * removePuntoDiRitiroById
 */
public interface PuntoDiRitiroRepository extends JpaRepository<PuntoDiRitiro, Long>{

	@Modifying
	@Query(value =  "DELETE FROM punto_di_ritiro WHERE id=?1" , nativeQuery=true)
	public void removePuntoDiRitiroById(Long idPuntoDiRitiro);
	
	
}
