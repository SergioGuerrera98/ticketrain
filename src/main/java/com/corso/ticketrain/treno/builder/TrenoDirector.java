package com.corso.ticketrain.treno.builder;

import java.util.ArrayList;
import java.util.List;


import com.corso.ticketrain.treno.exceptions.LocomotivaInCentroException;
import com.corso.ticketrain.treno.exceptions.TrenoException;
import com.corso.ticketrain.treno.exceptions.TroppiRistorantiException;
import com.corso.ticketrain.treno.factory.VagoneFactory;
import com.corso.ticketrain.treno.model.Locomotiva;
import com.corso.ticketrain.treno.model.Ristorante;
import com.corso.ticketrain.treno.model.Treno;
import com.corso.ticketrain.treno.model.Vagone;
import com.corso.ticketrain.treno.utils.UtilsTreno;



public class TrenoDirector {
private VagoneFactory vagoneFactory;
	
	public TrenoDirector(VagoneFactory vagoneFactory) {
		super();
		this.vagoneFactory = vagoneFactory;
	}
	
	public void aggiungiVagoni(String sigla, Treno treno) throws TrenoException {
		List<Vagone> listaVagoni = treno.getVagoni();
		StringBuilder stringBuilder = treno.getSiglaBuilder();
		stringBuilder.append(sigla);
		String composizioneTreno = stringBuilder.toString();
		if (sigla.length() > 1 && sigla.substring(1, sigla.length() - 1).contains("H")) {
		    throw new LocomotivaInCentroException(sigla);
		}
        if (composizioneTreno.chars().filter(c -> c == 'R').count() > 1) {
			 throw new TroppiRistorantiException(sigla);
		}
		List<Vagone> listaTemporanea = new ArrayList<>(listaVagoni);
		UtilsTreno.controlloPeso(listaTemporanea);
		Vagone ristoranteCentrale = ristoranteAlCentro(listaVagoni);
		Vagone locomotivaFinale = locomotivaFinale(listaVagoni);
		for (char tipoVagone : sigla.toCharArray()) {
			Vagone vagone = vagoneFactory.costruisciVagone(tipoVagone);
			listaVagoni.add(vagone);
		}
		if (locomotivaFinale != null) {
			listaVagoni.add(locomotivaFinale);
		}
		if (ristoranteCentrale != null) {
			int indiceCentrale = listaVagoni.size() / 2;
			listaVagoni.add(indiceCentrale, ristoranteCentrale);
		}
		
	}
	
	public void rimuoviVagoni(char charVagone , Treno treno) throws TrenoException {
		List<Vagone> listaVagoni = treno.getVagoni();
		switch (charVagone) {
		case 'H':
			if (!(listaVagoni.get(listaVagoni.size()-1) instanceof Locomotiva)) {
				throw new IllegalArgumentException("Non puoi rimuovere la Locomotiva in testa");
			}
			break;
		case 'P':
			break;
		case 'R':
			break;
		default:
			throw new IllegalArgumentException("Tipo di vagone non riconosciuto");
		}
		Vagone ristoranteCentrale = ristoranteAlCentro(listaVagoni);
		for (int i = listaVagoni.size() - 1; i >= 0; i--) {
			Vagone vagone = listaVagoni.get(i);
			if (vagone.getCarattere() == charVagone) {
				listaVagoni.remove(i);
				break;
			}
		}
		if (ristoranteCentrale != null) {
			int indiceCentrale = listaVagoni.size() / 2;
			listaVagoni.add(indiceCentrale, ristoranteCentrale);
		}
	}
	
	private Vagone locomotivaFinale(List<Vagone> listaVagoni) {
		Vagone locomotiva = null;
		if (listaVagoni.size()>1 && listaVagoni.get(listaVagoni.size()-1).getCarattere() == 'H') {
			locomotiva = listaVagoni.get(listaVagoni.size()-1);
			listaVagoni.remove(listaVagoni.size() - 1);
			return locomotiva;
		}
		return locomotiva;
	}
	
	private Vagone ristoranteAlCentro(List<Vagone> listaVagoni) {
		Vagone ristorante = null;
		for (Vagone vagone : listaVagoni) {
			if (vagone instanceof Ristorante) {
				ristorante = vagone;
				listaVagoni.remove(vagone);
				return ristorante;
			}
		}
		return ristorante;
	}

}
