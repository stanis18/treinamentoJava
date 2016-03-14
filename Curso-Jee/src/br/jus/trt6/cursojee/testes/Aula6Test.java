package br.jus.trt6.cursojee.testes;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;

import br.jus.trt6.cursojee.entidades.*;
import br.jus.trt6.cursojee.entidades.Servidor.Situacao;
import br.jus.trt6.cursojee.dao.*;

public class Aula6Test extends TesteGenerico {

	@Test
	public void testInstanciarDAOs() {
		testInstanciarDAOs(getJpa().getEm(), UF.class, Cidade.class, Pessoa.class, Servidor.class, Dependente.class, Projeto.class);
	}

	@Test
	public void testBuscarEntidades() {
		testBuscarEntidades(getJpa().getEm(), UF.class, Cidade.class, Pessoa.class, Servidor.class, Dependente.class, Projeto.class);
	}
	
	@Test
	public void testRecarregarUF() {
	
		UF uf = buscarEntidade(UF.class);
		String sigla = uf.getSigla();
		uf.setSigla("XP");
		
		IDAO<UF> dao = getDAO(UF.class);
		dao.recarregar(uf);
		assertEquals("Deveria ter recarreado os dados de UF", sigla, uf.getSigla());
		
	}
	
	@Test
	public void testRecarregarCidade() {
	
		Cidade cidade = buscarEntidade(Cidade.class);
		String nome = cidade.getNome();
		cidade.setNome("XP");
		
		IDAO<Cidade> dao = getDAO(Cidade.class);
		dao.recarregar(cidade);
		assertEquals("Deveria ter recarreado os dados de Cidade", nome, cidade.getNome());
		
	}
	
	@Test
	public void testInserirEntidades() {
		
		UF uf = new UF();
		uf.setSigla("XP");
		inserirTestar(uf);
		
		Cidade cidade = new Cidade();
		cidade.setNome("Cidade");
		cidade.setuf(uf);
		inserirTestar(cidade);
		
		Servidor servidor = new Servidor();
		servidor.setIdCidade(cidade);
		servidor.setCpf("99999999999");
		servidor.setDataNascimento(new Date());
		servidor.setEmail("mail@mail.com");
		servidor.setMatricula("32154");
		servidor.setNome("Nome serv");
		servidor.setSituacao(Situacao.A);
		inserirTestar(servidor);
		
		Dependente dependente = new Dependente();
		dependente.setIdCidade(cidade);
		dependente.setCpf("88888888888");
		dependente.setDataNascimento(new Date());
		dependente.setEmail("mail@mail.com");
		dependente.setNome("Nome serv");
		dependente.setDataInicio(new Date());
		dependente.setId_servidor(servidor);
		inserirTestar(dependente);
		
		Projeto projeto = new Projeto();
		projeto.setNome("Projeto");
		projeto.setSigla("PROJ");
		inserirTestar(projeto);
		
	}
	
	@Test
	public void testAtualizarEntidades() {
		
		// atualiza as entidades
		UF uf = buscarEntidade(UF.class);
		uf.setSigla("XP");
		IDAO<UF> ufdao = getDAO(UF.class);
		ufdao.atualizar(uf);
		
		Cidade cidade = buscarEntidade(Cidade.class);
		cidade.setNome("XP TO");
		IDAO<Cidade> cidadeDao = getDAO(Cidade.class);
		cidadeDao.atualizar(cidade);
		
		Servidor servidor = buscarEntidade(Servidor.class);
		servidor.setNome("XP TO");
		IDAO<Servidor> servidorDao = getDAO(Servidor.class);
		servidorDao.atualizar(servidor);
		
		Dependente dependente = buscarEntidade(Dependente.class);
		dependente.setNome("XP TO");
		IDAO<Dependente> dependenteDao = getDAO(Dependente.class);
		dependenteDao.atualizar(dependente);
		
		Projeto projeto = buscarEntidade(Projeto.class);
		projeto.setSigla("XP TO");
		IDAO<Projeto> projetoDao = getDAO(Projeto.class);
		projetoDao.atualizar(projeto);
		
		// limpa cache do entitymanager
		getJpa().getEm().clear();
		
		// consulta e checa valor alterado
		assertEquals("O valor deveria ter sido atualizado.", "XP", ufdao.buscar(uf.getId()).getSigla());
		assertEquals("O valor deveria ter sido atualizado.", "XP TO", cidadeDao.buscar(cidade.getId()).getNome());
		assertEquals("O valor deveria ter sido atualizado.", "XP TO", servidorDao.buscar(servidor.getId()).getNome());
		assertEquals("O valor deveria ter sido atualizado.", "XP TO", dependenteDao.buscar(dependente.getId()).getNome());
		assertEquals("O valor deveria ter sido atualizado.", "XP TO", projetoDao.buscar(projeto.getId()).getSigla());
		
	}

	private <TIPO> IDAO<TIPO> getDAO(Class<TIPO> clazz) {
		return DAOFactory.createDAO(clazz, getJpa().getEm());
	}
	
	@Test
	public void testExcluirEntidades() {
		
		// cria base de dados
		UF uf = new UF();
		uf.setSigla("XP");
		inserir(uf);
		
		Cidade cidade = new Cidade();
		cidade.setNome("Cidade");
		cidade.setuf(uf);
		inserir(cidade);
		
		Servidor servidor = new Servidor();
		servidor.setIdCidade(cidade);
		servidor.setCpf("99999999999");
		servidor.setDataNascimento(new Date());
		servidor.setEmail("mail@mail.com");
		servidor.setMatricula("32154");
		servidor.setNome("Nome serv");
		servidor.setSituacao(Situacao.A);
		inserir(servidor);
		
		Dependente dependente = new Dependente();
		dependente.setIdCidade(cidade);
		dependente.setCpf("88888888888");
		dependente.setDataNascimento(new Date());
		dependente.setEmail("mail@mail.com");
		dependente.setNome("Nome serv");
		dependente.setDataInicio(new Date());
		dependente.setId_servidor(servidor);
		inserir(dependente);
		
		Projeto projeto = new Projeto();
		projeto.setNome("Projeto");
		projeto.setSigla("PROJ");
		inserir(projeto);
		
		// limpara entity manager
		getJpa().getEm().clear();
		
		// excluir entidades  
		excluir(projeto, dependente, servidor, cidade, uf);
		
		// limpara entity manager
		getJpa().getEm().clear();
		
		// valida exclusao
		assertNull("O valor deveria ter sido atualizado.", getDAO(UF.class).buscar(uf.getId()));
		assertNull("O valor deveria ter sido atualizado.", getDAO(Cidade.class).buscar(cidade.getId()));
		assertNull("O valor deveria ter sido atualizado.", getDAO(Servidor.class).buscar(servidor.getId()));
		assertNull("O valor deveria ter sido atualizado.", getDAO(Dependente.class).buscar(dependente.getId()));
		assertNull("O valor deveria ter sido atualizado.", getDAO(Projeto.class).buscar(projeto.getId()));
		
	}

	private void inserirTestar(Object entidade) {
		assertFalse("A entidade não deveria estar persistente antes da inserção", getJpa().getEm().contains(entidade));
		inserir(entidade); //era inserir
		assertTrue("A entidade deveria estar persistente após a inserção", getJpa().getEm().contains(entidade));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void inserir(Object entidade) {
		IDAO dao = DAOFactory.createDAO(entidade.getClass(), getJpa().getEm());
		dao.inserir(entidade);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void excluir(Object...entidades) {
		for (Object object : entidades) {
			IDAO dao = DAOFactory.createDAO(object.getClass(), getJpa().getEm());
			dao.excluir(object);
		}
	}

	private void testInstanciarDAOs(EntityManager entityManager, Class<?>...clazz) {
		if (clazz != null) {
			for (Class<?> c : clazz) {
				IDAO<?> dao = DAOFactory.createDAO(c, entityManager);
				assertNotNull("Deveria ter criado o DAO para " + c.getSimpleName(), dao);
			}
		}
	}
	

	private void testBuscarEntidades(EntityManager entityManager, Class<?>...clazz) {
		if (clazz != null) {
			for (Class<?> c : clazz) {
				Object entidade = buscarEntidade(c);
				assertNotNull("Deveria ter encontrado a entidade " + c.getSimpleName(), entidade);
			}
		}	
	}

	private <TIPO> TIPO buscarEntidade(Class<TIPO> c) {
		IDAO<TIPO> dao = DAOFactory.createDAO(c, getJpa().getEm());
		return dao.buscar(recuperarIdEntidade(c));
	}
	
	private Object recuperarIdEntidade(Class<?> c) {
		// consulta um ID de um entidade
		Query query = getJpa().getEm().createQuery("select e.id from " + c.getSimpleName() + " e");
		query.setFirstResult(0).setMaxResults(1);
		return query.getSingleResult();
	}


	
}
