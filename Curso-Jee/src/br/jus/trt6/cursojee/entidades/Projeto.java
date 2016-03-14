package br.jus.trt6.cursojee.entidades;
import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.hibernate.validator.*;

import java.util.*;

@Entity
@Table(name="TB_PROJETO")
public class Projeto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@NotNull
	@Size(min=5, max=100)
	@Column(name="NOME", length=100, nullable=false)
	private String nome;
	
	@NotNull
	@Length(min=3, max=10)
	@Column(name="SIGLA", length=100, nullable=false)
	private String sigla;
	
	public Projeto(String nome, String sigla, boolean concluido) {
		super();
		this.nome = nome;
		this.sigla = sigla;
		this.concluido = concluido;
	}
			
	public Projeto() {
		super();
	}

	@Column(name="CONCLUIDO")			//Este campo foi modificado de boolean para char porque no banco de dados 
	@Type(type="yes_no")
	private boolean concluido;			// é armazenado como F ou V e o valor de uma variavel booleana é true ou false
		
	@OneToMany(mappedBy="projeto", fetch=FetchType.LAZY)	// Esse tipo de relacionamento também pode ser implementado 
	private List <ProjetoServidor> servidores;				//utilizando a notação OnetoMany.. vai depender da necessidade do programador
		
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
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public boolean getStatus() {
		return concluido;
	}
	public void setStatus(boolean concluido) {
		this.concluido = concluido;
	}
	public boolean isConcluido() {
		return concluido;
	}
	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}
	
	public List<ProjetoServidor> getServidores() {
		return servidores;
	}
	public void setServidores(List<ProjetoServidor> servidores) {
		this.servidores = servidores;
	}
	
}

