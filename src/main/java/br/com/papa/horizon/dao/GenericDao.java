package br.com.papa.horizon.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.papa.horizon.util.Util;

/**
 * 
 * @author Henry O' Papa
 *
 * @param <T>
 */

public abstract class GenericDao <T extends Serializable>{
	
	private Class<T> aClass;
	
	protected GenericDao(Class<T> aClass){
		this.aClass = aClass;
	}
	
	protected EntityManager getEntityManager(){
		return Util.getInstance().getEntityManager();
	}
	
	public long count(){
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();
		
		Query query = manager.createQuery("select count(c) from " + aClass.getSimpleName() + " c");
		
		long count = (Long) query.getSingleResult();
		
		manager.getTransaction().commit();
		manager.close();
		
		return count;
	}
	
	
	@SuppressWarnings("unchecked")
	public T findOne(String jpql, Object... params){
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();
		
		Query query = manager.createQuery(jpql);
		
		for(int i=0; i < params.length; i++){
			query.setParameter(i+1, params[i]);
		}
		
		
		T entity = (T)query.getSingleResult();
		
		
		manager.getTransaction().commit();
		manager.close();
		
		return entity;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> find(String jpql, Object... params){
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();
		
		Query query = manager.createQuery(jpql);
		
		for(int i=0; i < params.length; i++){
			query.setParameter(i+1, params[i]);
		}
		
		
		List<T> entities = query.getResultList();
		
		
		manager.getTransaction().commit();
		manager.close();
		
		return entities;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();
		
		Query query = manager.createQuery("from " + aClass.getSimpleName());
		List<T> entities = query.getResultList();
		
		manager.getTransaction().commit();
		manager.close();
		
		return entities;
	}
	
	
	/*
	 * O findById recebe um ID como parâmetro
	 * e busca no banco de dados o mesmo id na tabela
	 * referente ao Objeto "aClass"
	 */
	
	public T findById(Long id){
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();
		
		T entity = (T) manager.find(aClass, id);
		
		manager.getTransaction().commit();
		manager.close();
		
		
		return entity;
	}
	
	/*
	 * O update recebe um Objeto qualquer e salva
	 * na tabela referente ao Objeto passado
	 */
	public void save(T entity) {
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();
		manager.persist(entity);
		manager.getTransaction().commit();
		manager.close();
	}
	
	/*
	 * O update recebe um Objeto qualquer e salva as alterações
	 * na tabela referente ao Objeto passado
	 */
	public void update(T entity){
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();
		manager.merge(entity);
		manager.getTransaction().commit();
		manager.close();
	}
	
	/*
	 * O delete recebe um Objeto qualquer
	 * e deleta ele utilizando sua ID como refencia 
	 * com a tabela no banco de dados
	 */
	public void delete(Long id){
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.getReference(aClass, id));
		manager.getTransaction().commit();
		manager.close();
	}
	
	
	public void delete(T entity){
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.merge(entity));
		manager.getTransaction().commit();
		manager.close();
	}
}
 