package it.unicam.progettoc3.vlv.security.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.unicam.progettoc3.vlv.entity.utenti.Utente;



/**
 * Questa classe implementa UserDetails ed ha la responsabilita' di rappresentare un Utente con la lista delle autorizzazioni.
 * La lista delle autorizzazioni derivano dalla conversione dei ruoli dell'Utente nella classe relativa Ruolo.
 */

@SuppressWarnings("serial")

public class UtentePrincipale implements UserDetails {

	private String email;
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;

	
	private boolean stato;

	

	public UtentePrincipale(String email, String password,boolean stato, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.email = email;
		this.password = password;
		this.stato=stato;
		this.authorities = authorities;
	}

 public static  UtentePrincipale build(Utente utente)
 {
	 List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>() ;
		//	utente.getRuoli().stream().map(Ruolo -> new SimpleGrantedAuthority(Ruolo.getNomeRuolo().name())).collect(Collectors.toList());
			authorities.add(new SimpleGrantedAuthority( utente.getNomeRuolo().name()));
			
	 return new UtentePrincipale(utente.getEmail(), utente.getPassword(),utente.isStato(), authorities);
 };

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return stato;
		
		
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return stato;
		
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	
}
