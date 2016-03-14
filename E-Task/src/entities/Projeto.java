package entities;
import java.util.*;

public class Projeto implements IEntities <String>{

	private String nome;
	private String sigla;
	private List<Pessoa> funcionarios;
	private List<Tarefa> tarefas;
	
				
	public Projeto(String nome, String sigla) {
				
		this.nome = nome;
		this.sigla = sigla;
		
		funcionarios = new ArrayList <Pessoa>();
		tarefas = new ArrayList <Tarefa>();
		
	}

	@Override
	public String imprimir() {
						
		return getNome() + " " + getId();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public void setId(String sigla) {
		this.sigla = sigla;
	}

	public List<Pessoa> getParticipantes() {
		return funcionarios;
	}

	public void setParticipantes(List<Pessoa> funcionarios) {
		
		this.funcionarios = funcionarios;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	@Override
	public String getId() {
		
		return sigla;
	}		
}
