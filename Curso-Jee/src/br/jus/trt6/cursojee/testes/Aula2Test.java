package br.jus.trt6.cursojee.testes;

import org.junit.Test;

/**
 * Testa o exercício da aula 2: Configuração de PU 
 * @author augusto
 */
public class Aula2Test extends TesteGenerico {

	/**
	 * Testa conexão com o banco de dados da aplicação
	 */
	@Test
	public void validarConexaoPUAplicacao() {
		
		// criando uma conexão com o banco da aplicação, já que o teste utiliza o banco de testes por padrão
		JPAStandaloneUtil jpaUtil = criarJPAUtil(PU_CURSO_JEE);
		jpaUtil.iniciarSessao();
		
		// faz um select qualquer para testar criação do banco de dados
		try {
			jpaUtil.getEm().createNativeQuery("VALUES CURRENT_TIMESTAMP").getResultList();
		} finally {
			jpaUtil.encerrarSessao();
		}
		
	}
	
	/**
	 * Testa conexão com o banco de dados de testes da aplicação
	 */
	@Test
	public void validarConexaoPUTestes() {
		// faz um select qualquer para testar criação do banco de dados
		getJpa().getEm().createNativeQuery("VALUES CURRENT_TIMESTAMP").getResultList();
	}
}
