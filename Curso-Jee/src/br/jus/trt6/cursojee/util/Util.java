package br.jus.trt6.cursojee.util;

import org.hibernate.validator.AssertTrue;


/**
 * Classe utilitária
 */
public class Util {
	private static final int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

	
	@AssertTrue(message="{validation.cpf}")
	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	/**
	 * Verifica se cpf é válido. Considera apenas os valores numéricos, qualquer outro caractere será desconsiderado,
	 * como sendo parte da máscara.
	 * @param cpf CPF.
	 * @return true se for válido, false caso contrário.
	 */
	public static boolean isCPFValido(String cpf) {
		
		// extrai a máscara para validar apenas os números
		String cpfNum = extrairNumeros(cpf);
		
		if ((cpfNum == null) || (cpfNum.length() != 11))
			return false;

		Integer digito1 = calcularDigito(cpfNum.substring(0, 9), pesoCPF);
		Integer digito2 = calcularDigito(cpfNum.substring(0, 9) + digito1, pesoCPF);
		return cpfNum.equals(cpfNum.substring(0, 9) + digito1.toString()
				+ digito2.toString());
	}

	/**
	 * Remove os valores não numéricos de uma string.
	 * @param valor String a ser analisada. 
	 * @return String com os valores numéricos removidos.
	 */
	public static String extrairNumeros(String valor) {
		StringBuffer result = new StringBuffer("");
		
		if (!isStringEmpty(valor)) {
	        for (int i = 0; i < valor.length(); i++) {  
	            if (Character.isDigit(valor.charAt(i))) {  
	                result.append(valor.charAt(i));  
	            }    
	        }    
		}
        return result.toString(); 
	}
	
	public static boolean isStringEmpty(String s) {
		return s == null || s.trim().isEmpty();
	}
}
