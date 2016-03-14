package view;
import entities.*;
import entities.Pessoa.PERFIL;
import facade.*;
import exceptions.*;
import java.util.*;

public class MainPessoa {
			
static FacadePessoaTeste facadeTeste =  new FacadePessoaTeste();
	
	public static void main(String[] args) {
		
		System.out.println("Iniciando testes para Pessoas: \n");
		
		// criando pessoas
		Pessoa augusto =  new Pessoa("Augusto", PERFIL.ADMIN);
		Pessoa priscilla =  new Pessoa("Priscilla", PERFIL.USUARIO);
		Pessoa rafaella =  new Pessoa("Rafaella", PERFIL.ADMIN);
		Pessoa nomeInvalido =  new Pessoa(null, PERFIL.ADMIN);
		Pessoa perfilInvalido =  new Pessoa("Perfil Invalido", null);
		Pessoa matriculaInvalido =  new Pessoa("Matricula Invalida", PERFIL.USUARIO);						
		matriculaInvalido.setId(100);
		
		// testando inclusoes invalidas
		testarCadastrarPessoa(augusto, true, null); //ok
		testarMatriculaGerada(augusto, 1);//ok
		
		testarCadastrarPessoa(priscilla, true, null);//ok
		testarMatriculaGerada(priscilla, 2);//ok
		
		testarCadastrarPessoa(rafaella, true, null);//ok
		testarMatriculaGerada(rafaella, 3);//ok
								
		testarQuantidadeListagem(3);//ok
		
						
		// testando inclusoes inv�lidas
		testarCadastrarPessoa(nomeInvalido, false, "Nome � obrigat�rio");//ok		
		testarQuantidadeListagem(3);//ok
		
		
		testarCadastrarPessoa(perfilInvalido, false, "Perfil � obrigat�rio");//ok
		testarQuantidadeListagem(3);//ok
		
		
		testarCadastrarPessoa(matriculaInvalido, false, "Matr�cula n�o deve ser informada na inclus�o");//ok
		testarQuantidadeListagem(3);//ok
								
		// testando busca pessoa valida
		testarBuscarPessoa(priscilla, true);//ok
		testarBuscarPessoa(augusto, true);//ok
		
		// testando buscar pessoa invalida
		testarBuscarPessoa(matriculaInvalido, false);//ok		
		testarBuscarPessoa(nomeInvalido, false);//ok
		
									
		
		// testando excluir pessoa valida
		testarExcluirPessoa(rafaella.getId(), true);//ok
		testarQuantidadeListagem(2);//ok
		testarBuscarPessoa(rafaella, false);//ok
		
				
		testarExcluirPessoa(priscilla.getId(), true);//ok
		testarQuantidadeListagem(1);//ok
		testarBuscarPessoa(priscilla, false);//ok
						
		// testando excluir pessoa invalida
		testarExcluirPessoa(rafaella.getId(), false);//ok
		testarQuantidadeListagem(1);//ok
						
		testarExcluirPessoa(null, false);//ok
		testarQuantidadeListagem(1);//ok
				
		
		// testando alterar os dados de pessoa v�lida
		augusto.setPerfil(PERFIL.USUARIO);//ok
		testarAlterarPessoa(augusto, true, null);		
				
		augusto.setNome("Breno");//ok
		testarAlterarPessoa(augusto, true, null);//ok
						
		// testando alterar dados de pessoa invalida
		augusto.setPerfil(null);
		testarAlterarPessoa(augusto, false, "Perfil e obrigatorio!");//ok
		
		
		augusto.setPerfil(PERFIL.USUARIO);
		augusto.setNome(null);
		testarAlterarPessoa(augusto, false, "Nome e obrigatorio!");//ok
		
		
		priscilla.setId(null);
		testarAlterarPessoa(priscilla, false, null);//ok
		testarAlterarPessoa(matriculaInvalido, false, "Matr�cula n�o existe!");//ok
		
		
		// finalizando, excluindo ultima pessoa
		testarExcluirPessoa(augusto.getId(), true);//ok
		testarQuantidadeListagem(0);//ok
		testarBuscarPessoa(augusto, false);//ok
						
	}
		
	private static void testarExcluirPessoa(Integer matricula, boolean deveriaExcluir) {
		
		try {
			facadeTeste.excluirPessoa(matricula);
			if (!deveriaExcluir) {
				System.out.println(">>> Esperava-se uma excecao, pois a matr�cula � invalida: " + matricula);
			} else {
				System.out.println("OK");
			}
		} catch (ValidacaoException e) {
			if (deveriaExcluir) {
				System.out.println(">>> Deveria excluir, mas: " + e.getMessage());
			} else {
				System.out.println("OK");
			}	
		}
		
	}
		
	
	private static void testarMatriculaGerada(Pessoa pessoa, Integer matEsperada) {
		if (matEsperada.equals(pessoa.getId())) {
			System.out.println("OK");
		} else {
			System.out.println(">>> Erro na matricula gerada: Esperado: " + matEsperada + " Gerada: " + pessoa.getId());
		}
	}
		
	
	private static void testarQuantidadeListagem(int tamEsperado) {
		List<Pessoa> listarPessoas = facadeTeste.listarPessoas();
		int tamanho = listarPessoas == null ? 0 : listarPessoas.size();
		
		if (tamanho == tamEsperado) {
			System.out.println("OK");
		} else {
			System.out.println(">>> Erro na quantidade de pessoas cadastradas. Esperado: " + tamEsperado + " Recebido: " + tamanho);
		}
	}
		
	
	private static void testarBuscarPessoa(Pessoa pessoa, boolean deveriaEncontrar) {
		Pessoa pessoaEncontrada = facadeTeste.buscarPessoa(pessoa.getId());
		if (pessoaEncontrada != null) {
			
			if (deveriaEncontrar) {
				if (pessoa.getNome().equals(pessoaEncontrada.getNome())) {
					System.out.println("OK");
				} else {
					System.out.println("Buscou a pessoa errada. Esperada: " + pessoa.getNome() + " Encontrada: " + pessoaEncontrada.getNome());
				}
			} else {
				System.out.println(">>> Encontrou uma pessoa com a matr�cula " + pessoa.getId() + ", mas n�o deveria.");
			}
			
			// se n�o encontrou
		} else {
			if (deveriaEncontrar) {
				System.out.println(">>> Deveria ter encontrado uma pessoa com matr�cula: " + pessoa.getId());
			} else {
				System.out.println("OK");
			}
		}
	}
				
	private static void testarCadastrarPessoa(Pessoa pessoa, boolean deveriaCadastrar, String erro) {
		try {
			facadeTeste.cadastrarPessoa(pessoa);
			if (!deveriaCadastrar) {
				System.out.println(">>> Esperava-se uma excecao: " + erro);
			} else {
				System.out.println("OK");
			}
		} catch (ValidacaoException e) {
			if (deveriaCadastrar) {
				System.out.println(">>> Erro ao incluir: " + e.getMessage());
			} else {
				System.out.println("OK");
			}	
		}
	}
	
		
	
	private static void testarAlterarPessoa(Pessoa pessoa, boolean deveriaAlterar, String erro) {
		try {
			facadeTeste.atualizarPessoa(pessoa);
			if (!deveriaAlterar) {
				System.out.println(">>> Esperava-se uma excecao: " + erro);
			} else {
				
				// verifica os dados alterados
				Pessoa pessoaAlterada = facadeTeste.buscarPessoa(pessoa.getId());
				if (pessoa.getNome().equals(pessoaAlterada.getNome())
						&& pessoa.getPerfil().equals(pessoaAlterada.getPerfil())) {
					System.out.println("OK");
				} else {
					System.out.println(">>> Os dados n�o foram alterados corretamente.");
				}
				
			}
		} catch (ValidacaoException e) {
			if (deveriaAlterar) {
				System.out.println(">>> Erro ao alterar: " + e.getMessage());
			} else {
				System.out.println("OK");
			}	
		}
		
		
	} 

}
