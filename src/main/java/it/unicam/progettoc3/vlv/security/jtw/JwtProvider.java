package it.unicam.progettoc3.vlv.security.jtw;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.ExpiredJwtException;

import it.unicam.progettoc3.vlv.security.entity.UtentePrincipale;

@Component
/**
 * Questa classe si occupa di creare il token jwt, ottenere le informazioni dal token jwt e verificare la validita' 
 * dello stesso token a livello sintattico.
 */
public class JwtProvider {

	private final static  Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private int  expiration;
	
	public String generateToken(Authentication authentication)
	{
		UtentePrincipale utentePrincipale = (UtentePrincipale) authentication.getPrincipal();
		return Jwts.builder().setSubject(utentePrincipale.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date (new Date().getTime() + expiration * 1000) )
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
	public String getEmailFromToken(String token)
	{
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateToken(String token)
	{
		try {
			
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			// TODO: handle exception
			logger.error("token malformato");
		}catch (UnsupportedJwtException e) {
			// TODO: handle exception
			logger.error("token non supportato");
		}catch (ExpiredJwtException e) {
			// TODO: handle exception
			logger.error("token scaduto");
		}catch (IllegalArgumentException e) {
			// TODO: handle exception
			logger.error("token vuoto");
		}catch (SignatureException e) {
			// TODO: handle exception
			logger.error("errore nella firma");
		}
		return false;
	}
}
