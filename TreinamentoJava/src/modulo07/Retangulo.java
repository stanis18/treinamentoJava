package modulo07;

public class Retangulo extends PoligonoAbstrato {
		
	
	protected Retangulo(String nome, double ladoBase, double ladoAltura) {
		super(nome, ladoBase, ladoAltura);
	}
	
	protected Retangulo(double ladoBase, double ladoAltura){

		super("Retangulo", ladoBase, ladoAltura);
	}

	@Override
	public double calcularArea(){

		double area = arrayLados[0]*arrayLados[1];

		return area;
	}

	@Override
	public double calcularPerimetro(){

		double perimetro = arrayLados[0]*2 + arrayLados[1]*2;
		
		return perimetro;
	}
}

