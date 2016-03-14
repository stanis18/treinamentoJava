package modulo08;

import java.util.*;

public class ControleEstoque {
	
	ArrayList <ItemEstoque> itensEstoque = new ArrayList<ItemEstoque> ();
	
	
	public void adicionarItem(Livro livro, int quantidade){
				
		//Verifica se j� existe copias do livro do repositorio
		if (buscarItem(livro.getTitulo(), livro.getEdicao()) != null){
			
			//Faz uma nova chamada ao metodo buscarItem para fazer as modifica��es necessarias
			ItemEstoque aux = buscarItem(livro.getTitulo(), livro.getEdicao());
			
			//Faz a opera��o para determinar a nova quantidade
			int novaQtd = aux.getQuantidade() + quantidade;
			
			//Atribui a nova quantidade
			aux.setQuantidade(novaQtd);

		}else{
			
			ItemEstoque item =  new ItemEstoque();
			
			item.setLivro(livro);
			item.setQuantidade(quantidade);			
			itensEstoque.add(item);
		}								
	}
	
	public void removerItem(Livro livro, int quantidade ) throws ItensInsuficientesException{
				
		//Verifica se j� existe copias do livro do repositorio
		if (buscarItem(livro.getTitulo(), livro.getEdicao()) != null){

			//Caso exista seleciona o livro com uma copia igua e coloca em uma variavel auxiliar 
			ItemEstoque aux = buscarItem(livro.getTitulo(), livro.getEdicao());
					
			//Faz a opera��o para determinar a nova quantidade
			int novaQtd = aux.getQuantidade() - quantidade;
			
			if(novaQtd < 0){
				
				// lan�ando a exce��o
				throw new ItensInsuficientesException(livro.getTitulo(), aux.getQuantidade());
			}else{
				
				//Atribui a nova quantidade
				aux.setQuantidade(novaQtd);	
			}					
		}
				
	}
	
	public ItemEstoque buscarItem(String titulo, String edicao){
		
		ItemEstoque itemEstoque = null;
		
		for ( ItemEstoque item: itensEstoque ){
			
			if (item.getLivro().getTitulo().equals(titulo)){
				
				itemEstoque = item;
			}			
		}
				
		return itemEstoque;
	}
	
	
}
