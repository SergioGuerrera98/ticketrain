package com.corso.ticketrain.treno.builder;

import java.util.ArrayList;
import java.util.List;


import com.corso.ticketrain.treno.exceptions.LocomotivaInCentroException;
import com.corso.ticketrain.treno.exceptions.LocomotivaNonInTestaException;
import com.corso.ticketrain.treno.exceptions.RistoranteNonAlCentroException;
import com.corso.ticketrain.treno.exceptions.TrenoException;
import com.corso.ticketrain.treno.exceptions.TroppiRistorantiException;
import com.corso.ticketrain.treno.factory.VagoneFactory;
import com.corso.ticketrain.treno.model.Treno;
import com.corso.ticketrain.treno.model.Vagone;
import com.corso.ticketrain.treno.utils.UtilsTreno;


public class TrenoBuilder implements TrenoBuilderInterface{
	
	private VagoneFactory vagoneFactory;
	private List<Vagone> listaVagoni;
	
	public TrenoBuilder(VagoneFactory vagoneFactory) {
		super();
		this.vagoneFactory = vagoneFactory;
	}

	
	final public Treno costruisciTreno(String sigla) throws TrenoException {
		controllaSigla(sigla);
		this.listaVagoni = new ArrayList<>();
		for (char tipoVagone : sigla.toCharArray()) {
			 costruisciVagone(tipoVagone);
		}
		UtilsTreno.controlloPeso(listaVagoni);
		return new Treno(sigla, "TickeTrain", listaVagoni);
	}
	
	private void costruisciVagone(char tipoVagone) {
		listaVagoni.add(vagoneFactory.costruisciVagone(tipoVagone));
	}
	
	private void controllaSigla(String sigla) throws TrenoException {
		if (sigla.length() < 1 || sigla.charAt(0) != 'H') {
	        throw new LocomotivaNonInTestaException(sigla);
	    }
		if (sigla.length() > 1 && sigla.substring(1, sigla.length() - 1).contains("H")) {
		    throw new LocomotivaInCentroException(sigla);
		}
		if (sigla.chars().filter(c -> c == 'R').count() > 1) {
			 throw new TroppiRistorantiException(sigla);
		}
		if (sigla.contains("R")) {
	        int indiceR = sigla.indexOf("R");
	        int lunghezza = sigla.length();
	        int posizioneCentrale = lunghezza / 2;
	        if (indiceR != posizioneCentrale) {
	            throw new RistoranteNonAlCentroException(sigla);
	        }
	    }
	}

}
