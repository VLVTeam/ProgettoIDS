package it.unicam.progettoc3.vlv.entity.enumeratori;



/**
 * stati possibili che un ordine assume nel corso del suo iter. L'unico stato che non deve per forza assumere
 * e': 'PRONTO_PER_IL_RITIRO', in quanto nel caso in cui il prodotto venga consegnato presso il domicilio 
 * del cliente lo stato dell'ordine passa direttamente da 'IN_TRANSITO' a 'CONSEGNATO'. Gli stati scritti sotto
 * seguono l'ordine preciso del percorso dell'ordine.
 */
public enum StatiOrdine {
	
	IN_ACCETTAZIONE,
	IN_RITIRO,
	IN_TRANSITO,
	PRONTO_PER_IL_RITIRO,
	CONSEGNATO
}
