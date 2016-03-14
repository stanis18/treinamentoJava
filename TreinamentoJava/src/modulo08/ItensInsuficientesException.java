package modulo08;

public class ItensInsuficientesException extends Exception{

	public ItensInsuficientesException(){

		super ("Não existem itens suficientes!");
	}
	
	public ItensInsuficientesException(String titulo, int livro){
		
		super("Não existe esta quantidade do livro " + titulo + " para remover. Deseja remover esta quantidade " + livro + "?");
		
	}
	
}
