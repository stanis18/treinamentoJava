package facade;
import entities.*;
import controller.*;
import exceptions.*;
import java.util.*;

public class FacadePessoaTeste {
	
	private static PessoaController pessoaController = new PessoaController();
	
	
	public void cadastrarPessoa(Pessoa pessoa) throws ValidacaoException {
		
		pessoaController.cadastrar(pessoa);
		
	}
	
	
	public void atualizarPessoa(Pessoa pessoa) throws ValidacaoException {
		
		pessoaController.atualizar(pessoa);
	}

	
	public void excluirPessoa(Integer matricula) throws ValidacaoException {
		
		pessoaController.excluir(matricula);
	}
	
	
	public Pessoa buscarPessoa(Integer matricula) {
		
		return pessoaController.consultar(matricula);
	
	}
	
	public List <Pessoa> listarPessoas() {
						
		
		return pessoaController.listar();
	}	
		
}
