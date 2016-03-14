package br.jus.trt6.cursojee.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import br.jus.trt6.cursojee.entidades.Servidor;

public class ServidorDAO extends GenericDAO <Servidor> {

	List<Servidor>  servidores =  null;
	Query query = null;
	String namedQuery = null;

	@Override
	public Servidor buscar(Object matricula) {

		Query query = getEntityManager().createQuery("select s from Servidor s where s.id = ?");

		query.setParameter(1, matricula);		
		Servidor servidor = (Servidor) query.getSingleResult();

		return servidor; 																				
	}

	/**
	 * Consulta servidores de acordo com filtros.
	 * 
	 * @paramservidor Objeto cujos atributos preenchidos serão utilizados como
	 *                filtro.
	 * @paramnumeroMinimoProjetoCaso informado, considera apenas os servidores
	 *                               que trabalham em um número mínimo de
	 *                               projetos
	 * @paramapenasComDependentesCaso true, considera apenas os servidores que
	 *                                possuem dependentes.
	 * @returnLista de servidores que atendem àconsulta.
	 */
	@SuppressWarnings("unchecked")
	public List<Servidor> consultar(Servidor servidor, Integer numeroMinimoProjeto, boolean apenasComDependentes) {

		EntityManager em = getEntityManager();		


		if ( servidor == null && numeroMinimoProjeto == null && apenasComDependentes == false){

//			query = em.createNamedQuery("Servidor.queryTodosServidores");
//			servidores = query.getResultList();	


			Session session = (Session) getEntityManager().getDelegate();

			Criteria criteria = session.createCriteria(Servidor.class);	
			criteria.addOrder(Order.asc("nome"));		
			servidores = criteria.list();
			

		} else if( servidor != null && numeroMinimoProjeto == null && apenasComDependentes == false){

			query = em.createNamedQuery("Servidor.queryUmServidor");  // adaptar para o metodo NamedQuery

			query.setParameter("cpf", servidor.getCpf());
			query.setParameter("dataNascimento", servidor.getDataNascimento());
			query.setParameter("email", servidor.getEmail());
			query.setParameter("matricula", servidor.getMatricula());
			query.setParameter("nome", servidor.getNome());
			query.setParameter("situacao", servidor.getSituacao());

			servidores = query.getResultList();

		}else if( servidor != null && numeroMinimoProjeto != null && apenasComDependentes == false){

			query = em.createNamedQuery("Servidor.queryComProjetoMinimo");

			query.setParameter("cpf", servidor.getCpf());
			query.setParameter("numProjetos", numeroMinimoProjeto);
			servidores = query.getResultList();

		} else if( servidor != null && numeroMinimoProjeto == null && apenasComDependentes == true){

			query = em.createNamedQuery("Servidor.queryComDependentes");

			query.setParameter("cpf", servidor.getCpf());
			servidores = query.getResultList();

		}

		return servidores;
	}

	@SuppressWarnings("unchecked")
	public List<Servidor> listarRelatorio() {

		query = getEntityManager().createNamedQuery("Servidor.queryListarRelatorio");

		servidores = query.getResultList();

		return servidores;
	}
}
