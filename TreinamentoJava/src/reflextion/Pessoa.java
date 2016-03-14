package reflextion;

public class Pessoa {
	
	private String nome;
	private String sobrenome;
	private String cpf;
	private Integer idade;
	private Double salario;
	
	public Pessoa() {
	}
	
	public Pessoa(String nome, String sobrenome, String cpf, Integer idade, Double salario){
		
		setNome(nome);
		setSobrenome(sobrenome);
		setCpf(cpf);
		setIdade(idade);
		setSalario(salario);
	}
	
	
	@Override
	public String toString() {	
		
		return getNome();
	}
	
	
	public void multiplicarSalario(Double x){
		
		salario = salario*x;
		System.out.println(salario);
	}
	
	public String retornarNomeCompleto(){
		
		return getNome() + " " + getSobrenome();
	}
		
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
}
