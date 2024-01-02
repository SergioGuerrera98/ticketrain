package com.corso.ticketrain.application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.corso.ticketrain.config.AppConfig;
import com.corso.ticketrain.dao.CittaDao;
import com.corso.ticketrain.dao.DaoInterface;
import com.corso.ticketrain.model.Citta;
import com.corso.ticketrain.service.CittaService;


public class TickeTrainMain {
	
	//static CittaDao cittaDao;

	public static void main(String[] args) {
		EntityManager manager = null;
		try {
			/* EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence");
            manager = factory.createEntityManager();
			ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
			 */

			BeanFactory factory = new AnnotationConfigApplicationContext(AppConfig.class);
			//CittaDao cittaDao = (CittaDao)factory.getBean("cittaDao", CittaDao.class);
			//System.out.println(cittaDao);
			CittaService service = new CittaService();
			Citta citta = new Citta();
			citta.setNomeCitta("torino");
			service.insert(citta);
			//cittaDao.create(citta);
			System.out.println("provaprovaprova");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (manager != null && manager.isOpen()) {
				manager.close();
			}
		}
	}

}
