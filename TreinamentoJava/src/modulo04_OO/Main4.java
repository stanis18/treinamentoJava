package modulo04_OO;

import java.util.*;

public class Main4 {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);	
				
		Agencia agen = new Agencia();
		Cliente cli = new Cliente();
				
		
		System.out.print("Digite o numero da agencia: ");
		agen.setNumero(scn.nextInt());
		
		System.out.print("Digito o digito: ");
		agen.setDigito(scn.nextInt());
				
		
		System.out.print("Digite o nome do cliente: ");
		cli.setNome(scn.next());
		
		System.out.print("Digite o cpf: ");
		cli.setCpf(scn.next());
		
		ContaCorrente conta = new ContaCorrente(agen, cli, "259874-X", 256);
				
		System.out.println("Numero da agencia com digito: "+conta.getAgencia().getNumero() + "-" + 
		conta.getAgencia().getDigito());
		
		System.out.println("cpf " + conta.getCliente().getCpf() + " e nome " + conta.getCliente().getNome() );
		
		System.out.println("Numero da conta corrente é: " + conta.getNumeroCC());
		
		System.out.println("Saldo inicial de: " + conta.getSalo());
		
		
	}
}
