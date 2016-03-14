package br.jus.trt6.cursojee.testes;

import java.io.Serializable;

/**
 * Classe de uso geral para representar um objeto com nome e valor 
 */
@SuppressWarnings("serial")
public class Parametro implements Serializable {

	private String nome;
	private Object valor;
	
	/**
	 * Construtor.
	 * @param nome Nome do parâmetro.
	 * @param valor Valor do parâmetro. De qualquer tipo
	 */
	public Parametro(String nome, Object valor) {
		super();
		this.nome = nome;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Object getValor() {
		return valor;
	}
	public void setValor(Object valor) {
		this.valor = valor;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Parametro) {
			return this.nome.equals(((Parametro) obj).nome);
		}
		return false;
	}	
}
