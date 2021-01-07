package it.unicam.progettoc3.vlv.gestori;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import it.unicam.progettoc3.vlv.elementi.Promozione;

public class GestorePromozioni implements IGestorePromozioni {
	
	private List<Promozione> promozioni = new ArrayList<>();


	public List<Promozione> getPromozioni() {
		return promozioni;
	}

	@Override
	public void addPromozione(Promozione promozione) {
		// TODO Auto-generated method stub
		if(!promozioni.contains(promozione)){promozioni.add(promozione);}
		else{System.out.println("La promozione è gia presente");}
	}

	@Override
	public void removePromozione(Promozione promozione) {
		// TODO Auto-generated method stub
		if(promozioni.contains(promozione)){promozioni.add(promozione);}
		else{System.out.println("La promozione non è  presente");}
	}

	@Override
	public List<Promozione> filtraPromozioni(Predicate<Promozione> p) {
		// TODO Auto-generated method stub
        return promozioni.stream().filter(p).collect(Collectors.toList());
        //return promozioni.stream().filter(p).collect(Collectors.<Promozione>toList());
	}

}
