package br.jus.trt6.cursojee.entidades.validator;

import java.lang.annotation.*;

import org.hibernate.validator.*;

@Documented
@Target(value={ElementType.METHOD,ElementType.FIELD})
@Retention(value=RetentionPolicy.RUNTIME)
@ValidatorClass(value=ValidarTokensImpl.class)
public @interface ValidarTonkens {
	
	String message() default "N�mero m�nimo de tokens";
	int minimo();		
	
}
