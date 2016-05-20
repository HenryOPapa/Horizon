package br.com.papa.horizon.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Util {
	
	private EntityManagerFactory factory;
	private static Util instance;
	
	private Util(){
		this.factory = Persistence.createEntityManagerFactory("horizon");		
	}
	
	public static synchronized Util getInstance(){
		if (instance == null){
			instance = new Util();
		}
		return instance;
	}
	
	public EntityManager getEntityManager(){
		return factory.createEntityManager();
	}

}
