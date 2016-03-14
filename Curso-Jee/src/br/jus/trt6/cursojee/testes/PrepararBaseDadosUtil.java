package br.jus.trt6.cursojee.testes;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Classe que prepara a base de dados para realização dos testes de unidade.
 * <br/>
 * Realiza drop, create e carga
 * @author augusto
 *
 */
public class PrepararBaseDadosUtil {

	private static final String DERBY_TABLE_NOT_EXISTS_CODE = "42Y55";

	/** Script para criação de tabelas */
	public static final String SCRIPT_DDL_PATH = "dbScripts/script_ddl.sql";
	
	/** Script para drop de tabelas */
	public static final String SCRIPT_DROP_PATH = "dbScripts/script_drop.sql";	
	
	/** Script para limpar base de dados */
	public static final String SCRIPT_CLEAR_PATH = "dbScripts/script_clear.sql";

	/** Script para realizar carga de dados */
	public static final String SCRIPT_DML_PATH = "dbScripts/script_dml.sql";
	
	private static JPAStandaloneUtil jpa;
	
	/**
	 * Garante que sempre haverá uma base de dados limpa para testes
	 * @throws Exception 
	 */
	public static void prepararDados() throws Exception {
		
		jpa = new JPAStandaloneUtil(TesteGenerico.PU_CURSO_JEE_TESTES);
		jpa.iniciarSessao();
		jpa.iniciarTransacao();
		
		try {
			System.out.println("Removendo tabelas para testes, se necessário...");
			executarScriptDDL(SCRIPT_DROP_PATH);			
			System.out.println("Criando tabelas para testes, se necessário...");
			executarScriptDDL(SCRIPT_DDL_PATH);
			System.out.println("Executando script de carga de dados para testes...");
			executarUpdateScript(SCRIPT_DML_PATH);
			
			jpa.concluirTransacao();
		} finally {
			jpa.encerrarSessao();
		}
	}

	/**
	 * Executa scripts que criam ou deletam elementos do banco de dados (tables).
	 * @param scriptDdlPath
	 * @throws Exception
	 */
	private static void executarScriptDDL(String scriptDdlPath) throws Exception {
		String[] comandos = lerScript(scriptDdlPath);
		
		// acessando diretamente a conexão para poder executar um bulk delete, não permitido pelo hibernate
		@SuppressWarnings("deprecation")
		Connection connection = jpa.getSession().connection();
		connection.setAutoCommit(false);
		Statement st = connection.createStatement();
		for (String comando : comandos) {
	        try { 
	            st.executeUpdate(comando); 
	        } catch (SQLException e) { 
	        	// se for um comando de drop e a tabela não existe, o erro é aceitável
	        	// e o script porde prosseguir, afinal pode ser a primeira execução.
	        	if (!(comando.trim().toUpperCase().contains("DROP") && e.getSQLState().equals(DERBY_TABLE_NOT_EXISTS_CODE))) {
	        		throw e;
	        	}
	        } 
		}
			
		
	}

	/**
	 * Executa um script de banco de dados como um comando.
	 * 
	 * @param scriptClearPath
	 *            Caminho para o arquivo.
	 * @throws Exception 
	 */
	private static void executarUpdateScript(String scriptClearPath) throws Exception {
		String[] comandos = lerScript(scriptClearPath);
		
		// acessando diretamente a conexão para poder executar um bulk delete, não permitido pelo hibernate
		@SuppressWarnings("deprecation")
		Connection connection = jpa.getSession().connection();
		connection.setAutoCommit(false);
		Statement st = connection.createStatement();
		for (String comando : comandos) {				
			st.addBatch(comando);
		}
		st.executeBatch();
			
	}

	private static String[] lerScript(String scriptClearPath) throws Exception {
		Scanner sc = new Scanner(new FileReader(new File(scriptClearPath)));
		
		// junta todos os comandos do arquivo em uma String
		StringBuffer comandos = new StringBuffer();
		while (sc.hasNext()) {
			String comando = sc.nextLine();
			if (!comando.trim().isEmpty() && !comando.startsWith("--")) {
				comandos.append(comando);
			}	
		}
		
		// realiza split dos comandos utilizando ";" como separador
		return comandos.toString().split(";");
	}	
}
