package br.jus.trt6.cursojee.entidades;

import javax.persistence.*;
import org.hibernate.validator.*;

@Entity
@Table(name="TB_CIDADE")
@NamedQuery(name = "Cidade.queryCidadePorUf",
query = "select c from Cidade c where c.uf.id = :ufId")
public class Cidade {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
		
	@NotNull
	@Size(min=5, max=50)
	@Column(name="NOME", length=50, nullable=false)
	private String nome;
		
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_UF", nullable = false) //o parametro da variavel nome deve ser diferente
	private  UF uf;							//dos parametros que ja existem na classe.
	
		
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public UF getuf() {
		return uf;
	}
	
	public void setuf(UF id_uf) {
		this.uf = id_uf;
	}
			
}
