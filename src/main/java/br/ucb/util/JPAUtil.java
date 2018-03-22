package br.ucb.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory factory;
	
	public static EntityManager getEntityManaged(){
		if(factory == null){
			factory = Persistence.createEntityManagerFactory("prodAc");
		}
		return factory.createEntityManager();	
	}
	
}
