package modulo07;

public class Quadrado extends Retangulo {

	
	protected Quadrado(String nome, double lado){
		
		super(nome, lado, lado);
	}

	protected Quadrado(double lado){
		
		super("Quadrado", lado, lado);
	}
}