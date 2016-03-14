package br.jus.trt6.cursojee.testes;

import org.junit.Test;

import br.jus.trt6.cursojee.entidades.Cidade;
import br.jus.trt6.cursojee.entidades.Dependente;
import br.jus.trt6.cursojee.entidades.Pessoa;
import br.jus.trt6.cursojee.entidades.Projeto;
import br.jus.trt6.cursojee.entidades.Servidor;
import br.jus.trt6.cursojee.entidades.UF;

/**
 * Testa o exerc�cio da aula 3: Mapeamentos das propriedades b�sicas 
 * @author augusto
 */
public class Aula3Test extends TesteGenerico {

	@Test
	public void validarMapeamentoUF() {
		listar(UF.class);		
	}
	
	@Test
	public void validarMapeamentoCidade() {
		listar(Cidade.class);		
	}
	
	@Test
	public void validarMapeamentoPessoa() {
		listar(Pessoa.class);		
	}
	
	@Test
	public void validarMapeamentoServidor() {
		listar(Servidor.class);		
	}
	
	@Test
	public void validarMapeamentoDependente() {
		listar(Dependente.class);		
	}
	
	@Test
	public void validarMapeamentoProjeto() {
		listar(Projeto.class);		
	}
	
	private void listar(Class<?> tipo) {
		// se n�o ocorrer erro, o mapeamento das propriedades b�sicas n�o apresenta problemas
		getJpa().listar(tipo, 0, 1);
	}
}
