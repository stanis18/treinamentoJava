package modulo08;

public class ItensInsuficientesException extends Exception{

	public ItensInsuficientesException(){

		super ("N�o existem itens suficientes!");
	}
	
	public ItensInsuficientesException(String titulo, int livro){
		
		super("N�o existe esta quantidade do livro " + titulo + " para remover. Deseja remover esta quantidade " + livro + "?");
		
	}
	
}
