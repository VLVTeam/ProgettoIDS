package it.unicam.progettoc3.vlv.entity.gestori;

import java.util.List;
import java.util.function.Predicate;

import it.unicam.progettoc3.vlv.entity.elementi.Promozione;

public interface IGestorePromozioni {

	void addPromozione(Promozione promozione);
	void removePromozione(Promozione promozione);
	List<Promozione> filtraPromozioni(Predicate<Promozione> p);
}
