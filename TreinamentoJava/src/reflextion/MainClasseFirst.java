package reflextion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MainClasseFirst {

	public static void main(String[] args) {
		
		//captura a classe.
		Class<?> classe =  new Pessoa().getClass();
		//cria um objeto do tipo pessoa.
		Pessoa pessoa = new Pessoa();
		
		try {
			
			//usa a instancia da classe capturada para encontrar o metodo
			//passando como parametro a assinatura do metodo.
			Method metodo0 = classe.getMethod("completarNome", new Class[]{});
			//chama o metodo passando o objeto e os respectivos parametros.
			// nesse casso o parametro foi um array de objetos vazios.
			metodo0.invoke(pessoa, new Object[]{});
			
			//recuperando um metodo com um parametro do tipo Double 		
			Method metodo1 = classe.getMethod("multiplicarSalario", new Class[]{Double.class});
			//chamando o metodo passando o paramentro necessario
			metodo1.invoke(pessoa, new Object[]{5.0});
			

		} 
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		} 
		catch (SecurityException e) {
			e.printStackTrace();
		}		
		catch (IllegalAccessException e) {		
			e.printStackTrace();
		} 
		catch (IllegalArgumentException e) {		
			e.printStackTrace();
		} 
		catch (InvocationTargetException e) {	
			e.printStackTrace();
		}
	}
}



