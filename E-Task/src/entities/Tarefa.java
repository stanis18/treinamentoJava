package entities;

import java.util.*;

public class Tarefa implements IEntities<Integer>{
	
	public enum STATUS{NOVA, EM_ANDAMENTO, FINALIZADA}
	
	
	
	private Integer numero;
	
	private String nome;
	private Date dataInicio;
	private Date dataFim;		
	private Pessoa responsavel;
	private Pessoa solicitante;
	private String descricao;
	private STATUS status;
	private Projeto projeto;
	
					
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public void setId(Integer numero) {
		this.numero = numero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public Pessoa getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Pessoa responsavel) {
		this.responsavel = responsavel;
	}
	public Pessoa getSolicitante() {
		return solicitante;
	}
	
	public void setSolicitante(Pessoa solicitante) {
		this.solicitante = solicitante;
	}
		
	public STATUS getStatus() {
		return status;
	}
	public void setStatus(STATUS status) {
		this.status = status;
	}
	
	@Override
	public String imprimir() {
		
		return getId()+" "+getNome()+" "+getStatus();
	}
	@Override
	public Integer getId() {
		
		return numero;
	}
	
	
}
