package it.unicam.progettoc3.vlv.controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import it.unicam.progettoc3.vlv.entity.riferimenti.OrdineRiferimento;


public interface IOrdini {
	
	public ResponseEntity<String> addOrdine(@RequestBody OrdineRiferimento ordine);
	
	
	public List<OrdineRiferimento> getOrdiniCliente(@RequestParam Long idCliente);
	
	public List<OrdineRiferimento> getOrdiniLiberi(); 
	
	public List<OrdineRiferimento> getOrdiniDaRitirare(@RequestParam Long idCorriere);
	
	public List<OrdineRiferimento> getOrdiniInTransito(@RequestParam Long idCorriere);
	
	public ResponseEntity<String> ritiraOrdine(@RequestParam Long idOrdine ,@RequestParam String codiceRitiro);	
	
	public ResponseEntity<String> setPresaInCaricoOrdine ( @RequestParam Long idOrdine , @RequestParam Long idCorriere  , @RequestParam Date dataPrevistaRitiro);
	
	public ResponseEntity<String> setDataConsegnaPrevista(@RequestParam Long idOrdine ,  @RequestParam Date dataConsegnaPrevista);
	
	public ResponseEntity<String> setOrdineConsegnato(@RequestParam Long idOrdine  );
	
	public ResponseEntity<String> setOrdineProntoPerIlRitiro(@RequestParam Long idOrdine);
}
