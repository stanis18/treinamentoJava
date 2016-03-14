package modulo06;

public class Livro {

	private String edicao;
	private String titulo;
				
	public Livro(String titulo, String edicao) {
	
		this.edicao = edicao;
		this.titulo = titulo;
	}
	
	public String getEdicao() {
		return edicao;
	}
	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}






}
