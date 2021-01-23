package it.unicam.progettoc3.vlv.entity.gestori;

import java.util.ArrayList;
import java.util.List;

import it.unicam.progettoc3.vlv.entity.elementi.Ordine;




public class GestoreOrdini implements IGestoreOrdini {
	
	private List<Ordine> ordini = new ArrayList<>();

	@Override
	public void addOrdine(Ordine ordine) {
		// TODO Auto-generated method stub
		if(!ordini.contains(ordine)){ordini.add(ordine);}
		else{System.out.println("Ordine gia presente");}
	}

	@Override
	public void removeOrdine(Ordine ordine) {
		// TODO Auto-generated method stub
		if(ordini.contains(ordine)){ordini.remove(ordine);}
		else{System.out.println("Ordine non presente");}
	}

	public List<Ordine> getOrdini() {
		return ordini;
	}



}
