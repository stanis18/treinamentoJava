package modulo05;

import java.util.ArrayList;
import java.util.*;


public class Modulo05 {
	
	public static void main(String[] args) {

		/* CALCULE e LISTE os numeros primos */
		System.out.println("Encontrando os números primos existentes no intervalo de 45 a 1445");
		ArrayList<Integer> numerosPrimos = listaNumerosPrimos(45, 1445);
					
		for(Integer num: numerosPrimos){
			
			System.out.println(num);
			
		}
						
		
		/* Crie um ArrayList, insira os nomes "Pedro", "Jose", "Antonio", depois utilize o 
		 * for each para listar os elementos (verifique a ordenação) */
		// TODO IMPLEMENTE!
		
		System.out.println("Nomes Lista:");
		
		ArrayList<String> listaNomes = new ArrayList<String>();
		
		listaNomes.add("Pedro");
		listaNomes.add("Jose");
		listaNomes.add("Antonio");
		
		Collections.sort(listaNomes);
		
		for( String nome: listaNomes){

			System.out.println(nome);

		}
				
		
		/* Crie um TreeSet, insira os nomes "Pedro", "Jose", "Antonio", depois utilize o 
		 * for each para listar os elementos (verifique a ordenação) */
		// TODO IMPLEMENTE!		
		
		System.out.println("Nomes Arvore:");
		
		TreeSet<String> arvoreNomes = new TreeSet<String>();
		
		arvoreNomes.add("Pedro");
		arvoreNomes.add("Jose");
		arvoreNomes.add("Antonio");
		
		Iterator<String> iterador = arvoreNomes.iterator();
		
		while(iterador.hasNext()){
			
			System.out.println(iterador.next());
		}
		
		/* como você poeria utilizar uma estrutura de dados para facilitar esta busca? */
		System.out.println("Descricao do estado (Estrutura de Dados): " + recuperaDescricaoEstadoEstrutura("PE"));
		
	}
	
	/**
	 * Lista todos os números primos existentes em um determinado intervalo.
	 * @param inicioIntervalo Inicio do intervalo.
	 * @param fimIntervalo Fim do intervalo.
	 * @return A lista de números primos existente. Se não for encontrado nenhum número primo no intervalo, retorna uma lita vazia.
	 */
	public static ArrayList<Integer> listaNumerosPrimos(int inicioIntervalo, int fimIntervalo) {
		
		ArrayList<Integer> numPrimos = new ArrayList<Integer>();
		
		int i;
		
		for( int j = inicioIntervalo; j <= fimIntervalo; j++){
			
			i = 1;
			int numDivisores = 0;
			
			//procura todos os numeros primos
			while(i <= j){
				
				if ( j%i == 0){
					
					numDivisores++;
				}
				
				i++;									
			}
			
			//caso o numero tenha dois divisores ou menos sera adicionado na lista
			if (numDivisores <=2){ 
				
				numPrimos.add(j);
			}
			
		}
								
		return numPrimos;
	}
	

	/**
	 * Recupera a descrição de um estado a partir de seu código. Deverá ser utilizada uma solução baseada em estrutura 
	 * de dados.
	 * @param cod Código do estado. Referente a um dos atributos estáticos da classe.
	 * @return descrição do estado. Referente a um dos atributos estáticos da classe.
	 */
	private static String recuperaDescricaoEstadoEstrutura(String se) {

		String nomeEstado = RepositorioEstados.getDescricaoEstado(se);
		
		return nomeEstado;
	}
}
