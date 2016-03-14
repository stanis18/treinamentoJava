package br.jus.trt6.cursojee.testes;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.junit.Test;

import br.jus.trt6.cursojee.entidades.Cidade;
import br.jus.trt6.cursojee.entidades.Dependente;
import br.jus.trt6.cursojee.entidades.Pessoa;
import br.jus.trt6.cursojee.entidades.Projeto;
import br.jus.trt6.cursojee.entidades.Servidor;
import br.jus.trt6.cursojee.entidades.UF;

/**
 * Testa o exerc�cio da aula 4: Mapeamentos das propriedades de associa��o 
 * @author augusto
 */
public class Aula4Test extends TesteGenerico {

	@Test
	public void validarMapeamentoAssociacaoUF() {
		// consulta uma UF 
		UF uf = getJpa().listar(UF.class, 0, 1).get(0);
		
		// verifica se a lista de cidades est� corretamente configurada como lazy
		assertFalse("A associa��o UF.cidades deveria estar configurado como LAZY.", Hibernate.isInitialized(uf.getCidades()));
		
		// Realiza acesso � lista de cidades para validar o mapeamento
		assertNotNull("A UF deveria ter cidades associadas", uf.getCidades());
		assertFalse("A lista de cidades n�o deveria estar vazia", uf.getCidades().isEmpty());
		
	}
	
	@Test
	public void validarMapeamentoAssociacaoCidade() {
		// consulta uma Cidade
		Cidade cidade = getJpa().listar(Cidade.class, 0, 1).get(0);
		
		// verifica se a UF est� corretamente configurada como lazy
		assertFalse("A associa��o cidade.uf deveria estar configurado como LAZY.", Hibernate.isInitialized(cidade.getuf()));
		
		// Realiza acesso � UF para validar o mapeamento
		assertNotNull("A Cidade deveria ter uma UF associada", cidade.getuf());
		assertNotNull("A UF n�o deveria estar vazia", cidade.getuf().getSigla());				
	}
	
	@Test
	public void validarMapeamentoAssociacaoPessoa() {
		// consulta uma Pessoa
		Pessoa pessoa = getJpa().listar(Pessoa.class, 0, 1).get(0);
		
		// verifica se a Cidade est� corretamente configurada como lazy
		assertFalse("A associa��o pessoa.cidade deveria estar configurado como LAZY.", Hibernate.isInitialized(pessoa.getIdCidade()));
		
		// Realiza acesso � Cidade para validar o mapeamento
		assertNotNull("A Pessoa deveria ter uma Cidade associada", pessoa.getIdCidade());
		assertNotNull("A Cidade n�o deveria estar vazia", pessoa.getIdCidade().getNome());		
	}
	
	@Test
	public void validarMapeamentoEnumServidor() {
		// consulta os Servidores
		List<Servidor> servidores = getJpa().listar(Servidor.class);
		
		// varre os servidores e verifica se o mapeamento da Situacao est� correto
		for (Servidor servidor : servidores) {
			assertNotNull("A situa��o n�o foi corretamente carregada", servidor.getSituacao());
		}
	}
	
	@Test
	public void validarMapeamentoAssociacaoServidor() {
		
		// busca um servidor com dependentes e projetos
		Servidor servidor = buscarServidorComDependenteComProjeto();
		
		// verifica se a lista de dependentes est� corretamente configurada como lazy
		assertFalse("A associa��o servidor.dependentes deveria estar configurada como LAZY.", Hibernate.isInitialized(servidor.getDependentes()));
		
		// verifica se a lista de projetos est� corretamente configurada como lazy
		assertFalse("A associa��o servidor.projetos deveria estar configurada como LAZY.", Hibernate.isInitialized(servidor.getProjetos()));
		
		// Realiza acesso � lista de dependentes para validar o mapeamento
		assertNotNull("O servidor deveria ter dependentes associados", servidor.getDependentes());
		assertFalse("A lista de dependentes n�o deveria estar vazia", servidor.getDependentes().isEmpty());
		
		// Realiza acesso � lista de projetos para validar o mapeamento
		assertNotNull("O servidor deveria ter projetos associados", servidor.getProjetos());
		assertFalse("A lista de projetos n�o deveria estar vazia", servidor.getProjetos().isEmpty());		
	}

	@Test
	public void validarMapeamentoDependente() {
		// consulta um Dependente
		Dependente dependente = getJpa().listar(Dependente.class, 0, 1).get(0);
		
		// verifica se o Servidor est� corretamente configurado como lazy
		assertFalse("A associa��o dependente.servidor deveria estar configurado como LAZY.", Hibernate.isInitialized(dependente.getId_servidor()));
		
		// Realiza acesso ao Servidor para validar o mapeamento
		assertNotNull("O dependente deveria ter um Servidor associado", dependente.getId_servidor());
		assertNotNull("O Servidor n�o deveria estar vazio", dependente.getId_servidor().getMatricula());			
	}
	
	@Test
	public void validarMapeamentoProjetoServidor() {
		// busca um projeto com servidores
		String hql = "select p from Projeto p where p.servidores is not empty";
		Query query = getJpa().getEm().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Projeto projeto = (Projeto) query.getSingleResult();
		
		// verifica se a lista de servidores est� corretamente configurada como lazy
		assertFalse("A associa��o projeto.servidores deveria estar configurado como LAZY.", Hibernate.isInitialized(projeto.getServidores()));
		
		// Realiza acesso � lista de servidores para validar o mapeamento
		assertNotNull("o projeto deveria ter servidores associados", projeto.getServidores());
		assertFalse("A lista de servidores n�o deveria estar vazia", projeto.getServidores().isEmpty());
	}
	
	@Test
	public void validarExclusaoServidorDependenteProjetoCascata() {
		
		// busca um servidor com dependentes e projetos
		Servidor servidor = buscarServidorComDependenteComProjeto();

		// armazena o id de um de seus dependentes
		Integer idDependentes = servidor.getDependentes().get(0).getId();
		
		//exclui servidor
		// se a exclus�o funcionou, a rela��o com projeto foi removido com sucesso, caso contr�rio, ocorrei erro de banco
		getJpa().excluir(servidor); 
		getJpa().getEm().flush();
		
		// limpa sess�o e tenta recuperar um dos dependentes pelo ID
		getJpa().getEm().clear();
		Dependente dependente = getJpa().buscar(Dependente.class, idDependentes);
		assertNull("Dependente deveria ter sido exclu�do", dependente);
		
	}

	private Servidor buscarServidorComDependenteComProjeto() {
		String hql = "select s from Servidor s where s.dependentes is not empty  and s.projetos is not empty";
		return consultaServidor(hql);
	}

	private Servidor consultaServidor(String hql) {
		Query query = getJpa().getEm().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Servidor servidor = (Servidor) query.getSingleResult();
		return servidor;
	}
	
}
