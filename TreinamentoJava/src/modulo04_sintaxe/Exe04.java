package modulo04_sintaxe;

import java.util.*;

public class Exe04 {
	
	public static void main(String[] args) {
		
		Scanner scn = new Scanner(System.in);
		int numero;
		
		System.out.println("Digite o numero: ");
		numero= scn.nextInt();
		
		while ( numero/2 != 0){
			
			numero = modificarNumero(numero);			
			System.out.println("O numero é: " + numero);		
		}
								
	}// fim do metodo main
	
	
	public static int modificarNumero(int number){
						
		if ( number % 2 == 0){
						
			number = number/2;
		}else{
			
			number = number*3 +1;			
		}
		
		return number;
	}
}
