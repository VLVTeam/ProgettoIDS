package it.unicam.progettoc3.vlv.entity.utenti;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import it.unicam.progettoc3.vlv.entity.enumeratori.NomiRuoli;





@Entity
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(unique = true)
	private String email;
	
	@NotNull
	private String password;
	
	/*
	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "utente_ruolo",joinColumns = @JoinColumn(name = "utente_id"), inverseJoinColumns = @JoinColumn(name = "ruolo_id"))
	private Set<Ruolo> ruoli = new HashSet<>();
*/
	@NotNull
	@Enumerated(EnumType.STRING)
 private NomiRuoli nomeRuolo;
	
	private boolean stato;
	
	
	@NotNull
	@JsonManagedReference
	@OneToOne(cascade= CascadeType.ALL , fetch = FetchType.EAGER )
	Ruolo associato;

	public Utente() {
		
	}

	public Utente(@NotNull  String email, @NotNull String password , NomiRuoli nomeRuolo) {
		super();
		this.email = email;
		this.password = password;
		this.nomeRuolo=nomeRuolo;
		if(nomeRuolo==NomiRuoli.ROLE_COMMERCIANTE || nomeRuolo==NomiRuoli.ROLE_CORRIERE)
		this.stato=false;
		else this.stato=true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*
	public Set<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	*/
	public Ruolo getAssociato() {
		
		return associato;
	}

	public void setAssociato(Ruolo associato) {
		this.associato = associato;
	}
	
	
	@JsonIgnore
	public Long getIdAssociato(){
		if(associato==null) return null;
		return associato.getId();
	}
	
	

	public boolean isStato() {
		return stato;
	}

	public void setStato(boolean stato) {
		this.stato = stato;
	}

	public NomiRuoli getNomeRuolo() {
		return nomeRuolo;
	}

	public void setNomeRuolo(NomiRuoli nomeRuolo) {
		this.nomeRuolo = nomeRuolo;
	}
	
	
}
