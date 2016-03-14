package br.jus.trt6.cursojee.entidades.validator;

import org.hibernate.validator.*;
import java.io.*;

@SuppressWarnings("serial")
public class ValidarTokensImpl implements Validator<ValidarTonkens>, Serializable{
	
	Integer numMinimo;
	
	@Override
	public void initialize(ValidarTonkens minimo) {
		 
		numMinimo =  minimo.minimo();
	}

	@Override
	public boolean isValid(Object nome) {
		
		if (nome != null) {
	    	
			int numTokens = nome.toString().split(" ").length;
	        
			return (numTokens >= numMinimo);
			
    	} else {
    		return true;
    	}
	}

}
