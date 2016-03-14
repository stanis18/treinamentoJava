package modulo07;

public abstract class PoligonoAbstrato implements InterfacePoligono {
	
	private String nome;
	protected double [] arrayLados;
	
	
	
	//double... indica que pode-se ou não passar um array de parametros 
	public PoligonoAbstrato(String nome, double...lados) { 
		
		this.nome = nome;
		this.arrayLados = lados;
	}
	
	@Override
	public String getNome(){
		
		return nome;
	}
		
	@Override
	public int numeroLados(){
		
		return arrayLados.length;
	}
	
	@Override
	public double[] tamanhoLados(){
		
		return arrayLados;
	}
	
	//metodos abstrato para retornar perimetro
	public abstract double calcularPerimetro();
	
	//metodo abstrato para retornar area
	public abstract double calcularArea();
}
