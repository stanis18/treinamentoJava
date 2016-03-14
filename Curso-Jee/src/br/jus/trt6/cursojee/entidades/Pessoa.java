package br.jus.trt6.cursojee.entidades;
import java.util.*;

import javax.persistence.*;

import org.hibernate.validator.*;

import br.jus.trt6.cursojee.entidades.validator.*;

@Entity
@Table(name= "TB_Pessoa")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Pessoa {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@NotNull
	@Length(min=5, max=100)
	@ValidarTonkens(minimo=2)
	@Column(name= "NOME", nullable = false, length=100)	
	private String nome;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_Cidade")
	private Cidade cidade;
	
	@Column(name="DT_NASCIMENTO", nullable=true)
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Column(name="CPF", length=11, nullable=true)
	@Length(min=11, max=11)
	@ValidarCPF
	private String cpf;
	
	@Column(name="EMAIL", length=30, nullable=true)
	@Email
	private String email;
		
	
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
	public Cidade getIdCidade() {
		return cidade;
	}
	public void setIdCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
		
}
