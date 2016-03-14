package br.jus.trt6.cursojee.testes;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Classe genéricia para padronizar o comportamento das classes de teste.
 * Realiza o controle de acesso ao EntityManager corretamente.
 * @author augusto
 */
public class TesteGenerico extends Assert {

	/** Nome da PU da aplicação. */
	public static final String PU_CURSO_JEE = "cursoPU";
	
	/** Nome da PU de testes da aplicação. */
	public static final String PU_CURSO_JEE_TESTES = "cursoPUTest";	
	
	/** acesso compartilhado ao entityManager */
	private static final JPAStandaloneUtil jpa = criarJPAUtil(PU_CURSO_JEE_TESTES);

	/**
	 * Método de fábrica para criação de uma JPAStandaloneUtil.
	 * @param nomePU Nome da persistence unit.
	 */
	protected static JPAStandaloneUtil criarJPAUtil(String nomePU) {
		return new JPAStandaloneUtil(nomePU);
	}

	/** realiza commit ao final de cada teste */
	private boolean commitDepoisDoTeste = false;
	
	/** flag que determina se a base de dados já foi criada por algum teste */
	private static boolean dadosInicializados = false;
	
	@BeforeClass
	public static void prepararDados() throws Exception {
		if (!dadosInicializados) {
			PrepararBaseDadosUtil.prepararDados();
			dadosInicializados = true;
		}
	}
	
	@Before
	public void preparar() {
		if (jpa != null) {
			jpa.iniciarSessao();
			jpa.iniciarTransacao();
		}		
	}
	
	@After
	public void finalizar() {
		// se o teste pretende comitar, realiza o comit e encerra a transação
		if (jpa != null) {
			if (commitDepoisDoTeste) {
				jpa.concluirTransacao();
			} else {
				jpa.desfazerTransacao();
			}
			jpa.encerrarSessao();
		}
	}	
	
	/** Acessa à classe utilitário de recursos do JPA */
	protected static JPAStandaloneUtil getJpa() {
		return jpa;
	}
	
}
