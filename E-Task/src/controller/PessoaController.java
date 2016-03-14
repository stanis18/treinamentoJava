package controller;
import model.*;
import entities.*;
import exceptions.*;

import java.util.*;

public class PessoaController {

	private static PessoaModel pessoaModel = new PessoaModel();
	
	public void cadastrar(Pessoa pessoa) throws ValidacaoException{
		
		validarCadastrar(pessoa);
		pessoaModel.cadastrar(pessoa);
	}

	private void validarCadastrar(Pessoa pessoa) throws ValidacaoException {
		
		if(pessoa.getNome() ==null || pessoa.getPerfil() == null || pessoa.getId() != null){

			throw new ValidacaoException();
		}
	}

	public void atualizar(Pessoa pessoa) throws ValidacaoException{
		validarAtualizar(pessoa);
		pessoaModel.atualizar(pessoa);
	}

	
	private void validarAtualizar(Pessoa pessoa)
			throws ValidacaoException {
		if(pessoa.getNome() ==null || pessoa.getPerfil() == null || pessoa.getId() == null ||
				(Pessoa) pessoaModel.consultar(pessoa.getId()) == null){

			throw new ValidacaoException();
		}
	}

	public Pessoa consultar(Integer id){

		return (Pessoa) pessoaModel.consultar(id);
	}

	public void excluir(Integer id) throws ValidacaoException{

		if ((Pessoa) pessoaModel.consultar(id) == null){

			throw new ValidacaoException();
		}

		pessoaModel.excluir(id);

	}						
	
			
	public List<Pessoa> listar(){
		
		return pessoaModel.listar();
	}

}
