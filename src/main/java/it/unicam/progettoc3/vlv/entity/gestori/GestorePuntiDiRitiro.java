package it.unicam.progettoc3.vlv.entity.gestori;

import java.util.ArrayList;
import java.util.List;

import it.unicam.progettoc3.vlv.entity.elementi.PuntoDiRitiro;


public class GestorePuntiDiRitiro implements IGestorePuntiDiRitiro {
	private List<PuntoDiRitiro> puntiDiRitiro = new ArrayList<>();
	@Override
	public void addPuntoDiRitiro(PuntoDiRitiro puntoDiRitiro) {
		// TODO Auto-generated method stub
			if(!puntiDiRitiro.contains(puntoDiRitiro)){puntiDiRitiro.add(puntoDiRitiro);}
			else{System.out.print("Punto di ritiro gia presente");}
	}

	@Override
	public void removePuntoDiRitiro(PuntoDiRitiro puntoDiRitiro) {
		// TODO Auto-generated method stub
		if(puntiDiRitiro.contains(puntoDiRitiro)){puntiDiRitiro.remove(puntoDiRitiro);}
		else{System.out.print("Punto di ritiro non presente");}
	}

	public List<PuntoDiRitiro> getPuntiDiRitiro() {
		return puntiDiRitiro;
	}

}
