package reflextion;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class MainClassSecond {
		
	public static void main(String[] args) {
				
		//capturando as classes. 
		Class<?> pessoaClasse = Pessoa.class;
		Class<?> rentCarClasse = RentCar.class;
		Class<?> rentHouseClasse = RentHouse.class;
							
		//capturando os construtores das classes.
		Constructor<?>[] contrutoresPessoa = pessoaClasse.getConstructors();
		Constructor<?>[] contrutoresRentCar = rentCarClasse.getConstructors();
		
		//imprimindo os construtores. 
		System.out.println("Os contrutores da classe Pessoa são: " + Arrays.toString(contrutoresPessoa));
		System.out.println("Os contrutores da classe rentCar são: " + Arrays.toString(contrutoresRentCar));
		
		try {
			//capturando construtores individualmente da classe pessoa. 
			Constructor<?> contrutuorPessoaVazio = pessoaClasse.getConstructor();
			System.out.println(contrutuorPessoaVazio.toString());
			
			Constructor<?> constructorPessoaArgumentos = pessoaClasse.getConstructor(String.class, String.class, String.class, Integer.class, Double.class);
			System.out.println(constructorPessoaArgumentos.toString());
			
			//capturando contrutores individualmente da classe rentCar.
			Constructor<?> construtorRentCarVazio = rentCarClasse.getConstructor();
			System.out.println(construtorRentCarVazio.toString());
			
			Constructor<?> construtorRentCarArgumentos = rentCarClasse.getConstructor(Pessoa.class, String.class, Integer.class);
			System.out.println(construtorRentCarArgumentos.toString());
			
			Constructor<?> construtorRentHouseArgumentos = rentHouseClasse.getConstructor(Pessoa.class, String.class, Integer.class);
			
			//criando objetos das classes utilizando contrutores vazio e com argumentos e imprimindo a saida.  	
			Pessoa pessoaVazio = (Pessoa)contrutuorPessoaVazio.newInstance();
			System.out.println(pessoaVazio.getNome());
			
			Pessoa pessoaArgumentos = (Pessoa)constructorPessoaArgumentos.newInstance("Juliandson", "Estanislau", "07092521459", 26, 956.0);
			System.out.println(pessoaArgumentos.getNome());
			
			RentCar rentCarVazio = (RentCar)construtorRentCarVazio.newInstance();
			System.out.println(rentCarVazio.getNome());
			
			RentCar rentCarArgumentos = (RentCar)construtorRentCarArgumentos.newInstance(pessoaArgumentos,"Gol", 400);
			System.out.println(rentCarArgumentos.getNome());
			
			RentHouse rentHouseArgumentos = (RentHouse) construtorRentHouseArgumentos.newInstance(pessoaArgumentos,"Casa de Praia", 1300); 
			System.out.println(rentHouseArgumentos.getNome());
			
			//fazendo chamadas dos metodos sem retorno. 
			Method metodoPessoa = pessoaClasse.getDeclaredMethod("multiplicarSalario", new Class[]{Double.class});			
			metodoPessoa.invoke(pessoaArgumentos, new Object[]{3.0});
			
			//fazendo chamadas dos metodos com retorno. Obs: o metodo retorna uma variavael do tipo o Objeto, devemos sempre realizar o cast.
			metodoPessoa = pessoaClasse.getDeclaredMethod("retornarNomeCompleto", new Class[]{});
			String nomeCompleto = (String) metodoPessoa.invoke(pessoaArgumentos, new Object[]{});
			System.out.println(nomeCompleto);
			
			// neste caso os metodos tem o mesmo nome porem estão em classes diferentes.
			Method metodoRent = rentCarClasse.getDeclaredMethod("calcularPreco", new Class[]{Integer.class});
			Integer preco = (Integer) metodoRent.invoke(rentCarArgumentos, new Object[]{5});
			System.out.println("O preco do aluguel do carro é: " + preco);
			
			metodoRent = rentHouseClasse.getDeclaredMethod("calcularPreco", new Class[]{Integer.class});
			preco = (Integer) metodoRent.invoke(rentHouseArgumentos, new Object[]{5});
			System.out.println("O preco do aluguel da casa é: " + preco);
						
			System.out.println("O tipo de retorno do metodo calcularPreco é: " + " " + metodoRent.getReturnType());
					
			Field[] pessoaFields = pessoaClasse.getDeclaredFields();
			//Provavelmente o metodo toString da classe Field foi sobreescrito para imprimir a saida mostrada.
									
			
			for(Field field: pessoaFields){
								
				if(Modifier.isPrivate(field.getModifiers())){				
					field.setAccessible(true);
				}
						
				System.out.println("Nome do field: " + field.getName()+ " Tipo do field: " + field.getType() + " O valor do campo é "+field.get(pessoaArgumentos));
				System.out.println(Modifier.toString(field.getModifiers()));
			}
			

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
		catch (InstantiationException e) {			
			e.printStackTrace();
		}
	}
}
