package facade;

import entities.*;
import controller.*;
import exceptions.*;
import java.util.*;

public class FacadeProjetoTeste {
	
	private static ProjetoController projetoController = new ProjetoController();
	
	
	public void cadastrar(Projeto projeto) throws ValidacaoException {
			
		projetoController.cadastrar(projeto);
	}
	
		
	public void atualizar(Projeto projeto) throws ValidacaoException {
		
		
		projetoController.atualizar(projeto);						
	}
	
				
	public Projeto buscar(String sigla) {

		return projetoController.buscar(sigla);				 
	}


	public List<Pessoa> listarParticipantes(String sigla) throws ValidacaoException {

		return projetoController.listarParticipantes(sigla);
	}


	public void adicionarParticipante(String sigla, Pessoa pessoa) throws ValidacaoException {

		projetoController.adicionarParticipante(sigla, pessoa);

	}


	public List<Projeto> listarProjetos() {


		return projetoController.listar();
	}

	public void removerParticipante(String sigla, Pessoa pessoa) throws ValidacaoException {
		
		projetoController.removerParticipante(sigla, pessoa);
	}	
	
	
	public void excluir(String sigla) throws ValidacaoException {
		
		projetoController.remover(sigla);
		
	}

	public boolean isParticipanteProjeto(String sigla, Pessoa pessoa) throws ValidacaoException {
		
			 		
		return projetoController.isParticipanteProjeto(sigla, pessoa);
	}	
			
	
}
