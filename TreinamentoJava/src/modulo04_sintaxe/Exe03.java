package modulo04_sintaxe;

public class Exe03 {
	
	public static void main(String[] args) {

		double fatorial= 1;
		

		for (int i = 1; i<=10; i++){

			fatorial = fatorial*i;
			
			System.out.println("O fatorial de "+i+" é igual a: "+ fatorial);					
			
		}
	}
}
