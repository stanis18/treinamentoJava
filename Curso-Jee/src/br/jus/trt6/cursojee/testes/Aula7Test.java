package br.jus.trt6.cursojee.testes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.junit.Test;

import br.jus.trt6.cursojee.dao.*;
import br.jus.trt6.cursojee.entidades.*;
import br.jus.trt6.cursojee.entidades.Servidor.Situacao;

public class Aula7Test extends TesteGenerico {

//	/**
//	 * Testa método de consulta sem informar parâmetros
//	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testConsultarHQLSimples() {
		String hql = "from UF";
		
		// consultando manualmente
		Query query = getJpa().getEm().createQuery(hql);
		List<UF> esperado = query.getResultList();
		
		// consultando via DAO
		IDAO<UF> dao = getDAO(UF.class);
		List<UF> recebido = dao.consultar(hql);
		
		validarResultadosIguais(esperado, recebido);
	}
	
	/**
	 * Testa método de consulta sem informar parâmetros
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testConsultarHQLParametros() {
		String hql = "from Cidade c where c.uf.sigla = ? and c.nome like ?";
		
		// consultando manualmente
		Query query = getJpa().getEm().createQuery(hql);
		query.setParameter(1, "PE");
		query.setParameter(2, "%Rec%");
		List<UF> esperado = query.getResultList();
		
		// consultando via DAO
		IDAO<UF> dao = getDAO(UF.class);
		List<UF> recebido = dao.consultar(hql, "PE", "%Rec%");
		
		validarResultadosIguais(esperado, recebido);
	}
	
	@Test
	public void testCriacaoDAO() {
		
		IDAO<?> dao = getDAO(Cidade.class);
		assertTrue("DAO para cidade deveria estar configurado.", dao instanceof CidadeDAO);
		
		dao = getDAO(Servidor.class);
		assertTrue("DAO para servidor deveria estar configurado.", dao instanceof ServidorDAO);
		
		dao = getDAO(UF.class);
		assertTrue("DAO para uf deveria estar configurado.", dao instanceof GenericDAO);
		
		dao = getDAO(Dependente.class);
		assertTrue("DAO para dependente deveria estar configurado.", dao instanceof GenericDAO);
		
		dao = getDAO(Projeto.class);
		assertTrue("DAO para projeto deveria estar configurado.", dao instanceof GenericDAO);		
		
	}
	
	/**
	 * Testa método de consulta de cidades por UF
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testConsultarCidades() {
		
		// buscando uma UF
		Query queryUF = getJpa().getEm().createQuery("from UF");
		queryUF.setMaxResults(1);
		UF uf = (UF) queryUF.getSingleResult();
		
		// consultando manualmente
		String hql = "from Cidade c where c.uf.id = ?";
		Query query = getJpa().getEm().createQuery(hql);
		query.setParameter(1, uf.getId());
		List<Cidade> esperado = query.getResultList();
		
		// consultando via DAO
		CidadeDAO dao = (CidadeDAO) getDAO(Cidade.class);
		List<Cidade> recebido = dao.consultar(uf);
		
		validarResultadosIguais(esperado, recebido);
	}
	
	/**
	 * Testa consulta de servidor sem parametros de filtro
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testConsultaServidorSemFiltro() {
		// consultando manualmente
		String hql = "from Servidor";
		Query query = getJpa().getEm().createQuery(hql);
		List<Servidor> esperado = query.getResultList();
		
		// consulta via dao
		ServidorDAO dao = (ServidorDAO) getDAO(Servidor.class);
		List<Servidor> recebido = dao.consultar( null, null, false); 
		
		validarResultadosIguais(esperado, recebido);
	}
	
	/**
	 * Testa consulta de servidor com parametros de filtro
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testConsultaServidorFiltroServidor() throws ParseException {
		ServidorDAO dao = (ServidorDAO) getDAO(Servidor.class);

		// criando base de dados, difeciando os servidores em apenas um atributo, a fim de serem todos diferentes,
		// mas tendo o primeiro como base. Assim, posso testar todos os atributos, mas o resultado esperado deverá
		// ser o servidor1
		Servidor servidor1 = criarServidor("99999999999", "01/01/1984", "serv1@serv.com", "11111", "Servidor 1", Situacao.A);
		dao.inserir(servidor1);
		Servidor servidor2 = criarServidor("99999999999", "01/01/1950", "serv1@serv.com", "11111", "Servidor 1", Situacao.A);
		dao.inserir(servidor2);
		Servidor servidor3 = criarServidor("99999999999", "01/01/1984", "serv3@serv.com", "11111", "Servidor 1", Situacao.A);
		dao.inserir(servidor3);
		Servidor servidor4 = criarServidor("99999999999", "01/01/1984", "serv1@serv.com", "44444", "Servidor 1", Situacao.A);
		dao.inserir(servidor4);
		Servidor servidor5 = criarServidor("99999999999", "01/01/1984", "serv1@serv.com", "11111", "Servidor 5", Situacao.A);
		dao.inserir(servidor5);
		Servidor servidor6 = criarServidor("99999999999", "01/01/1984", "serv1@serv.com", "11111", "Servidor 1", Situacao.E);
		dao.inserir(servidor6);
				
		// consultando manualmente
		String hql = "from Servidor s where s.cpf = ? and s.dataNascimento >= ? and s.email like ? and " +
				"s.matricula = ? and s.nome like ? and s.situacao = ?";
		Query query = getJpa().getEm().createQuery(hql);
		query.setParameter(1, servidor1.getCpf());
		query.setParameter(2, servidor1.getDataNascimento());
		query.setParameter(3, servidor1.getEmail());
		query.setParameter(4, servidor1.getMatricula());
		query.setParameter(5, servidor1.getNome());
		query.setParameter(6, servidor1.getSituacao());
		
		List<Servidor> esperado = query.getResultList(); // apenas o servidor 1
		
		// consulta via dao
		List<Servidor> recebido = dao.consultar(servidor1, null, false);
		
		validarResultadosIguais(esperado, recebido);
	}
	
	/**
	 * Testa consulta de servidor com parametros de filtro e dependentes
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testConsultaServidorFiltroServidorDependentes() throws ParseException {
		ServidorDAO dao = (ServidorDAO) getDAO(Servidor.class);

		// criando base de dados
		Servidor servidor1 = criarServidor("99999999999", "01/01/1984", "serv1@serv.com", "11111", "Servidor 1", Situacao.A);
		dao.inserir(servidor1);
		Servidor servidor2 = criarServidor("88888888888", "01/01/1950", "serv1@serv.com", "11111", "Servidor 1", Situacao.A);
		dao.inserir(servidor2);
		
		// adiciona um dependente ao servidor
		Dependente dependente = new Dependente();
		dependente.setCpf("77777777777");
		dependente.setDataNascimento(new Date());
		dependente.setEmail("dep@dep.com");
		dependente.setNome("dependente serv 1");
		dependente.setDataInicio(new Date());
		dependente.setId_servidor(servidor1);
				
		// consultando manualmente, fixando cpf para garantir que apenas os servidores criados estão considerados
		Servidor filtro = new Servidor();
		filtro.setCpf(servidor1.getCpf());
		
		String hql = "from Servidor s where s.cpf = ? and s.dependentes is not empty";
		Query query = getJpa().getEm().createQuery(hql);
		query.setParameter(1, filtro.getCpf());
		List<Servidor> esperado = query.getResultList(); // apenas o servidor 1
		
		// consulta via dao
		List<Servidor> recebido = dao.consultar(filtro, null, true);
		
		validarResultadosIguais(esperado, recebido);
	}
	
	/**
	 * Testa consulta de servidor com parametros de filtro e projetos
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testConsultaServidorFiltroServidorProjetos() throws ParseException {
		ServidorDAO dao = (ServidorDAO) getDAO(Servidor.class);

		// criando base de dados 
		Servidor servidor1 = criarServidor("99999999999", "01/01/1984", "serv1@serv.com", "11111", "Servidor 1", Situacao.A);
		dao.inserir(servidor1);
		Servidor servidor2 = criarServidor("99999999999", "01/01/1950", "serv1@serv.com", "22222", "Servidor 2", Situacao.A);
		dao.inserir(servidor2);
		Servidor servidor3 = criarServidor("99999999999", "01/01/1950", "serv1@serv.com", "33333", "Servidor 3", Situacao.A);
		dao.inserir(servidor3);		
		
		// criando projetos
		IDAO<Projeto> daoProj = getDAO(Projeto.class);
		Projeto projeto1 = new Projeto("Projeto1", "PJ1", true);
		daoProj.inserir(projeto1);
		Projeto projeto2 = new Projeto("Projeto2", "PJ2", true);
		daoProj.inserir(projeto2);

		// associando aos servidores. 
		// 2 projetos para o servidor1, 1 para o servidor2, 0 para o servidor3
		IDAO<ProjetoServidor> daoProjServ = getDAO(ProjetoServidor.class);
		daoProjServ.inserir(new ProjetoServidor(projeto1, servidor1)); 
		daoProjServ.inserir(new ProjetoServidor(projeto2, servidor1));
		daoProjServ.inserir(new ProjetoServidor(projeto2, servidor2));
		
		// consultando manualmente, fixando cpf para garantir que apenas os servidores criados estão considerados
		// buscando apenas servidores que trabalham em pelo menos 2 projetos
		Servidor filtro = new Servidor();
		filtro.setCpf(servidor1.getCpf());
		
		String hql = "from Servidor s where s.cpf = ? and size(s.projetos) >= ? ";
		Query query = getJpa().getEm().createQuery(hql);
		query.setParameter(1, filtro.getCpf());
		query.setParameter(2, 2);
		List<Servidor> esperado = query.getResultList(); // apenas o servidor 1
		
		// consulta via dao
		List<Servidor> recebido = dao.consultar(filtro, 2, false);
		
		validarResultadosIguais(esperado, recebido);
	}

	private Servidor criarServidor(String cpf, String data, String email, String matricula, 
			String nome, Situacao a ) throws ParseException {
		Servidor servidor = new Servidor();
		servidor.setCpf(cpf);
		servidor.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(data));
		servidor.setEmail(email);
		servidor.setMatricula(matricula);
		servidor.setNome(nome);
		servidor.setSituacao(a);
		return servidor;
	}
	
	private <TIPO> IDAO<TIPO> getDAO(Class<TIPO> clazz) {
		return DAOFactory.createDAO(clazz, getJpa().getEm());
	}
	
	private void validarResultadosIguais(List<?> resultadoEsperado, List<?> resultadoRecebido) {
		assertTrue("O resultado Esperado não contem todos os objetos do resultado Recebido", resultadoEsperado.containsAll(resultadoRecebido));
		assertTrue("O resultado Recebido não contem todos os objetos do resultado Esperado", resultadoRecebido.containsAll(resultadoEsperado));
	}
	
}
