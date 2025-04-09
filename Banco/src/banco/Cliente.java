package banco;

public class Cliente {
	
	String nome;
	String cpf;
	ContaBancaria contaBancaria;
	
	public Cliente(String nome, String cpf, ContaBancaria contaBancaria) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.contaBancaria = contaBancaria;
	}
	
	

}
