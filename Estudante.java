package filaRecreio;

import java.util.GregorianCalendar;

public class Estudante {

	private String nome, sexo;
	private int senha, tempo, pedido1, pedido2;
	private double nota;
	private GregorianCalendar horaCadastro;
	private long counter;
	private long segundosCorridos;
	
	public Estudante(String nome, String sexo, int pedido1, int pedido2, double nota) {
		this.nome = nome;
		this.sexo = sexo;
		this.pedido1 = pedido1;
		this.pedido2 = pedido2;
		this.nota = nota;
		this.segundosCorridos = 0;
		this.horaCadastro = new GregorianCalendar();
	}
	
	public void atualizar() {
		long tempoEnfileiramento = horaCadastro.getTimeInMillis();
		long tempoAtual = new GregorianCalendar().getTimeInMillis() - counter;
		segundosCorridos = (tempoAtual - tempoEnfileiramento) / 1000;

		if (segundosCorridos >= 60) {
			do {
				segundosCorridos = -60;
				tempo--;
				counter += 60000;
			} while (segundosCorridos >= 60);
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public int getPedido1() {
		return pedido1;
	}
	
	public void setPedido1(int pedido1) {
		this.pedido1 = pedido1;
	}
	
	public int getPedido2() {
		return pedido2;
	}
	
	public void setPedido2(int pedido2) {
		this.pedido2 = pedido2;
	}
	
	public double getNota() {
		return nota;
	}
	
	public void setNota(double nota) {
		this.nota = nota;
	}
	
	public int getSenha() {
		return senha;
	}
	
	public void setSenha(int senha) {
		this.senha = senha;
	}
	
	public int tempo() {
		return tempo;
	}
	
	public GregorianCalendar getHoraCadastro() {
		return horaCadastro;
	}
	
	public long getSegundosCorridos() {
		return segundosCorridos;
	}
	
}
