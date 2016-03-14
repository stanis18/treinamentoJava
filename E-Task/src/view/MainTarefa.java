package view;
import java.util.*;

import exceptions.*;
import facade.*;
import entities.*;
import entities.Pessoa.PERFIL;

public class MainTarefa {


	static FacadePessoaTeste facadePessoa =  new FacadePessoaTeste();
	static FacadeProjetoTeste facadeProjeto =  new FacadeProjetoTeste();
	static FacadeTarefaTeste facadeTarefa =  new FacadeTarefaTeste();

	public static void main(String[] args) {

		System.out.println("Iniciando testes para Tarefas: \n");

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

		// criando projetos e adicionando participantes
		Projeto etask = new Projeto("Etask", "etask");
		Projeto gmail = new Projeto("Gmail", "gmail");

		try {
			facadeProjeto.cadastrar(etask);
			facadeProjeto.cadastrar(gmail);
			facadeProjeto.adicionarParticipante(etask.getId(), augusto);
			facadeProjeto.adicionarParticipante(gmail.getId(), augusto);
			facadeProjeto.adicionarParticipante(etask.getId(), priscilla);
			facadeProjeto.adicionarParticipante(gmail.getId(), priscilla);
			facadeProjeto.adicionarParticipante(etask.getId(), rafaella);

		} catch (ValidacaoException e) {
			System.out.println(">>> Erro no cadastro de projetos");
			e.printStackTrace();
		}		


		// criando tarefa
		Tarefa tarefaG1 = criarTarefa("tarefa 1", new Date(), new Date(), augusto, priscilla, gmail);
		Tarefa tarefaE1 = criarTarefa("tarefa 1", new Date(), new Date(), augusto, rafaella, etask);

		Tarefa tarefaE2 = criarTarefa("tarefa 2", new Date(), new Date(), priscilla, rafaella, etask);
		Tarefa tarefaE3 = criarTarefa("tarefa 3", new Date(), new Date(), priscilla, augusto, etask);

		Tarefa tarefaInvalida = criarTarefa(null, new Date(), new Date(), priscilla, augusto, etask);
		Tarefa tarefaRepetida = criarTarefa("tarefa 3", new Date(), new Date(), priscilla, augusto, etask);
		Tarefa tarefaResponsavelInvalido = criarTarefa("tarefa 5", new Date(), new Date(), rafaella, augusto, gmail);
		Tarefa tarefaSolicitanteInvalido = criarTarefa("tarefa 5", new Date(), new Date(), augusto, rafaella, gmail);


		/*#### INCLUSAO ####*/
		// testando inclusoes v�lidas

		testarCadastrarTarefa(tarefaG1, true, null);//ok
		testarCadastrarTarefa(tarefaE1, true, null);//ok
		testarCadastrarTarefa(tarefaE2, true, null);//ok
		testarCadastrarTarefa(tarefaE3, true, null);//ok

		testarQuantidadeListagemTarefas(4);//ok

		testarQuantidadeListagemTarefas(etask.getId(), 3, true, null);//ok
		testarQuantidadeListagemTarefas(gmail.getId(), 1, true, null);//ok

		testarQuantidadeTarefasResponsavelTarefas(augusto.getId(), 2, true, null);//ok

		testarQuantidadeTarefasSolicitanteTarefas(augusto.getId(), 1, true, null);//ok

		testarQuantidadeTarefasResponsavelTarefas(priscilla.getId(), 2, true, null);//ok
		testarQuantidadeTarefasSolicitanteTarefas(priscilla.getId(), 1, true, null);//ok

		testarQuantidadeTarefasResponsavelTarefas(rafaella.getId(), 0, true, null);//ok
		testarQuantidadeTarefasSolicitanteTarefas(rafaella.getId(), 2, true, null);//ok

		// testando inclusoes inv�lidas

		testarCadastrarTarefa(tarefaInvalida, false, "Nome é obrigatório");//ok
		testarQuantidadeListagemTarefas(4);//ok
		testarQuantidadeListagemTarefas(etask.getId(), 3, true, null);//ok

		testarCadastrarTarefa(tarefaRepetida, false, "tarefa já cadastrada");//ok

		testarQuantidadeListagemTarefas(4);//ok						
		testarQuantidadeListagemTarefas(etask.getId(), 3, true, null);//ok

		testarCadastrarTarefa(tarefaResponsavelInvalido, false, "Responsavel deve ser participante do projeto!");//ok
		testarQuantidadeListagemTarefas(4);//ok
		testarQuantidadeListagemTarefas(etask.getId(), 3, true, null);//ok


		testarCadastrarTarefa(tarefaSolicitanteInvalido, false, "Solicitante deve ser participante do projeto!");//ok
		testarQuantidadeListagemTarefas(4);//ok
		testarQuantidadeListagemTarefas(etask.getId(), 3, true, null);//ok


		/*#### BUSCA ####*/		
		// testando busca valido

		Tarefa tarefaG1Clone = criarTarefa("tarefa 1", new Date(), new Date(), augusto, priscilla, gmail);
		tarefaG1Clone.setId(tarefaG1.getId());

		testarBuscarTarefa(tarefaG1Clone, true);//ok
		testarBuscarTarefa(tarefaE3, true);//ok

		// testando buscar tarefa invalido

		tarefaG1Clone.setId(1000);//ok 
		testarBuscarTarefa(tarefaG1Clone, false);// busca de uma tarefa que não esta no banco de dados

		Tarefa tarefaE2Invalida = criarTarefa("tarefa 2", new Date(), new Date(), priscilla, rafaella, gmail);
		testarBuscarTarefa(tarefaE2Invalida, false);//nao entendi porque é invalida

		/*#### ALTERACAO ####*/						
		// testando alterar os dados de tarefa v�lido

		Tarefa tarefaE3Clone = criarTarefa("tarefa 3", new Date(), new Date(), priscilla, augusto, etask);

		tarefaE3Clone.setId(tarefaE3.getId()); 
		tarefaE3Clone.setResponsavel(rafaella);	

		testarAlterarTarefa(tarefaE3Clone, true, null);//ok

		testarQuantidadeTarefasResponsavelTarefas(rafaella.getId(), 1, true, null);
		testarQuantidadeTarefasSolicitanteTarefas(rafaella.getId(), 2, true, null);		


	}

	private static Tarefa criarTarefa(String nome, Date dataInicio, Date dataFim, 
			Pessoa responsavel, Pessoa solicitante, Projeto etask) {

		Tarefa tarefa = new Tarefa();
		tarefa.setNome(nome);
		tarefa.setDataFim(dataFim);
		tarefa.setDataInicio(dataInicio);
		tarefa.setDescricao("Descricao");
		tarefa.setProjeto(etask);
		tarefa.setResponsavel(responsavel);
		tarefa.setSolicitante(solicitante);

		return tarefa;
	}


	private static void testarAlterarTarefa(Tarefa tarefa, boolean deveriaAlterar, String erro) {
		try {
			facadeTarefa.atualizar(tarefa);
			if (!deveriaAlterar) {
				System.out.println(">>> Esperava-se uma excecao: " + erro);
			} else {

				// verifica os dados alterados
				Tarefa tarefaAlterado = facadeTarefa.buscar(tarefa.getId());
				if (tarefa.getResponsavel().equals(tarefaAlterado.getResponsavel())) {
					System.out.println("OK");
				} else {
					System.out.println(">>> Os dados não foram alterados corretamente.");
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

	private static void testarBuscarTarefa(Tarefa tarefa, boolean deveriaEncontrar) {
		Tarefa tarefaEncotrado = facadeTarefa.buscar(tarefa.getId());
		if (tarefaEncotrado != null) {

			if (deveriaEncontrar) {
				if (tarefa.getNome().equals(tarefaEncotrado.getNome())) {
					System.out.println("OK");
				} else {
					System.out.println("Buscou a tarefa errada. Esperado: " + tarefa.getNome() + " Encontrado: " + tarefaEncotrado.getNome());
				}
			} else {
				System.out.println(">>> Encontrou a tarefa com o nome " + tarefa.getNome() + ", mas n�o deveria.");
			}

			// se n�o encontrou
		} else {
			if (deveriaEncontrar) {
				System.out.println(">>> Deveria ter encontrado uma tarefa com nome: " + tarefa.getNome());
			} else {
				System.out.println("OK");
			}
		}
	}

	private static void testarCadastrarTarefa(Tarefa tarefa, boolean deveriaCadastrar, String erro) {
		try {
			facadeTarefa.cadastrar(tarefa);
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


	private static void testarQuantidadeListagemTarefas(int tamEsperado) {
		List<Tarefa> listarTarefas = facadeTarefa.listar();
		int tamanho = listarTarefas == null ? 0 : listarTarefas.size();

		if (tamanho == tamEsperado) {
			System.out.println("OK");
		} else {
			System.out.println(">>> Erro na quantidade de tarefas cadastrados. Esperado: " + tamEsperado + " Recebido: " + tamanho);
		}
	}

	private static void testarQuantidadeListagemTarefas(String siglaProjeto, int tamEsperado, boolean deveriaListar, String erro) {
		try {
			List<Tarefa> tarefasProjeto;
			tarefasProjeto = facadeTarefa.listarTarefas(siglaProjeto);

			if (deveriaListar) {

				int tamanho = tarefasProjeto == null ? 0 : tarefasProjeto.size();

				if (tamanho == tamEsperado) {
					System.out.println("OK");
				} else {
					System.out.println(">>> Erro na quantidade de tarefas no projeto. Esperado: " + tamEsperado + " Recebido: " + tamanho);
				}

			} else {
				System.out.println(">>> Esperava-se uma excecao: " + erro);
			}
		} catch (ValidacaoException e) {
			if (deveriaListar) {
				System.out.println("Erro ao listar tarefas de projeto");
				e.printStackTrace();
			} else {
				System.out.println("OK");
			}
		}
	}

	private static void testarQuantidadeTarefasResponsavelTarefas(Integer matriculaResponsavel, int tamEsperado, boolean deveriaListar, String erro) {
		try {
			List<Tarefa> tarefasResponsavel = facadeTarefa.listarTarefasResponsavel(matriculaResponsavel);

			if (deveriaListar) {

				int tamanho = tarefasResponsavel == null ? 0 : tarefasResponsavel.size();

				if (tamanho == tamEsperado) {
					System.out.println("OK");
				} else {
					System.out.println(">>> Erro na quantidade de tarefas do responsavel. Esperado: " + tamEsperado + " Recebido: " + tamanho);
				}

			} else {
				System.out.println(">>> Esperava-se uma excecao: " + erro);
			}
		} catch (ValidacaoException e) {
			if (deveriaListar) {
				System.out.println("Erro ao listar tarefas de responsavel");
				e.printStackTrace();
			} else {
				System.out.println("OK");
			}
		}
	}

	private static void testarQuantidadeTarefasSolicitanteTarefas(Integer matriculaResponsavel, int tamEsperado, boolean deveriaListar, String erro) {
		try {
			List<Tarefa> tarefasSolicitante = facadeTarefa.listarTarefasSolicitante(matriculaResponsavel);

			if (deveriaListar) {

				int tamanho = tarefasSolicitante == null ? 0 : tarefasSolicitante.size();

				if (tamanho == tamEsperado) {
					System.out.println("OK");
				} else {
					System.out.println(">>> Erro na quantidade de tarefas do solicitante. Esperado: " + tamEsperado + " Recebido: " + tamanho);
				}

			} else {
				System.out.println(">>> Esperava-se uma excecao: " + erro);
			}
		} catch (ValidacaoException e) {
			if (deveriaListar) {
				System.out.println("Erro ao listar tarefas de solicitante");
				e.printStackTrace();
			} else {
				System.out.println("OK");
			}
		}
	}

}

