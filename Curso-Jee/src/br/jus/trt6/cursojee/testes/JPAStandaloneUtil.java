package br.jus.trt6.cursojee.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;

/**
 * Classe utilitária que permite a utilização e gerenciamento de transações e
 * conexções com a base de dados de forma StandAlone, complementamente independente.
 * <br/>
 * Consegue ler um arquivo "persistence.xml", configurar um EntityManagerFactory e disponibilizar
 * um EntityManager para uso desacoplado de qualquer contexto. 
 * <br/>
 * Não é uma classe estática, e manipula apenas uma PersistenceUnit por vez. Para situações onde
 * são necessárias utilizar mais de uma base de dados, deve-se intanciar vários objetos desta classe.
 */
public class JPAStandaloneUtil {
	
	/** Factory associada à  persistence unit */
	private EntityManagerFactory emf;
	
	/** EntityManager utilizado nas operações */
	private EntityManager em;

	/** Nome da persistence unit configurado no arquivo percistence.xml*/
	private final String persistenceUnitName; 
	
	/**
	 * @param persistenceUnitName Nome da Persistence unit
	 */
	public JPAStandaloneUtil(String persistenceUnitName) {
		this.persistenceUnitName = persistenceUnitName;
		configurar();
	}
	
	/**
	 * Inicializa uma EntityManagerFactory
	 */
	private void configurar() {
		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
	}

	/**
	 * Inicia um novo EntityManager
	 */
	public void iniciarSessao() {
		em = emf.createEntityManager();
	}
	
	/**
	 * Inicia um bloco transacional para operações na base de dados
	 */
	public void iniciarTransacao() {
		// iniciando uma transação
		em.getTransaction().begin();
	}
	
	/**
	 * Realiza commit e encerra a transação.
	 */
	public void concluirTransacao() {
		
		if (em.getTransaction().isActive()) {
			em.getTransaction().commit();
		}	
	}
	
	/**
	 * Realiza rollback e encerra a transação
	 */
	public void desfazerTransacao() {
		
		if (em.getTransaction().isActive()) {
			em.getTransaction().rollback();
		}	
	}

	public void encerrarSessao() {
		em.close();
	}
	
	public void limparCache() {
		em.clear();
	}
	
	/**
	 * Persiste um objeto mapeado na base de ddos
	 * @param entidade Objeto mapeado.
	 */
	public void salvar(Object entidade) {
		em.persist(entidade);
	}
	
	/**
	 * Exclui um objeto mapeado da base de dados
	 * @param entidade Objeto mapeado
	 */
	public void excluir(Object entidade) {
		em.remove(entidade);
	}
	
	/**
	 * Atualiza um objeto mapeado na base de dados
	 * @param entidade Objeto mapeado
	 */
	public void atualizar(Object entidade) {
		em.merge(entidade);
	}
	
	/**
	 * Consulta um objeto mapeado através da chave primária
	 * @param <TIPO> Tipo do objeto
	 * @param classEntidade Classe do objeto mapeado
	 * @param chave Chave primÃ¡ria para identificação do objeto
	 * @return Objeto com as informações recuperadas da base de dados
	 */
	public <TIPO> TIPO buscar(Class<TIPO> classEntidade, Object chave) {
		
		return em.find(classEntidade, chave);
	}
	
	/**
	 * Lista todos os objetos persistidos de um determinado tipo
	 * @param <TIPO> Tipo do objeto
	 * @param classeEntidade Classo do objeto mapeado
	 * @return todos os objetos persistidos
	 */
	@SuppressWarnings("unchecked")
	public <TIPO> List<TIPO> listar(Class<TIPO> classeEntidade) {
		return em.createQuery("from " + classeEntidade.getSimpleName()).getResultList();
	}
	
	/**
	 * Lista com paginação.
	 * @param <TIPO> Tipo do objeto
	 * @param classeEntidade Classo do objeto mapeado
	 * @param posInicio Posição do primeiro registro a ser retornado.
	 * @param numMaxRegistros Número máximo de registros.
	 * @return todos os objetos persistidos
	 */
	@SuppressWarnings("unchecked")
	public <TIPO> List<TIPO> listar(Class<TIPO> classeEntidade, int posInicio, int numMaxRegistros) {
		Query query = em.createQuery("from " + classeEntidade.getSimpleName());
		query.setFirstResult(posInicio);
		query.setMaxResults(numMaxRegistros);
		return query.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}
	
	protected Session getSession() {
		return  getEm() != null ? (Session) getEm().getDelegate() : null;
	}

	public String getPersistenceUnitName() {
		return persistenceUnitName;
	}
}
