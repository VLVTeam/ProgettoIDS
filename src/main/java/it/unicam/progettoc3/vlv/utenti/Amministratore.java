package it.unicam.progettoc3.vlv.utenti;

public class Amministratore {
private int ID;
private String nome;
private String cognome;

public Amministratore(int ID, String nome, String cognome) {
	this.ID = ID;
	this.nome = nome;
	this.cognome = cognome;
}

public int getID() {
	return ID;
}


public String getNome() {
	return nome;
}



public String getCognome() {
	return cognome;
}



}
