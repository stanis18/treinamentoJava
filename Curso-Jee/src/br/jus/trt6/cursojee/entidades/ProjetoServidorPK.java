package br.jus.trt6.cursojee.entidades;
import java.io.*;
import javax.persistence.*;

@SuppressWarnings("serial")
@Embeddable
public class ProjetoServidorPK implements Serializable{

	@Column(name="ID_PROJETO", nullable=false)
	private Integer pk_projeto;
				
	@Column(name="ID_SERVIDOR", nullable=false)
	private Integer pk_servidor;

	public Integer getPk_projeto() {
		return pk_projeto;
	}
	public void setPk_projeto(Integer pk_projeto) {
		this.pk_projeto = pk_projeto;
	}
	public Integer getPk_servidor() {
		return pk_servidor;
	}
	public void setPk_servidor(Integer pk_servidor) {
		this.pk_servidor = pk_servidor;
	}	
	
}
