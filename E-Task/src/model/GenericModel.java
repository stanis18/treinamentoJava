package model;
import entities.*;

import java.util.*;

public class GenericModel<T extends IEntities<Key>, Key > {
	
	protected HashMap<Key, T> repositorio = new HashMap<Key, T>();
		
	public void cadastrar(T entidade) {
		
		repositorio.put(entidade.getId(), entidade);
						
	}
	
	public void atualizar(T entidade){
								
		repositorio.put(entidade.getId(), entidade);
		
	}

	public void excluir(Key id){
		
		repositorio.remove(id);
	}

	public T consultar(Key id){
						
		return repositorio.get(id);
	}
	
	public List<T> listar(){

		ArrayList<T> lista = new ArrayList<T>(repositorio.values());

		return lista;
	}
		
}
