package br.ucb.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	public static EntityManager getEntityManaged(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("prodAc");
		return emf.createEntityManager();	
	}
	
}
