package it.unicam.progettoc3.vlv.gestori;

import it.unicam.progettoc3.vlv.utenti.Cliente;
import it.unicam.progettoc3.vlv.utenti.Commerciante;
import it.unicam.progettoc3.vlv.utenti.Corriere;

public interface IGestoreUtenti {
	
	
	void setStatoIscrizioneCommerciante(Commerciante commerciante, boolean stato);
    void setStatoIscrizioneCorriere(Corriere corriere, boolean stato);
    void addCommerciante(Commerciante commerciante);
    void addCliente(Cliente cliente);
    void addCorriere(Corriere corriere);
}
