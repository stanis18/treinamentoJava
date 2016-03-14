package reflextion;

public class RentCar {
	
	private Pessoa pessoa;
	private String nome;
	private Integer preco;
	
	//contrutor vazio e com quatro elementos
	public RentCar(){
		
	}
	
	public RentCar(Pessoa pessoa, String nome, Integer preco){
		
		setPessoa(pessoa);
		setNome(nome);
		setPreco(preco);	
	}
	
	public void validarDias(Integer dias) throws Excecao {
		
		if(dias <= 0){
			throw new Excecao("Os dias nao podem ser negativos");
		}
	}
	
	public Integer calcularPreco(Integer dias){
		
		try {
			
			validarDias(dias);			
		} catch (Excecao e) {
	
			e.printStackTrace();
		}
		
		return getPreco()*dias;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getPreco() {
		return preco;
	}
	public void setPreco(Integer preco) {
		this.preco = preco;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
