package it.unicam.progettoc3.vlv.security.jtw;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
/**
 * Questa classe implementa AuthenticationEntryPoint, grazie a questo puo' verificare se l'utente che ha tentato l'autenticazione
 * ha un token jwt valido. In caso negativo viene inviato l'errore 401 (NON AUTORIZZATO).
 */
public class JwtEntryPoint implements AuthenticationEntryPoint {
	
	private final static  Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		logger.error("errore");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED , "NON AUTORIZZATO");
	}

}
