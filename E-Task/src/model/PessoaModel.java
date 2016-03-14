package model;
import entities.*;

public class PessoaModel  extends GenericModel<Pessoa, Integer>{
	
	private static Integer count = 0;
							
	public void cadastrar(Pessoa entidade) {
						
		count++;
		entidade.setId(count);				
		super.cadastrar(entidade);
				
		
	}

}
