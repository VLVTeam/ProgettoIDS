package it.unicam.progettoc3.vlv.utenti;

public class Corriere {
private int ID;
private String nomeDitta;
private boolean statoIscrizione;


public Corriere(int ID, String nomeDitta) {
	this.ID = ID;
	this.nomeDitta = nomeDitta;
	this.statoIscrizione=false;
}

public int getID() {
	return ID;
}



public String getNomeDitta() {
	return nomeDitta;
}


public boolean isStatoIscrizione() {
	return statoIscrizione;
}

public void setStatoIscrizione(boolean statoIscrizione) {
	this.statoIscrizione = statoIscrizione;
}




}
