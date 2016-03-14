package br.jus.trt6.cursojee.testes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.jus.trt6.cursojee.entidades.*;
import br.jus.trt6.cursojee.entidades.Servidor.Situacao;

public class Aula5Test extends TesteGenerico {

	@Test
	public void testConsultaServidor() {
		
		// consulta servidores 
		List<Servidor> servidores = getJpa().listar(Servidor.class);
		
		// garante que a lista não esta vazia
		assertFalse("Deveria ter encontrado servidores.", servidores == null || servidores.isEmpty());
		
		// testa o acesso à propriedades herdadas da classe Pessoa
		Servidor servidor = servidores.get(0);
		
		assertNotNull("Deveria ter herdado o nome de Pessoa.", servidor.getNome());
		assertNotNull("Deveria ter herdado a cidade de Pessoa.", servidor.getIdCidade());
		assertNotNull("Deveria ter herdado a cidade e uf de Pessoa.", servidor.getIdCidade().getuf());
	}
	
	@Test
	public void testConsultaDependente() {
		
		// consulta dependentes 
		List<Dependente> dependentes = getJpa().listar(Dependente.class);
		
		// garante que a lista não esta vazia
		assertFalse("Deveria ter encontrado dependentes.", dependentes == null || dependentes.isEmpty());
		
		// testa o acesso à propriedades herdadas da classe Pessoa
		Dependente dependente = dependentes.get(0);
		
		assertNotNull("Deveria ter herdado o nome de Pessoa.", dependente.getNome());
		assertNotNull("Deveria ter herdado a cidade de Pessoa.", dependente.getIdCidade());
		assertNotNull("Deveria ter herdado a cidade e uf de Pessoa.", dependente.getIdCidade().getuf());
	}
	
	@Test
	public void testConsultaPolimorfica() {
		
		// consulta pessoas, esperando conter todos os servidores e dependentes 
		List<Pessoa> pessoas = getJpa().listar(Pessoa.class);
		
		// garante que a lista não esta vazia
		assertFalse("Deveria ter encontrado pessoas.", pessoas == null || pessoas.isEmpty());
		
		// consulta todos os servidores
		List<Servidor> servidores = getJpa().listar(Servidor.class);
		
		// consulta todos os dependentes
		List<Dependente> dependentes = getJpa().listar(Dependente.class);
		
		// verifica se a lista de pessoas é igual à lista de servidores + dependentes
		List<Pessoa> servDependentes = new ArrayList<Pessoa>();
		servDependentes.addAll(servidores);
		servDependentes.addAll(dependentes);
		
		assertEquals("As listas de pessoas e servidores + dependentes deveriam ser do mesmo tamanho", pessoas.size(), servDependentes.size());
		assertTrue("A lista de pessoas deveria conter todos os servidores e dependentes", pessoas.containsAll(servDependentes));
		assertTrue("A lista de servidores e dependentes deveria conter todas as pessoas", servDependentes.containsAll(pessoas));
	}
	
	@Test
	public void testPersistindoServidor() {
		// tenta persistir um servidor. Espera-se que não ocorra erro
		Servidor servidor = new Servidor();
		servidor.setIdCidade(getJpa().listar(Cidade.class, 0, 1).get(0));
		servidor.setCpf("99999999999");
		servidor.setDataNascimento(new Date());
		servidor.setEmail("serv@email.com");
		servidor.setNome("Nome 1");
		
		servidor.setSituacao(Situacao.A);
		servidor.setMatricula("123456");
		
		getJpa().salvar(servidor);
		getJpa().getEm().flush();
	}
	
	@Test
	public void testPersistindoDependente() {
		// tenta persistir um dependente. Espera-se que não ocorra erro
		Dependente dependente = new Dependente();
		dependente.setIdCidade(getJpa().listar(Cidade.class, 0, 1).get(0));
		dependente.setCpf("99999999999");
		dependente.setDataNascimento(new Date());
		dependente.setEmail("serv@email.com");
		dependente.setNome("Nome 1");
		
		dependente.setDataInicio(new Date());
		dependente.setId_servidor(getJpa().listar(Servidor.class, 0, 1).get(0));
		
		getJpa().salvar(dependente);
		getJpa().getEm().flush();
	}	
}
