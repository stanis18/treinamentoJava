package br.jus.trt6.cursojee.dao;

import java.util.List;


/**
 * Interface que define as opera��es de persist�ncia sobre uma entidade mapeada.
 * @param TIPO Define o tipo da entidade persistente associada a este DAO. 
 */
public interface IDAO <TIPO extends Object> {

	/**
	 * Recupera um objeto a partir do id
	 * @param id Identificador da entidade.
	 * @return A entidade.
	 */
	public TIPO buscar(Object id);

	/**
	 * Restaura os dados da entidade assim como est�o na base de dados.
	 * @param entidade a entidade.
	 */
	public void recarregar(TIPO entidade);
	
	
	/**
	 * Realiza a inclus�o de uma entidade na base de dados.
	 * @param entidade Entidade para inclus�o.
	 */
	public void inserir(TIPO entidade);
	
	/**
	 * Realiza a atualiza��o dos dados da entidade na base de dados.
	 * @param entidade Entidade para atualiza��o de dados.
	 */
	public void atualizar(TIPO entidade);
	
	/**
	 * Exclui uma entidade da base de dados.
	 * @param entidade Entidade a ser exclu�da.
	 */
	public void excluir(TIPO entidade);
	
	/**
	 * Realiza uma consulta na base de dados a partir de um HQL definido, com o resultado
	 * composto de uma cole��o da entidade associada a este DAO.
	 * @param hql Query a ser executada.
	 * @param parametros Par�metros ordenados para serem considerados na query.
	 * @return Lista de entidades.
	 */
	List<TIPO> consultar(String hql, Object...parametros);
		
	
	List<TIPO> consultarNamedQuery(String namedQuery, Object...parametros);
	
}
