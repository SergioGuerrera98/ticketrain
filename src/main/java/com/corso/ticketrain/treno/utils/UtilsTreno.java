package com.corso.ticketrain.treno.utils;

import java.util.List;


import com.corso.ticketrain.treno.exceptions.PesoEccessivoException;
import com.corso.ticketrain.treno.exceptions.TrenoException;
import com.corso.ticketrain.treno.model.Locomotiva;
import com.corso.ticketrain.treno.model.Vagone;



public class UtilsTreno {

	static public void controlloPeso(List<Vagone> listaVagoni) throws TrenoException {
		Locomotiva testa = (Locomotiva) listaVagoni.get(0);
		double pesoTrainabile = testa.getPesoTrainabile();
		double pesoTreno = 0;
		for (Vagone vagone : listaVagoni) {
			pesoTreno += vagone.getPeso();
		}
		if (pesoTrainabile < pesoTreno) {
			throw new PesoEccessivoException();
		}
	}
}
