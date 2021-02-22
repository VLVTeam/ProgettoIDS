package it.unicam.progettoc3.vlv.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * classe dedicata al JWT (JSON Web Token). Questo token, ad ogni richiesta di autenticazione da parte di un client, deve venir generato, firmato e 
 * restituito al client. Il cliente utilizzera' questo token per autenticare le successive richieste
 */
public class JwtDto {
	
	private String token ;
	private String bearer = "Bearer";
	private String email;
	private Collection<? extends  GrantedAuthority> authorities;
	public JwtDto(String token, String email, Collection<? extends GrantedAuthority> authorities) {
		
		this.token = token;
		this.email = email;
		this.authorities = authorities;
		
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getBearer() {
		return bearer;
	}
	public void setBearer(String bearer) {
		this.bearer = bearer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	

}
