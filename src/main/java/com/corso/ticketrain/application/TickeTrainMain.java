package com.corso.ticketrain.application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TickeTrainMain {

	public static void main(String[] args) {
        EntityManager manager = null;
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence");
            manager = factory.createEntityManager();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (manager != null && manager.isOpen()) {
                manager.close();
            }
        }
    }

}
