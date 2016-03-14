package br.jus.trt6.cursojee.dao;

import javax.persistence.*;

import br.jus.trt6.cursojee.entidades.*;

/**
 * Fábrica de DAO que sabe criar instâncias concretas de um DAO para determinada entidade
 * persistente.
 * @author augusto
 */
public class DAOFactory {

	/**
	 * Cria um DAO específico para a entidade informada e configurado com o EntitManager informado.
	 * @param clazz Tipo da entidade para criação do DAO.
	 * @param em EntityManager para configuração do DAO.
	 * @return DAO devidamente configurado para o tipo de entidade informado.
	 */
			
					//método generioco	//retorno generico	//parametros genericos	
	@SuppressWarnings("unchecked")
	public static <TIPO extends Object> GenericDAO<TIPO> createDAO( Class<TIPO> clazz, EntityManager em) {
										//mudei o tipo de retorno										
		
		GenericDAO<TIPO> genericDAO = null;

		if( clazz.equals(Cidade.class) ){

			genericDAO = (GenericDAO<TIPO>) new CidadeDAO();

		} else if(clazz.equals(Servidor.class) ){

			genericDAO = (GenericDAO<TIPO>) new ServidorDAO();
					
		}else{

			genericDAO = new GenericDAO<TIPO>();							
		}
				
		genericDAO.setEntityManager(em);
		genericDAO.setTipoEntidade(clazz);		
		return genericDAO;
	}
	
}
