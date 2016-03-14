package modulo07;

public class Triangulo extends PoligonoAbstrato {
	
	
	protected Triangulo ( String nome, double ladoA, double ladoB, double ladoC){
		
		super(nome, ladoA, ladoB, ladoC);
		
	}
	
	protected Triangulo (double ladoA, double ladoB, double ladoC){
		
		super("Triangulo", ladoA, ladoB, ladoC);
		
	}
	
	@Override
	public double calcularPerimetro(){
		
		double perimetro = arrayLados[0]+arrayLados[1]+arrayLados[2];
		
		return perimetro;
	}
	
	
	@Override
	public double calcularArea(){
		
		double p = (arrayLados[0]+arrayLados[1]+arrayLados[2])/2;
		
		double area = Math.sqrt( p*(p-arrayLados[0]) * p*(p-arrayLados[1]) * p*(p-arrayLados[2]));
		
		return area;
	}
}
