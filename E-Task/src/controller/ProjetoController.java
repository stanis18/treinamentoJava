package controller;

import exceptions.*;
import entities.*;
import model.*;

import java.util.*;

public class ProjetoController {
	
	private static ProjetoModel projetoModel =  new ProjetoModel();
	private static PessoaController pessoaController = new PessoaController();
	
	
	public void cadastrar(Projeto projeto)throws ValidacaoException{
		
		validarCadastrar(projeto);
		projetoModel.cadastrar(projeto);
		
	}
	
	private void validarCadastrar(Projeto projeto) throws ValidacaoException  {
		
		if(projeto.getId() == null || projeto.getNome() == null || 
				verificarExistencia(projeto.getId())){
			
			throw new ValidacaoException();
		}
		
	}

	private boolean verificarExistencia(String sigla) {
		
		return buscar(sigla) != null;
	}			
	
	public void atualizar(Projeto projeto) throws ValidacaoException{
		
		validarAtualizar(projeto);
		projetoModel.atualizar(projeto);
		
		
	}
	
	private void validarAtualizar(Projeto projeto) throws ValidacaoException{
		
		if(projeto.getId() == null || projeto.getNome() == null || !verificarExistencia(projeto.getId())){
			
			throw new ValidacaoException();
		}		
	}	
	
	public Projeto buscar(String sigla){

		return projetoModel.consultar(sigla);
	}
	
	public void remover(String sigla) throws ValidacaoException{
		
		confirmarExistencia(sigla);
		projetoModel.excluir(sigla);	

	}

	public List<Projeto> listar(){

		return projetoModel.listar();
	}
					
	public void confirmarExistencia(String sigla) throws ValidacaoException {

		if( !verificarExistencia(sigla) ){

			throw new ValidacaoException();
		}
	}
		
	public List<Pessoa> listarParticipantes(String sigla) throws ValidacaoException{

		confirmarExistencia(sigla);

		return projetoModel.listarPessoas(sigla);

	}


	public void adicionarParticipante(String sigla, Pessoa pessoa) throws ValidacaoException{

		confirmarExistencia(sigla);

		if(pessoaController.consultar(pessoa.getId()) == null || isParticipanteProjeto(sigla, pessoa)){

			throw new ValidacaoException();
		}

		projetoModel.adicionarPessoas(sigla, pessoa);
	}


	public void removerParticipante(String sigla, Pessoa pessoa) throws ValidacaoException{

		confirmarExistencia(sigla);

		//isParticipanteProjeto(sigla, pessoa);

		if(pessoaController.consultar(pessoa.getId()) == null || !isParticipanteProjeto(sigla, pessoa) ){

			throw new ValidacaoException();
		}

		projetoModel.removerPessoas(sigla, pessoa);
	}
	
	public List<Tarefa> listarTarefas (String sigla) throws ValidacaoException{
		
		confirmarExistencia(sigla);
						
		return projetoModel.listarTarefas(sigla);
	}
	
	public void adicionarTarefa(String sigla, Tarefa tarefa) throws ValidacaoException{
		
		confirmarExistencia(sigla);
			
		if( isTarefaProjeto(sigla, tarefa) || !confirmarResponsavel(tarefa) || !confirmarSolicitante(tarefa)){
			
			throw new ValidacaoException();
		}
						
						
		projetoModel.adicionarTarefas(sigla, tarefa);
	}
	
		
	private boolean confirmarResponsavel(Tarefa tarefa) throws ValidacaoException{

		boolean confir = false;

		List<Pessoa> participante = listarParticipantes(  (tarefa.getProjeto()).getId()  );

		for (int i=0; i < participante.size(); i++ ){

			if(  ( tarefa.getResponsavel() ).getNome().equals( (participante.get(i) ).getNome()   )      ){

				confir = true;
			}

		}

		return confir;
	}
	
	
	private boolean confirmarSolicitante(Tarefa tarefa) throws ValidacaoException{

		boolean confir = false;

		List<Pessoa> participante = listarParticipantes(  (tarefa.getProjeto()).getId()  );

		for (int i=0; i < participante.size(); i++ ){

			if(  ( tarefa.getSolicitante() ).getNome().equals( (participante.get(i) ).getNome()   )      ){

				confir = true;
			}

		}

		return confir;
	}
			
	
	public boolean isTarefaProjeto(String sigla, Tarefa tarefa) throws ValidacaoException{
		
		boolean confir = false;
		
		List<Tarefa> tarefasLista = listarTarefas(sigla);
		
		for(Tarefa aux: tarefasLista){

			if( aux.getNome().equals(tarefa.getNome() ) ){

				confir = true;

			}
		}
		
		return confir;
	}
	

	public void removerTarefa(String sigla, Tarefa tarefa) throws ValidacaoException{
		
		confirmarExistencia(sigla);
		
		projetoModel.removerTarefas(sigla, tarefa);
	}
						
	
	public boolean isParticipanteProjeto(String sigla, Pessoa pessoa)
			throws ValidacaoException {
		
		List<Pessoa> participantes = listarParticipantes(sigla);
		
		boolean nome = false; 
		
		for(Pessoa aux: participantes){
			
			if (aux.getNome().equals(pessoa.getNome())){
				
				nome = true;
			}
		}
		
		return nome;
	}
																	
	
}
