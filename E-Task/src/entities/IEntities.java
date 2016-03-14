package entities;

public interface IEntities<Key>{
	
	public String imprimir();	
	public void setId(Key count);
	public Key getId();	
}
