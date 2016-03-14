package facade;
import java.util.*;
import exceptions.*;
import controller.*;
import entities.*;

public class FacadeTarefaTeste {
	
	
	private static TarefaController tarefaController   = new TarefaController();
	private static ProjetoController projetoController = new ProjetoController();
	

	public void cadastrar(Tarefa tarefa) throws ValidacaoException {
		
		tarefaController.cadastrar(tarefa);
														
	}

	public List<Tarefa> listar() {

		return tarefaController.listar();
	}

	public List<Tarefa> listarTarefas(String sigla) throws ValidacaoException {

		return projetoController.listarTarefas(sigla);
	}
		
	public List<Tarefa> listarTarefasResponsavel(Integer matricula) throws ValidacaoException {

		return tarefaController.listarTarefasResponsavel(matricula);
	}

	public List<Tarefa> listarTarefasSolicitante(Integer matricula) throws ValidacaoException {
				
		return tarefaController.listarTarefasSolicitante(matricula);
	}


	public Tarefa buscar(Integer chave) {
				
		return tarefaController.buscar(chave);
	}
				
	public void atualizar(Tarefa tarefa) throws ValidacaoException {
		
		tarefaController.atualizar(tarefa);
	}
	
	//casos de teste
	public void excluir(Integer numero) throws ValidacaoException {
		
		tarefaController.excluir(numero);
	}
	
		
}
