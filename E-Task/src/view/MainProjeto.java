package view;

import entities.*;
import entities.Pessoa.PERFIL;
import facade.*;
import exceptions.*;
import java.util.*;


public class MainProjeto {
		
	
	static FacadePessoaTeste facadePessoa =  new FacadePessoaTeste();
	static FacadeProjetoTeste facadeProjeto =  new FacadeProjetoTeste();
	
	public static void main(String[] args) {
		
		
		
		System.out.println("Iniciando testes para Projetos: \n");
		
		// criando pessoas
		Pessoa augusto =  new Pessoa("Augusto", PERFIL.ADMIN);
		Pessoa priscilla =  new Pessoa("Priscilla", PERFIL.USUARIO);
		Pessoa rafaella =  new Pessoa("Rafaella", PERFIL.ADMIN);
		
		try {
			facadePessoa.cadastrarPessoa(augusto);
			facadePessoa.cadastrarPessoa(priscilla);
			facadePessoa.cadastrarPessoa(rafaella);
		} catch (ValidacaoException e) {
			System.out.println(">>> Erro no cadastro de pessoas");
			e.printStackTrace();
		}
			
		
		// criando projetos
		Projeto etask = new Projeto("Etask", "etask");//ok
		Projeto gmail = new Projeto("Gmail", "gmail");
		
		Projeto nomeInvalido = new Projeto(null, "sigla");//ok
		Projeto siglaInvalida = new Projeto("nome", null);//ok
		Projeto siglaRepetida = new Projeto("nome", "gmail");//ok
		
				
		// testando inclusoes v�lidas
		testarCadastrarProjeto(etask, true, null);//ok
		testarCadastrarProjeto(gmail, true, null);//ok
		testarQuantidadeListagem(2);//ok
		
				
		// testando inclusoes inv�lidas
		testarCadastrarProjeto(nomeInvalido, false, "Nome é obrigatório");//ok
		testarQuantidadeListagem(2);//ok
		
		testarCadastrarProjeto(siglaInvalida, false, "Sigla é obrigatório");//ok
		testarQuantidadeListagem(2);//ok
					
		testarCadastrarProjeto(siglaRepetida, false, "Sigla não pode ser repetida");//ok
		testarQuantidadeListagem(2);//ok
				
		// testando busca projeto valido
		testarBuscarProjeto(etask, true);//ok
		testarBuscarProjeto(gmail, true);//ok
					
		// testando buscar projeto invalido
		testarBuscarProjeto(nomeInvalido, false);//ok
		testarBuscarProjeto(siglaInvalida, false);//ok
		
					
		// testando alterar os dados de projeto v�lido
		Projeto etask_alterado = new Projeto("Etask", "etask");//ok
		
		etask_alterado.setNome("Etask 2");
		testarAlterarProjeto(etask_alterado, true, null);//ok
			
		
		etask_alterado.setNome("Etask");
		testarAlterarProjeto(etask, true, null);//ok
		
						
		// testando alterar dados de pessoa invalida
		etask_alterado.setNome(null);
		testarAlterarProjeto(etask_alterado, false, "Nome e obrigatorio!");//ok
		
				
		etask_alterado.setId(null);
		testarAlterarProjeto(etask_alterado, false, "Sigla e obrigatoria!");//ok
				
		
		Projeto inexistente = new Projeto("Sigla inexistente", "SInex");
		testarAlterarProjeto(inexistente, false, "Sigla não existe");
			
		
		// incluindo participantes v�lidos ao projeto
		testarAdicionarParticipante(etask, augusto, true, null);//ok
		testarAdicionarParticipante(etask, priscilla, true, null);//ok
		testarAdicionarParticipante(gmail, rafaella, true, null);//ok
		
		testarQuantidadeParticipantes(etask, 2, true, null);//ok
		testarQuantidadeParticipantes(gmail, 1, true, null);//ok
						
		
		// incluindo participantes invalidos
		Projeto projetoInexistente = new Projeto("Inexistente", "inexistente");
		Pessoa pessoaInexistente =  new Pessoa("Inexistente", PERFIL.ADMIN);
						
		
		testarAdicionarParticipante(etask, augusto, false, "Já faz parte do projeto");//ok
		testarAdicionarParticipante(gmail, pessoaInexistente, false, "Pessoa não existe");
		testarAdicionarParticipante(projetoInexistente, augusto, false, "Projeto não existe");//ok
				
		
		testarQuantidadeParticipantes(etask, 2, true, null);//ok
		testarQuantidadeParticipantes(gmail, 1, true, null);//ok
						
		
		// removendo participantes v�lidos
		testarRemoverParticipante(etask, priscilla, true, null);//not ok
					
		
		// removendo participantes inv�lidos
		testarRemoverParticipante(gmail, augusto, false, "Não é participante do projeto");//ok
		testarRemoverParticipante(etask, rafaella, false, "Não é participante do projeto");//ok
						
		
		testarRemoverParticipante(gmail, pessoaInexistente, false, "Pessoa não existe");//ok
		testarRemoverParticipante(projetoInexistente, augusto, false, "Projeto não existe");//ok		
		
				
		// testando excluir projeto valido
		testarExcluirProjeto(gmail.getId(), true);//ok
		testarQuantidadeListagem(1);//ok
		testarBuscarProjeto(gmail, false);//ok
		
		// testando excluir projeto invalido
		testarExcluirProjeto(gmail.getId(), false);//ok
		testarQuantidadeListagem(1);//ok
		
		testarExcluirProjeto(null, false);//ok
		testarQuantidadeListagem(1);//ok
		
		// finalizando, excluindo ultima pessoa
		testarExcluirProjeto(etask.getId(), true);//ok
		testarQuantidadeListagem(0);//ok
		testarBuscarProjeto(etask, false);//ok
								
	}
		
	
	private static void testarAdicionarParticipante(Projeto projeto, Pessoa participante, boolean deveriaIncluir, String erro) {
		try {
			facadeProjeto.adicionarParticipante(projeto.getId(), participante);
			if (!deveriaIncluir) {
				System.out.println(">>> Esperava-se uma excecao: " + erro);
			} else {
				
				// verifica se adicionou
				boolean adicionado = facadeProjeto.isParticipanteProjeto(projeto.getId(), participante);
				if (adicionado) {
					System.out.println("OK");
				} else {
					System.out.println(">>> O participante não foi alterado.");
				}
				
			}
		} catch (ValidacaoException e) {
			if (deveriaIncluir) {
				System.out.println(">>> Erro ao adicionar: " + e.getMessage());
			} else {
				System.out.println("OK");
			}	
		}
		
	}	
	
	
	private static void testarRemoverParticipante(Projeto projeto, Pessoa participante, boolean deveriaRemover, String erro) {
		try {
			facadeProjeto.removerParticipante(projeto.getId(), participante);
			if (!deveriaRemover) {
				System.out.println(">>> Esperava-se uma excecao: " + erro);
			} else {
				
				// verifica se removeu
				boolean removido = !facadeProjeto.isParticipanteProjeto(projeto.getId(), participante);
				if (removido) {
					System.out.println("OK");
				} else {
					System.out.println(">>> O participante n�o foi removido.");
				}
				
			}
		} catch (ValidacaoException e) {
			if (deveriaRemover) {
				System.out.println(">>> Erro ao remover: " + e.getMessage());
			} else {
				System.out.println("OK");
			}	
		}
		
	}	
	
	private static void testarAlterarProjeto(Projeto projeto, boolean deveriaAlterar, String erro) {
		try {
			facadeProjeto.atualizar(projeto);
			if (!deveriaAlterar) {
				System.out.println(">>> Esperava-se uma excecao: " + erro);
			} else {
				
				// verifica os dados alterados
				Projeto projetoAlterado = facadeProjeto.buscar(projeto.getId());
				if (projeto.getNome().equals(projetoAlterado.getNome())) {
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
	
	
	
	private static void testarExcluirProjeto(String sigla, boolean deveriaExcluir) {
		
		try {
			facadeProjeto.excluir(sigla);
			if (!deveriaExcluir) {
				System.out.println(">>> Esperava-se uma excecao, pois a sigla � invalida: " + sigla);
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
	
	
	
	private static void testarBuscarProjeto(Projeto projeto, boolean deveriaEncontrar) {
		Projeto projetoEncotrado = facadeProjeto.buscar(projeto.getId());
		if (projetoEncotrado != null) {
			
			if (deveriaEncontrar) {
				if (projeto.getNome().equals(projetoEncotrado.getNome())) {
					System.out.println("OK");
				} else {
					System.out.println("Buscou o projeto errado. Esperado: " + projeto.getNome() + " Encontrado: " + projetoEncotrado.getNome());
				}
			} else {
				System.out.println(">>> Encontrou um projeto com a sigla " + projeto.getId() + ", mas n�o deveria.");
			}
			
			// se n�o encontrou
		} else {
			if (deveriaEncontrar) {
				System.out.println(">>> Deveria ter encontrado um projeto com sigla: " + projeto.getId());
			} else {
				System.out.println("OK");
			}
		}
	}
	
	
	
	private static void testarCadastrarProjeto(Projeto projeto, boolean deveriaCadastrar, String erro) {
		try {
			facadeProjeto.cadastrar(projeto);
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

	
	private static void testarQuantidadeListagem(int tamEsperado) {
		List<Projeto> listarProjetos = facadeProjeto.listarProjetos();
		int tamanho = listarProjetos == null ? 0 : listarProjetos.size();
		
		if (tamanho == tamEsperado) {
			System.out.println("OK");
		} else {
			System.out.println(">>> Erro na quantidade de projetos cadastrados. Esperado: " + tamEsperado + " Recebido: " + tamanho);
		}
	}
	
	
	private static void testarQuantidadeParticipantes(Projeto projeto, int tamEsperado, boolean deveriaListar, String erro) {
		try {
			List<Pessoa> participantes;
			participantes = facadeProjeto.listarParticipantes(projeto.getId());
			
			if (deveriaListar) {
				
				int tamanho = participantes == null ? 0 : participantes.size();
				
				if (tamanho == tamEsperado) {
					System.out.println("OK");
				} else {
					System.out.println(">>> Erro na quantidade de participantes para o projeto. Esperado: " + tamEsperado + " Recebido: " + tamanho);
				}
				
			} else {
				System.out.println(">>> Esperava-se uma excecao: " + erro);
			}
		} catch (ValidacaoException e) {
			if (deveriaListar) {
				System.out.println("Erro ao listar participantes de projeto");
				e.printStackTrace();
			} else {
				System.out.println("OK");
			}
		}
		
		
	} 
				
}
