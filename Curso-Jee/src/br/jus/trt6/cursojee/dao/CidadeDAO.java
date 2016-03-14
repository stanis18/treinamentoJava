package br.jus.trt6.cursojee.dao;
import br.jus.trt6.cursojee.entidades.*;
import java.util.*;
import javax.persistence.*;



public class CidadeDAO extends GenericDAO <Cidade>{
	
	public List<Cidade> consultar(UF uf) {

		EntityManager em = getEntityManager();

		Query query = em.createNamedQuery("Cidade.queryCidadePorUf");
		query.setParameter("ufId", uf.getId());

		@SuppressWarnings("unchecked")
		List<Cidade> lista = query.getResultList();

		return lista;
				
	}

}
