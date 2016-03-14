package model;

import entities.*;

import java.util.*;

public class ProjetoModel extends GenericModel<Projeto, String> {
	
	
	public List<Pessoa> listarPessoas(String sigla){
		
		Projeto projeto =  (Projeto)repositorio.get(sigla);		
		
		return projeto.getParticipantes();
	}
		
	
	public void adicionarPessoas(String sigla, Pessoa pessoa){
		
		Projeto projeto =  (Projeto)repositorio.get(sigla);
		
		List<Pessoa> funcionarios = projeto.getParticipantes();
		
		funcionarios.add(pessoa);
		
	}
	
	
	public void removerPessoas(String sigla, Pessoa pessoa){

		Projeto projeto =  (Projeto)repositorio.get(sigla);

		List<Pessoa> funcionarios = projeto.getParticipantes();

		for (int i = 0; i< funcionarios.size(); i++){

			if(funcionarios.get(i).getId() == pessoa.getId()){

				funcionarios.remove(i);

			}			
		}

	}
	
	
	public List<Tarefa> listarTarefas(String sigla){

		Projeto projeto =  (Projeto)repositorio.get(sigla);
	
		return projeto.getTarefas();
	}
	
	public void adicionarTarefas(String sigla, Tarefa tarefa){
		
		Projeto projeto =  (Projeto)repositorio.get(sigla);
								
		List<Tarefa> tarefas = projeto.getTarefas();
										
		tarefas.add(tarefa);
		
	}
	
					
	public void removerTarefas(String sigla, Tarefa tarefa){
		
		Projeto projeto =  (Projeto)repositorio.get(sigla);
		
		List<Tarefa> tarefas = projeto.getTarefas();
		
		for(int i = 0; i < tarefas.size(); i++){
			
			if(tarefas.get(i).getId() == tarefa.getId()){
				
				tarefas.remove(i);
			}
		}
		
	}
				
}
