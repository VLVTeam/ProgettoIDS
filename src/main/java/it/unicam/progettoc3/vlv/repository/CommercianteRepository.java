package it.unicam.progettoc3.vlv.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;

public interface CommercianteRepository extends JpaRepository<Commerciante, Long> {
	
	
}
