package br.jus.trt6.cursojee.entidades.validator;

import org.hibernate.validator.*;
import br.jus.trt6.cursojee.util.*;
import java.io.*;

@SuppressWarnings("serial")
public class ValidarCPFImpl implements Validator<ValidarCPF>, Serializable {

	@Override
	public void initialize(ValidarCPF arg0) {
				
	}

	@Override
	public boolean isValid(Object objeto) {
						
  	String cpfString = objeto != null ? objeto.toString() : null; 
    	
        if (cpfString != null && !Util.isStringEmpty((cpfString))) {
            
			if (!Util.isCPFValido(cpfString)) {
                return false;
            }
        }
        return true;
    }			

}
