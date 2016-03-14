package modulo04_OO;

public class ContaCorrente {

	private Agencia agencia;
	private Cliente cliente;
	private String numeroCC;
	private double saldo;
	private double limiteCheque;
	
	
	
	//Construtor
	
	public ContaCorrente ( Agencia agencia, Cliente cliente, String numeroCC, double saldo){
			
		this.agencia = agencia;
		this.cliente = cliente;
		this.numeroCC = numeroCC;
		this.saldo = saldo;
		this.limiteCheque =  limiteCheque;
	}
	
	public double getLimiteCheque() {
		return limiteCheque;
	}
	public void setLimiteCheque(double limiteCheque) {
		this.limiteCheque = limiteCheque;
	}
	public Agencia getAgencia() {
		return agencia;
	}
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getNumeroCC() {
		return numeroCC;
	}
	public void setNumeroCC(String numeroCC) {
		this.numeroCC = numeroCC;
	}
	
	public double getSalo() {
		return saldo;
	}
	public void setSalo(double saldo) {
		this.saldo = saldo;
	}
	
	public void creditarConta (double saldo ){
		
		this.saldo = this.saldo + saldo;
	}
}
