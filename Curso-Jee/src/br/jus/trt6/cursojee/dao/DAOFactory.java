package br.jus.trt6.cursojee.dao;

import javax.persistence.*;

import br.jus.trt6.cursojee.entidades.*;

/**
 * F�brica de DAO que sabe criar inst�ncias concretas de um DAO para determinada entidade
 * persistente.
 * @author augusto
 */
public class DAOFactory {

	/**
	 * Cria um DAO espec�fico para a entidade informada e configurado com o EntitManager informado.
	 * @param clazz Tipo da entidade para cria��o do DAO.
	 * @param em EntityManager para configura��o do DAO.
	 * @return DAO devidamente configurado para o tipo de entidade informado.
	 */
			
					//m�todo generioco	//retorno generico	//parametros genericos	
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
