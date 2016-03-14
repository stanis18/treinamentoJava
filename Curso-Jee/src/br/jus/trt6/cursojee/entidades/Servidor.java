package br.jus.trt6.cursojee.entidades;
import javax.persistence.*;
import org.hibernate.validator.*;
import java.util.*;

@Entity
@PrimaryKeyJoinColumn(name="ID")
@Table(name="TB_SERVIDOR")
@NamedQueries({
		
	@NamedQuery(
			name = "Servidor.queryTodosServidores",
			query= "select s from Servidor s"
			),
	@NamedQuery(
			name = "Servidor.queryUmServidor",
			query= "select s from Servidor s where s.cpf = :cpf and s.dataNascimento >= :dataNascimento and s.email like :email and " +
					"s.matricula = :matricula and s.nome like :nome and s.situacao = :situacao"
			),
	@NamedQuery(
			name = "Servidor.queryComProjetoMinimo",
			query= "select s from Servidor s where s.cpf = :cpf and size(s.projetos) >= :numProjetos"
			),
	@NamedQuery(
			name="Servidor.queryComDependentes",
			query= "select s from Servidor s where s.cpf = :cpf and s.dependentes is not empty"
			),
	@NamedQuery(
			name = "Servidor.queryListarRelatorio",
			query = "select new br.jus.trt6.cursojee.entidades.Servidor ( s.id, s.nome, s.email, s.cidade.nome, "
					+ " s.cidade.uf.sigla ) from Servidor s "		
			)	
})

public class Servidor extends Pessoa{

	@NotNull
	@Length(max=10)
	@Column(name="MATRICULA", nullable=false, length=10)
	private String matricula;	
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="SITUACAO", length=1)
	private Situacao situacao;


	@OneToMany(mappedBy="servidor", fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List <Dependente> dependentes;

	@OneToMany(mappedBy="servidor", fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<ProjetoServidor> projetos;

				
	public Servidor(Integer id, String nome, String email,
			String cidade, String uf) {
		super();
				
		setId(id);
		setNome(nome);
		setEmail(email);
			
		setIdCidade(new Cidade());
		getIdCidade().setNome(cidade);
		getIdCidade().setuf(new UF());
		getIdCidade().getuf().setSigla(uf);
	}
	
					
	public Servidor() {
		super();
	}

	public List<ProjetoServidor> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<ProjetoServidor> projetos) {
		this.projetos = projetos;
	}

	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
	}

	public  String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public enum Situacao{

		A("Ativo"), P("Aposentado"), E("Exonerado") ;

		private final String situacao;

		private Situacao(String situacao){			
			this.situacao = situacao;
		}

		public String getSituacao(){
			return this.situacao;
		}

	}		
}


