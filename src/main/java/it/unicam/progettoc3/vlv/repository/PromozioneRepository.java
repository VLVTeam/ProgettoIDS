package it.unicam.progettoc3.vlv.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import it.unicam.progettoc3.vlv.entity.elementi.Promozione;

/**
 * questa interfaccia permette l'interazione con il database per la gestione di una promozione, fornisce la definizione del metodo removePromozioneById
 * 
 */
public interface PromozioneRepository extends JpaRepository<Promozione, Long>{
	@Modifying
	@Query("delete from Promozione where ID=?1")
	/** rimuove la promozione il quale id corrisponde a quello fornito al metodo */
	public void removePromozioneById(Long idPromozione);
}
