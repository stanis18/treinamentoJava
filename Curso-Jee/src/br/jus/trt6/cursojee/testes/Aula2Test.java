package br.jus.trt6.cursojee.testes;

import org.junit.Test;

/**
 * Testa o exerc�cio da aula 2: Configura��o de PU 
 * @author augusto
 */
public class Aula2Test extends TesteGenerico {

	/**
	 * Testa conex�o com o banco de dados da aplica��o
	 */
	@Test
	public void validarConexaoPUAplicacao() {
		
		// criando uma conex�o com o banco da aplica��o, j� que o teste utiliza o banco de testes por padr�o
		JPAStandaloneUtil jpaUtil = criarJPAUtil(PU_CURSO_JEE);
		jpaUtil.iniciarSessao();
		
		// faz um select qualquer para testar cria��o do banco de dados
		try {
			jpaUtil.getEm().createNativeQuery("VALUES CURRENT_TIMESTAMP").getResultList();
		} finally {
			jpaUtil.encerrarSessao();
		}
		
	}
	
	/**
	 * Testa conex�o com o banco de dados de testes da aplica��o
	 */
	@Test
	public void validarConexaoPUTestes() {
		// faz um select qualquer para testar cria��o do banco de dados
		getJpa().getEm().createNativeQuery("VALUES CURRENT_TIMESTAMP").getResultList();
	}
}
