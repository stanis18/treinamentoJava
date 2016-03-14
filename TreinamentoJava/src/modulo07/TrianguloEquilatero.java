package modulo07;

public class TrianguloEquilatero extends Triangulo {
	
		
	protected TrianguloEquilatero(String nome, double lado){
		
		super(nome, lado, lado, lado);
	}
	
	protected TrianguloEquilatero (double lado){
		
		super("Triangulo Equilatero", lado, lado, lado);
	}
	
	@Override
	public double calcularArea(){
		
	double altura = (arrayLados[0]*Math.sqrt(3))/2 ;
	
	double area = (altura * arrayLados[0])/2;
	
	return area;
	
	}

}
