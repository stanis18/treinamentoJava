package br.jus.trt6.cursojee.entidades.validator;

import java.lang.annotation.*;

import org.hibernate.validator.*;

@Documented
@Target(value={ElementType.METHOD,ElementType.FIELD})
@Retention(value=RetentionPolicy.RUNTIME)
@ValidatorClass(value= ValidarCPFImpl.class)
public @interface ValidarCPF {
	
	String message() default "CPF inválido"; 
		
}
