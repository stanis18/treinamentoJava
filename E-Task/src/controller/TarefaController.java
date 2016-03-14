package controller;
import java.util.*;

import entities.*;
import exceptions.ValidacaoException;
import model.*;


public class TarefaController {
	
	private static TarefaModel tarefaModel = new TarefaModel();
	private static ProjetoController projetoController = new ProjetoController();

	public void cadastrar(Tarefa tarefa) throws ValidacaoException{

		if( tarefa.getNome() == null ){			
			throw new ValidacaoException();
		}

		projetoController.adicionarTarefa((tarefa.getProjeto()).getId(), tarefa);

		tarefaModel.cadastrar(tarefa);
	}

	public List<Tarefa> listar(){

		return tarefaModel.listar();
	}

	public List<Tarefa> listarTarefasResponsavel(Integer id){

		return tarefaModel.listarTarefasResponsavel(id);
	}

	public List<Tarefa> listarTarefasSolicitante(Integer id) throws ValidacaoException {

		return tarefaModel.listarTarefasSolicitante(id);

	}

	public Tarefa buscar(Integer chave) {

		return (Tarefa)tarefaModel.consultar(chave);
	}

	public void atualizar(Tarefa tarefa){		

		tarefaModel.atualizar(tarefa);

	}
	
		
	public void excluir(Integer id){
				
		tarefaModel.excluir(id);
		
	}

}
