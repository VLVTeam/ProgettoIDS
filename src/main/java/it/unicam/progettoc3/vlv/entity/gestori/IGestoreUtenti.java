package it.unicam.progettoc3.vlv.entity.gestori;

import java.util.List;
import java.util.function.Predicate;

import it.unicam.progettoc3.vlv.entity.utenti.Cliente;
import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;
import it.unicam.progettoc3.vlv.entity.utenti.Corriere;

public interface IGestoreUtenti {
	
	
	void setStatoIscrizioneCommerciante(Commerciante commerciante, boolean stato);
    void setStatoIscrizioneCorriere(Corriere corriere, boolean stato);
    void addCommerciante(Commerciante commerciante);
    void addCliente(Cliente cliente);
    void addCorriere(Corriere corriere);
	List<Commerciante> filtraPuntiVendita(Predicate<Commerciante> p);
    
}
