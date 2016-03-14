package modulo07;

public class Modulo07 {
	
	public static void main(String[] args) {
						
		InterfacePoligono  trianguloEquilatero = new TrianguloEquilatero(10);
		InterfacePoligono  quadrado = new Quadrado(8);
		InterfacePoligono  retangulo = new Retangulo(2, 4);
		InterfacePoligono  triangulo = new Triangulo(10, 5, 8);
		
		
		imprimirDados(trianguloEquilatero);
		imprimirDados(quadrado);
		imprimirDados(retangulo);
		imprimirDados(triangulo);
		
	}
	
	
	public static void imprimirDados(InterfacePoligono poligono){
		
		System.out.println(poligono.getNome());
		System.out.println(poligono.calcularPerimetro());
		System.out.println(poligono.calcularArea());
		
	}
	
}
