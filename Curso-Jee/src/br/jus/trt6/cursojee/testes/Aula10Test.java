package br.jus.trt6.cursojee.testes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;
import org.junit.Test;

import br.jus.trt6.cursojee.entidades.Cidade;
import br.jus.trt6.cursojee.entidades.Dependente;
import br.jus.trt6.cursojee.entidades.Projeto;
import br.jus.trt6.cursojee.entidades.Servidor;
import br.jus.trt6.cursojee.entidades.UF;

/**
 * Teste de validaï¿½ï¿½es de domï¿½nio 
 * @author augusto
 */
public class Aula10Test extends TesteGenerico {

	private static final String CPF = "CPF inválido";;
	private static final String TOKENS = "Número mínimo de tokens";
	private static final String EMAIL = "não é um email válido";
	private static final String LENGTH = "tamanho deve estar entre";
	private static final String NOT_NULL = "não pode ser nulo";

	@Test
	public void testValidacaoUF() {
		
		// criando UF sem sigla
		UF uf = new UF();
		validar(uf, new Parametro("sigla", NOT_NULL)); 

		// criando UF com sigla invï¿½lida
		uf = new UF();
		uf.setSigla("Z");
		validar(uf, new Parametro("sigla", LENGTH));
		
		uf = new UF();
		uf.setSigla("ZZZ");
		validar(uf, new Parametro("sigla", LENGTH));
	}
	
	@Test
	public void testValidacaoCidade() {
		
		// criando cidade sem dados
		Cidade cidade = new Cidade();
		validar(cidade, 
				new Parametro("nome", NOT_NULL),
				new Parametro("uf", NOT_NULL));
		
		// validando outros valores
		cidade = new Cidade();
		cidade.setNome("1234");
		validar(cidade, 
				new Parametro("nome", LENGTH),
				new Parametro("uf", NOT_NULL));
		
		cidade = new Cidade();
		cidade.setNome(criarString("t", 51));
		validar(cidade, 
				new Parametro("nome", LENGTH),
				new Parametro("uf", NOT_NULL));		
		
	}
	
	@Test
	public void testValidacaoServidorPessoa() {
		
		// test NOT_NULL
		Servidor servidor = new Servidor();
		validar(servidor, 
				new Parametro("nome", NOT_NULL),
				new Parametro("matricula", NOT_NULL),
				new Parametro("situacao", NOT_NULL));
		
		// test outras validaï¿½ï¿½es
		servidor = new Servidor();
		servidor.setNome("1 34");
		servidor.setCpf("11111111110"); // invï¿½lido
		servidor.setMatricula(criarString("m", 11));
		servidor.setEmail("não válido");
		
		validar(servidor, 
				new Parametro("nome", LENGTH),
				new Parametro("cpf", CPF),
				new Parametro("email", EMAIL),
				new Parametro("matricula", LENGTH),
				new Parametro("situacao", NOT_NULL));	
		
		// test outras validaï¿½ï¿½es
		servidor = new Servidor();
		servidor.setNome("sssss");
		servidor.setCpf("11111111111"); // vï¿½lido
		servidor.setEmail("não válido");
		
		validar(servidor, 
				new Parametro("nome", TOKENS),
				new Parametro("email", EMAIL),
				new Parametro("matricula", NOT_NULL),
				new Parametro("situacao", NOT_NULL));	
		
		// test outras validaï¿½ï¿½es
		servidor = new Servidor();
		servidor.setNome(criarString("ss ss", 20) + "s"); //101 caracteres
		servidor.setCpf("11111111111");
		servidor.setEmail("não válido");
		
		validar(servidor, 
				new Parametro("nome", LENGTH),
				new Parametro("email", EMAIL),
				new Parametro("matricula", NOT_NULL),
				new Parametro("situacao", NOT_NULL));			
		
	}
	
	
	@Test
	public void testValidacaoDependente() {
		
		// criando servidor sem dados
		Dependente dependente = new Dependente();
		validar(dependente, 
				new Parametro("nome", NOT_NULL),
				new Parametro("servidor", NOT_NULL));
		
	}
	
	@Test
	public void testValidacaoProjeto() {
		
		// criando servidor sem dados
		Projeto projeto = new Projeto();
		validar(projeto, 
				new Parametro("nome", NOT_NULL),
				new Parametro("sigla", NOT_NULL));
		
		// test outras validaï¿½ï¿½es
		projeto = new Projeto();
		projeto.setNome("1234");
		projeto.setSigla("12");
		
		validar(projeto, 
				new Parametro("nome", LENGTH),
				new Parametro("sigla", LENGTH));
		
		// test outras validaï¿½ï¿½es
		projeto = new Projeto();
		projeto.setNome(criarString("p", 101));
		projeto.setSigla(criarString("p", 11));
		
		validar(projeto, 
				new Parametro("nome", LENGTH),
				new Parametro("sigla", LENGTH));		
	}
	
	/**
	 * Valida a entidade de acordo com as configuraï¿½ï¿½es de domï¿½nio.
	 * @param entidade Entidade a ser validada.
	 * @param errosEsperados Lista de erros esperados, onde a primeira posiï¿½ï¿½o representa a propriedade, e a segunda
	 * contï¿½m a mensagem de validaï¿½ï¿½o.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void validar(Object entidade, Parametro ... errosEsperados) {
		ClassValidator validador = new ClassValidator(entidade.getClass());
		InvalidValue[] erros = validador.getInvalidValues(entidade);
		imprimirErros(erros);
		
		// transforma os arrays em listas
		List<InvalidValue> errosList = asList(erros);
		List<Parametro> esperadosList = asList(errosEsperados);

		// compara as duas listas
		for (Iterator<Parametro> itParam = esperadosList.iterator(); itParam.hasNext();) {
			Parametro parametro = itParam.next();
			
			for (Iterator<InvalidValue> itErros = errosList.iterator(); itErros.hasNext();) {
				InvalidValue invalidValue = itErros.next();
				
				// se os erros coincidem, remove-os
				if (invalidValue.getPropertyPath().equals(parametro.getNome())
						&& invalidValue.getMessage().startsWith((String) parametro.getValor())) {
					itErros.remove();
					itParam.remove();
				}
			}
		}
		
		// se sobrar algum erro esperado ou encontrado, houve falha!
		for (Parametro parametro : esperadosList) {
			fail("Era esperado uma validação para o atributo " + parametro.getNome() + ": " + parametro.getValor() + "...");
		}
		for (InvalidValue erro : errosList) {
			fail("Não era esperado uma validação para o atributo " + erro.getPropertyPath() + ": " + erro.getMessage());
		}		
		
	}
	
	private <TIPO> List<TIPO> asList(TIPO[] erros) {
		List<TIPO> list = new ArrayList<TIPO>();
		
		if (erros != null) {
			for (int i = 0; i < erros.length; i++) {
				TIPO tipo = erros[i];
				list.add(tipo);
			}
		}
		
		return list;
	}

	private void imprimirErros(InvalidValue ... erros) {
		System.out.println("### RELATORIO DE ERROS ###");
		if (erros == null || erros.length == 0) {
			System.out.println("Entidade Válida");
		} else {
			for (InvalidValue value : erros) {
				System.out.println("========");
				System.out.println("propertyName=" + value.getPropertyName());
				System.out.println("message=" + value.getMessage());
				System.out.println("propertyPath=" + value.getPropertyPath());
				System.out.println("value=" + value.getValue());
			}
		}	
	}
	
	private String criarString(String token, int quantidade) {
		StringBuilder sb = new StringBuilder();
		
		for (int j = 0; j < quantidade; j++) {
			sb.append(token);
		}
		
		return sb.toString();
	}

}
