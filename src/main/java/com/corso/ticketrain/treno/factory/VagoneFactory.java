package com.corso.ticketrain.treno.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.corso.ticketrain.treno.model.Locomotiva;
import com.corso.ticketrain.treno.model.Passeggeri;
import com.corso.ticketrain.treno.model.Ristorante;
import com.corso.ticketrain.treno.model.Vagone;




public class VagoneFactory implements VagoneFactoryInterface{
	
	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

	
	public Vagone costruisciVagone(char tipoVagone) {
		switch (tipoVagone) {
		case 'H':
			return (Locomotiva) context.getBean("Locomotiva");
		case 'P':
			return (Passeggeri) context.getBean("Passeggeri"); 
		case 'R':
			return (Ristorante) context.getBean("Ristorante"); 
		default:
			throw new IllegalArgumentException("Tipo di vagone non riconosciuto");
		}
	}

}
