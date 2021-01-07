package it.unicam.progettoc3.vlv.utenti;

public class Cliente {
	private int ID;
	private String nome;
	private String cognome;
	private String indirizzo;
	
	public Cliente(int ID, String nome, String cognome, String indirizzo) {
		this.ID = ID;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
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

	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

 

}
