package br.jus.trt6.cursojee.entidades;
import java.util.*;
import javax.persistence.*;
import org.hibernate.validator.*;

@Entity
@PrimaryKeyJoinColumn(name="ID")
@Table(name="TB_DEPENDENTE")

public class Dependente extends Pessoa{
		
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_SERVIDOR", nullable=false) // ID_SERVIDOR é o nome da coluna da tabela dependente que desejamos
	private Servidor servidor;						// fazer a operação Join.. 
		
	@Column(name="DT_INICIO", nullable=false)
	@Temporal (TemporalType.DATE)
	private Date dataInicio;
	
	
	public Servidor getId_servidor() {
		return servidor;
	}

	public void setId_servidor(Servidor servidor) {
		this.servidor = servidor;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}			
}
