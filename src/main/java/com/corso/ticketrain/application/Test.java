package com.corso.ticketrain.application;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.corso.ticketrain.config.AppConfig;
import com.corso.ticketrain.dao.CittaDao;
import com.corso.ticketrain.dao.DaoInterface;
import com.corso.ticketrain.dao.PaeseDao;
import com.corso.ticketrain.dao.TicketDao;
import com.corso.ticketrain.dao.TrenoDao;
import com.corso.ticketrain.dao.interfacce.TicketDaoInterface;
import com.corso.ticketrain.dao.interfacce.TrenoDaoInterface;
import com.corso.ticketrain.model.Citta;
import com.corso.ticketrain.model.Paese;
import com.corso.ticketrain.model.Ticket;
import com.corso.ticketrain.service.IService;
import com.corso.ticketrain.service.TicketService;
import com.corso.ticketrain.service.TrenoService;
import com.corso.ticketrain.treno.builder.TrenoBuilder;
import com.corso.ticketrain.treno.exceptions.TrenoException;
import com.corso.ticketrain.treno.factory.VagoneFactory;
import com.corso.ticketrain.treno.model.Treno;
import com.corso.ticketrain.treno.model.Vagone;

@ComponentScan(basePackages = {"com.corso.ticketrain.model", "com.corso.ticketrain.treno.model", "com.corso.ticketrain.dao"})
public class Test {

	public static void main(String[] args) throws TrenoException {
		AnnotationConfigApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);
		testFilter(factory);
		//test00(factory);

	}

	public static void test00(AnnotationConfigApplicationContext factory) {

		DaoInterface<Paese> paeseDao = (DaoInterface<Paese>) factory.getBean("paeseDao");
		System.out.println(paeseDao);	
		Paese p = new Paese();
		p.setNomePaese("Italia");
		//paeseDao.create(p);
		//Citta c = new Citta("Milano", p);
		//cittaDao.create(c);
	}

	public static void testFilter(AnnotationConfigApplicationContext factory) throws TrenoException {
		TicketDaoInterface ticketDao = (TicketDaoInterface) factory.getBean("ticketDao");
		DaoInterface<Paese> paeseDao = (DaoInterface<Paese>) factory.getBean("paeseDao");
		DaoInterface<Citta> cittaDao = (DaoInterface<Citta>) factory.getBean("cittaDao");
		TrenoDaoInterface trenoDao = (TrenoDaoInterface) factory.getBean("trenoDao");
		//IService ticketService = (IService) factory.getBean("ticketService");
		//IService trenoService = (IService) factory.getBean("trenoService");
		TicketService ticketService = new TicketService(ticketDao);
		TrenoService trenoService = new TrenoService(trenoDao);
		/*
		TicketDao ticketDao = (TicketDao)factory.getBean("ticketDao");
		PaeseDao paeseDao = (PaeseDao)factory.getBean("paeseDao");
		CittaDao cittaDao = (CittaDao)factory.getBean("cittaDao");
		TrenoDao trenoDao = (TrenoDao)factory.getBean("trenoDao");
		TicketService ticketService = new TicketService(ticketDao);
		TrenoService trenoService = new TrenoService(trenoDao);*/

		Paese i = new Paese("Italia");
		Citta c = new Citta("Napoli", i);
		Citta c2 = new Citta("Catania", i);
		Citta c3 = new Citta("Bari", i);
		Citta c4 = new Citta("Torino", i);
		paeseDao.create(i);
		cittaDao.create(c);
		cittaDao.create(c2);
		cittaDao.create(c3);
		cittaDao.create(c4);


		Treno treno = ((TrenoService)trenoService).addTrain2("HPRPH", new VagoneFactory());
		Vagone vagone = treno.getVagoni().get(2);
		Ticket t = new Ticket("jhefj1", LocalDateTime.of(2019, 01, 22, 10, 30), LocalDateTime.of(2021, 01, 22, 10, 30), "Napoli", "Bari", 22.0f, treno, vagone);
		Ticket t1 = new Ticket("jhefj2", LocalDateTime.of(2022, 01, 22, 10, 30), LocalDateTime.of(2021, 01, 22, 10, 30), "Catania", "Torino", 22.0f, treno, vagone);
		Ticket t2 = new Ticket("jhefj3", LocalDateTime.of(2020, 01, 22, 10, 30), LocalDateTime.of(2021, 01, 22, 10, 30), "Napoli", "Torino", 22.0f, treno, vagone);
		Ticket t3 = new Ticket("jhefj4", LocalDateTime.of(2020, 01, 22, 10, 30), LocalDateTime.of(2021, 01, 22, 10, 30), "Catania", "Bari", 22.0f, treno, vagone);
		ticketDao.create(t);
		ticketDao.create(t1);
		ticketDao.create(t2);
		ticketDao.create(t3);


		List<Ticket> listaFiltrata = ((TicketService)ticketService).getTicketsFilter("Catania", "Torino", "2020-01-22T00:00:00");//2007-12-03T10:15:30
		System.out.println("Catania-torino-2022: " + listaFiltrata);
		listaFiltrata = ((TicketService)ticketService).getTicketsFilter("Catania", null, null);//2007-12-03T10:15:30
		System.out.println("Catania-?-?: " + listaFiltrata);
	}
}
