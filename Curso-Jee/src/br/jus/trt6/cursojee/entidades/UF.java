package br.jus.trt6.cursojee.entidades;
import javax.persistence.*;
import java.util.*;
import org.hibernate.validator.*;

@Entity
@Table(name="TB_UF")
public class UF {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	
	@NotNull
	@Length(max=2, min=2)
	@Column(name="SIGLA", length=2)
	private String sigla;
	
	
	@OneToMany(mappedBy="uf", fetch=FetchType.LAZY) //Caso nao faça este mapeamento o programa funciona normalmente
	List<Cidade> cidades;
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public List<Cidade> getCidades() {
		return cidades;
	}
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	
	
				
}
