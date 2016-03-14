package model;
import java.util.*;
import entities.*;

public class TarefaModel extends GenericModel<Tarefa, Integer>{

	private static Integer count = 0;
	
	public void cadastrar(Tarefa tarefa){
		count++;
		tarefa.setId(count);
		super.cadastrar(tarefa);
	}	
	
	public List<Tarefa> listarTarefasResponsavel(Integer id){
		
		List<Tarefa> tarefas = listar();
		
		ArrayList<Tarefa> aux = new ArrayList<Tarefa>();
				
		for(Tarefa tarefa: tarefas){
			
			if( (tarefa.getResponsavel().getId() == id) ){
								
				aux.add(tarefa);
			}			
		}		
		return aux;
	}
	
	
	public List<Tarefa> listarTarefasSolicitante(Integer id){
		
		List<Tarefa> tarefas = listar();
		
		ArrayList<Tarefa> aux = new ArrayList<Tarefa>();
		
		for(Tarefa tarefa: tarefas){
			
			if( (tarefa.getSolicitante().getId() == id) ){
				
				aux.add(tarefa);
			}			
		}		
		return aux;
	}
	
	

}
