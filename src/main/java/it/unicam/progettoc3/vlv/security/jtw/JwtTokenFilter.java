package it.unicam.progettoc3.vlv.security.jtw;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import it.unicam.progettoc3.vlv.security.service.UserDetailsServiceImpl;

/**
 * Questa classe estende OncePerRequestFilter ed ha la responsabilita' di verificare la validita' del token tramite la classe JwtProvider; 
 * in caso affermativo permette la visualizzazione della risorsa altrimenti lancia un eccezione.
 */
public class JwtTokenFilter extends OncePerRequestFilter{
	
	private final static  Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

	public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
	public static final String TOKEN_BEARER_HEADER_NAME = "Bearer ";
	
	// collegamento alla classe provider relativa
	@Autowired
	JwtProvider jwtProvider;
	
	// collegamento alla classe userDetailsService
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			String token =  getToken(request);
			if(token !=null && jwtProvider.validateToken(token))
			{
				String email  = jwtProvider.getEmailFromToken(token);
				UserDetails userDetails =  userDetailsService.loadUserByUsername(email);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("errore nel metodo doFilter" + e.getMessage());
		}
		filterChain.doFilter(request, response);
	}

	
	
	private String getToken(HttpServletRequest request)
	{
		String header = request.getHeader(AUTHORIZATION_HEADER_NAME);
		if(header != null &&  header.startsWith(TOKEN_BEARER_HEADER_NAME))
			return header.replace(TOKEN_BEARER_HEADER_NAME, "");
		
		return null;
	}
}
