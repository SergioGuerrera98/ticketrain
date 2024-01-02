package com.corso.ticketrain.application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.corso.ticketrain.config.AppConfig;
import com.corso.ticketrain.dao.CittaDao;
import com.corso.ticketrain.dao.DaoInterface;
import com.corso.ticketrain.model.Citta;
import com.corso.ticketrain.model.Paese;

@ComponentScan(basePackages = {"com.corso.ticketrain.model", "com.corso.ticketrain.treno.model", "com.corso.ticketrain.dao"})
public class Test {

	public static void main(String[] args) {
		test00();

	}

	public static void test00() {
		   AnnotationConfigApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);
		   DaoInterface<Paese> paeseDao = (DaoInterface<Paese>) factory.getBean("paeseDao");
		   System.out.println(paeseDao);	
		   Paese p = new Paese();
		   p.setNomePaese("Italia2");
		   paeseDao.create(p);
		   //Citta c = new Citta("Milano", p);
		   //cittaDao.create(c);
	}
}
