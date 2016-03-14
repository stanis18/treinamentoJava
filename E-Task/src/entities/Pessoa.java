package entities;

public class Pessoa implements IEntities<Integer>{
	
	public enum PERFIL {ADMIN, USUARIO};	
	private String nome;
	private PERFIL perfil;
	private Integer matricula;
	
	public Pessoa(){
		
	}
				
	public Pessoa(String nome, PERFIL perfil) {
		super();
		this.nome = nome;
		this.perfil = perfil;
		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public PERFIL getPerfil() {
		return perfil;
	}
	
	public void setPerfil(PERFIL perfil) {
		this.perfil = perfil;
	}
	
	
	public void setId(Integer matricula) {
		this.matricula = matricula;
	}

	@Override
	public String imprimir() {
		
		String aux = getNome() + " "+ getId() + " " + getPerfil();
				
		return aux;
	}

	@Override
	public Integer getId() {
		
		return matricula;
	}
	
			
}
