package modulo09Enum;

public class Modulo09Enum {
	
	public static void main(String[] args) {
		
		// Utilização de Enuns			
		System.out.println("Bem vindo a lanchonete TRT-PE! O que você deseja?");
		System.out.println(Bebidas.COCACOLA.getNome() + " "+ Bebidas.COCACOLA.getPreco() + " " +Bebidas.COCACOLA.getMls());
		System.out.println(Bebidas.SUCO.getNome() + " "+ Bebidas.SUCO.getPreco() + " " +Bebidas.SUCO.getMls());		
		System.out.println(Bebidas.CAFE.getNome() + " "+ Bebidas.CAFE.getPreco() + " " +Bebidas.CAFE.getMls());		
		
	}
			
	public enum Bebidas{
		
		COCACOLA("Coca-cola", 2.50, "300ml"), SUCO("Laranja", 3.00, "350ml"), CAFE("Café", 2.00, "100ml"); 
		
		private String nome;
		private double preco;
		private String mls;
				
		Bebidas(String nome, double preco, String mls){

			this.nome =  nome;
			this.preco = preco;
			this.mls = mls;
		}
				
		public String getNome(){
			
			return nome;
		}
		
		public double getPreco(){

			return preco;
		}
		
		public String getMls(){
			
			return mls;
		}
				
	}		
	
}
