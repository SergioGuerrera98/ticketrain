package com.corso.ticketrain.application;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.corso.ticketrain.config.AppConfig;
import com.corso.ticketrain.model.Citta;
import com.corso.ticketrain.model.Paese;
import com.corso.ticketrain.model.Ticket;
import com.corso.ticketrain.model.User;
import com.corso.ticketrain.service.CittaService;
import com.corso.ticketrain.service.PaeseService;
import com.corso.ticketrain.service.TicketService;
import com.corso.ticketrain.service.TrenoService;
import com.corso.ticketrain.service.UserService;
import com.corso.ticketrain.service.exceptions.DataPrecedenteException;
import com.corso.ticketrain.service.exceptions.PaeseNonTrovatoException;
import com.corso.ticketrain.treno.exceptions.TrenoException;
import com.corso.ticketrain.treno.factory.VagoneFactory;
import com.corso.ticketrain.treno.model.Treno;
import com.corso.ticketrain.treno.model.Vagone;


@ComponentScan(basePackages = {"com.corso.ticketrain.model", "com.corso.ticketrain.treno.model", "com.corso.ticketrain.dao"})
public class Test {

	public static void main(String[] args) throws TrenoException {
		AnnotationConfigApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);
		try {
			testFilter(factory);
		} catch (TrenoException | DataPrecedenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//test2(factory);
	}
	
	
	
	public static void test2(AnnotationConfigApplicationContext factory) {
		UserService userService = factory.getBean(UserService.class);
		PaeseService paeseService = factory.getBean(PaeseService.class);
		Paese p = new Paese("Italia");
		paeseService.insertPaese(p);
		//User user = userService.registrazione("prova", "1234", "italia");
		//System.out.println(user);
	}
	
	public static void testFilter(AnnotationConfigApplicationContext factory) throws TrenoException, DataPrecedenteException {
		PaeseService paeseService = factory.getBean(PaeseService.class);
		TicketService ticketService = factory.getBean(TicketService.class);
		CittaService cittaService = factory.getBean(CittaService.class);
		TrenoService trenoService = factory.getBean(TrenoService.class);
		
		Paese i = new Paese("Italia");
		Citta c = new Citta("Napoli", i);
		Citta c2 = new Citta("Catania", i);
		Citta c3 = new Citta("Bari", i);
		Citta c4 = new Citta("Torino", i);
		paeseService.insertPaese(i);
		cittaService.insert(c);
		cittaService.insert(c2);
		cittaService.insert(c3);
		cittaService.insert(c4);


		Treno treno = trenoService.addTrain2("HPRPH", new VagoneFactory());
		Vagone vagone = treno.getVagoni().get(2);
		Ticket t = new Ticket("jhefj1", LocalDateTime.of(2019, 01, 22, 10, 30), LocalDateTime.of(2021, 01, 22, 10, 30), "Napoli", "Bari", 22.0f, treno, vagone, "Economy");
		Ticket t1 = new Ticket("jhefj2", LocalDateTime.of(2022, 01, 22, 10, 30), LocalDateTime.of(2021, 01, 22, 10, 30), "Catania", "Torino", 22.0f, treno, vagone, "Economy");
		Ticket t2 = new Ticket("jhefj3", LocalDateTime.of(2020, 01, 22, 10, 30), LocalDateTime.of(2021, 01, 22, 10, 30), "Napoli", "Torino", 22.0f, treno, vagone, "Economy");
		Ticket t3 = new Ticket("jhefj4", LocalDateTime.of(2020, 01, 22, 10, 30), LocalDateTime.of(2021, 01, 22, 10, 30), "Catania", "Bari", 22.0f, treno, vagone, "Economy");
		ticketService.insert(t);
		ticketService.insert(t2);
		ticketService.insert(t3);
		ticketService.insert(t1);
	


		List<Ticket> listaFiltrata;
		try {
			listaFiltrata = ((TicketService)ticketService).getTicketsFilter("Catania", "Torino", null);
			listaFiltrata = ((TicketService)ticketService).getTicketsFilter("Catania", null, null);//2007-12-03T10:15:30
			System.out.println("Catania-torino-2022: " + listaFiltrata);
			
			System.out.println("Catania-?-?: " + listaFiltrata);
		} catch (PaeseNonTrovatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//2007-12-03T10:15:30

	}
	
}
