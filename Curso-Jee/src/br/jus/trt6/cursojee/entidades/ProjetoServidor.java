package br.jus.trt6.cursojee.entidades;
import javax.persistence.*;

@Entity
@Table(name="TB_PROJETO_SERVIDOR")
public class ProjetoServidor {

	@EmbeddedId
	private ProjetoServidorPK id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PROJETO", insertable=false, updatable=false)
	private Projeto projeto;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_SERVIDOR", insertable=false, updatable=false)
	private Servidor servidor;
								
	
	public ProjetoServidor(Projeto projeto, Servidor servidor) {
			
		this();
		setProjeto(projeto);
		setServidor(servidor);
		
	}

	public ProjetoServidor() {
		this.id = new ProjetoServidorPK();
	}
	
	public ProjetoServidorPK getId() {
		return id;
	}

	public void setId(ProjetoServidorPK id) {
		this.id = id;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {	
		getId().setPk_projeto(projeto.getId());
		this.projeto = projeto;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {		
		getId().setPk_servidor(servidor.getId());
		this.servidor = servidor;
		
	}
}

