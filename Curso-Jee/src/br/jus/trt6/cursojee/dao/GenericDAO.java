package br.jus.trt6.cursojee.dao;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class GenericDAO <TIPO extends Object> implements IDAO <TIPO> {
		
	private EntityManager entityManager;
	private Class<TIPO> tipoEntidade;
		
	
	@Override
	public void inserir(TIPO entidade) {
				
		entityManager.persist(entidade);
		entityManager.flush();
	}


	@Override
	public TIPO buscar(Object id) {
		 					
		return entityManager.find(tipoEntidade, id); 								
	}

	@Override
	public void recarregar(TIPO entidade) {
		
		entityManager.refresh(entidade);
		entityManager.flush();
	}

	@Override
	public void atualizar(TIPO entidade) {

		entityManager.merge(entidade);
		entityManager.flush();		
	}

	@Override
	public void excluir(TIPO entidade) {
		
		entidade = entityManager.merge(entidade);	
		entityManager.remove(entidade);
		entityManager.flush();
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void setTipoEntidade(Class<TIPO> tipoEntidade){
		
		this.tipoEntidade = tipoEntidade;
	}
			
	public EntityManager getEntityManager() {
		return entityManager;
	}


	//novo método que dever ser implementado...
	@SuppressWarnings("unchecked")
	@Override	
	public List<TIPO> consultar(String hql, Object... parametros) {
		
		Query query = entityManager.createQuery(hql);
		
		if (parametros != null) {
			
			for (int i = 0; i < parametros.length; i++) {
				
				Object parametro = parametros[i];
				query.setParameter(i+1, parametro);
			}
		}
		return query.getResultList();	
	}


	@Override
	public List<TIPO> consultarNamedQuery(String namedQuery, Object... parametros) {
		
		//metodo ainda nao utilizado..
		
		
		return null;
	}
		
}
