package filaRecreio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class FilaRecreio {

	private double tempoEspera;
	PriorityQueue<Estudante> fila = new PriorityQueue<Estudante>(new EstudanteComparator());
	PriorityQueue<Estudante> atendidos = new PriorityQueue<Estudante>(new EstudanteComparator());
	private final String mensagemErro = "A fila está vazia!";

	public FilaRecreio() {
	}

	public void cadastrar(Estudante e) {
		boolean cad = true;

		Iterator<Estudante> it = fila.iterator();

		while (it.hasNext()) {
			if (it.next().getNome().equalsIgnoreCase(e.getNome())) {
				cad = false;
				System.err.println("O aluno já foi cadastrado!");
				return;
			}
		}

		fila.add(e);
		System.out.println("Aluno cadastrado na fila!");
	}

	public String chamarProximo() {
		String resultado = "";

		if (fila.isEmpty()) {
			resultado = mensagemErro;
		} else {
			fila.peek().atualizar();
			Estudante estudanteTemp = fila.poll();
			if (estudanteTemp != null) {
				resultado += "Senha: " + estudanteTemp.getSenha();
				resultado += "Nome: " + estudanteTemp.getNome();
				resultado += "Tempo de Espera: " + estudanteTemp.getSegundosCorridos();
				tempoEspera += estudanteTemp.getSegundosCorridos();
				atendidos.add(estudanteTemp);
			}
		}
		return resultado;
	}

	public int qtdRestantes() {
		return fila.size();
	}

	public String qtdEstudantesSexo() {
		int qtdHomem = 0;
		int qtdMulher = 0;
		int qtdOutro = 0;

		String resultado = "";

		if (fila.isEmpty()) {
			resultado = mensagemErro;
		} else {

			Iterator<Estudante> it = fila.iterator();

			while (it.hasNext()) {
				Estudante atual = it.next();
				String sexo = atual.getSexo();

				if (sexo.equalsIgnoreCase("H")) {
					qtdHomem++;
				} else if (sexo.equalsIgnoreCase("M")) {
					qtdMulher++;
				} else {
					qtdOutro++;
				}
			}

			resultado += ("Percentual de Homens ainda na fila: " + (qtdHomem * 100) / fila.size() + "%"
					+ "\nPercentual de Mulheres ainda na fila: " + (qtdMulher * 100) / fila.size() + "%"
					+ "\nPercentual de 'Outros' ainda na fila: " + (qtdOutro * 100) / fila.size()) + "%";
		}
		return resultado;
	}

	public String tempoMedio() {
		String resultado = "";

		if (atendidos.size() == 0) {
			resultado = "Nenhum aluno atendido ainda.";
		} else {
			resultado = "Tempo médio de espera: " + ((double) tempoEspera / atendidos.size());
		}
		return resultado;
	}

	public void atualizar() {
		Iterator<Estudante> it = fila.iterator();

		while (it.hasNext()) {
			it.next().atualizar();
		}

		List<Estudante> listaTemp = new ArrayList<Estudante>(fila);
		Collections.sort(listaTemp, new EstudanteComparator());
		fila.clear();

		while (listaTemp.size() > 0) {
			fila.add(listaTemp.remove(0));
		}
	}

	public String imprimirFila() {
		String resultado = "";

		if (fila.isEmpty()) {
			resultado = mensagemErro;
		} else {
			PriorityQueue<Estudante> fila2 = new PriorityQueue<Estudante>(new EstudanteComparator());

			while (!fila.isEmpty()) {
				resultado += "\nEstudante: " + fila.peek().getNome();

				fila2.add(fila.poll());
			}

			fila = fila2;
		}

		return resultado;
	}

	public String verificarProx() {
		String resultado = "";

		if (fila.isEmpty()) {
			resultado = mensagemErro;
		} else {
			Estudante estudanteTemp = fila.peek();
			if (estudanteTemp != null) {
				estudanteTemp.atualizar();

				resultado += ("Próximo estudante da fila: ") + "\nNome: " + estudanteTemp.getNome() + "\nSexo: "
						+ estudanteTemp.getSexo() + "\nNota: " + estudanteTemp.getNota() + "\nPedido 1: "
						+ Pedido.traduzirPedido(estudanteTemp.getPedido1()) + "\nPedido 2: "
						+ Pedido.traduzirPedido(estudanteTemp.getPedido2()) + "\nTempo de permanência na fila: "
						+ estudanteTemp.getSegundosCorridos();
			}
		}
		return resultado;
	}

	public String lanchesServidos() {
		String resultado = "";
		int[] pedidosServidos = new int[Pedido.TOTAL_LANCHES];
		
		PriorityQueue<Estudante> fila2 = new PriorityQueue<Estudante>(new EstudanteComparator());

		while (!atendidos.isEmpty()) {
			pedidosServidos[atendidos.peek().getPedido1() - 1]++; 
			pedidosServidos[atendidos.peek().getPedido2() - 1]++; 
			fila2.add(atendidos.poll());
		}

		atendidos = fila2;
		
		resultado += ("Quantidade de Mistos: " + pedidosServidos[Pedido.MISTO]) +
		("\nQuantidade de Cachorros-Quentes: " + pedidosServidos[Pedido.CACHORRO_QUENTE]) +
		("\nQuantidade de Pastéis: " + pedidosServidos[Pedido.PASTEL]) +
		("\nQuantidade de Coxinhas: " + pedidosServidos[Pedido.COXINHA]) +
		("\nQuantidade de Sucos: " + pedidosServidos[Pedido.SUCO]) +
		("\nQuantidade de Refrigerantes: " + pedidosServidos[Pedido.REFRIGERANTE]);
		
		return resultado;
	}
	
	public String lanchesNaoServidos() {
		int totalPedidos = 0;
		String resultado = "";
		int[] pedidosNaoServidos = new int[Pedido.TOTAL_LANCHES];
		
		PriorityQueue<Estudante> fila2 = new PriorityQueue<Estudante>(new EstudanteComparator());

		while (!fila.isEmpty()) {
			pedidosNaoServidos[fila.peek().getPedido1() - 1]++; 
			pedidosNaoServidos[fila.peek().getPedido2() - 1]++; 
			
			if(fila.peek().getPedido2() != 0) {
				totalPedidos += 2;
			} else {
				totalPedidos++;
			}
			
			fila2.add(fila.poll());
		}

		fila = fila2;
		
		resultado += ("Quantidade de Mistos: " + (pedidosNaoServidos[Pedido.MISTO] * 100) / totalPedidos) + "%" +
		("\nQuantidade de Cachorros-Quentes: " + (pedidosNaoServidos[Pedido.CACHORRO_QUENTE] * 100) / totalPedidos) + "%" +
		("\nQuantidade de Pastéis: " + (pedidosNaoServidos[Pedido.PASTEL] * 100) / totalPedidos) + "%" +
		("\nQuantidade de Coxinhas: " + (pedidosNaoServidos[Pedido.COXINHA] * 100) / totalPedidos) + "%" +
		("\nQuantidade de Sucos: " + (pedidosNaoServidos[Pedido.SUCO] * 100) / totalPedidos) + "%" +
		("\nQuantidade de Refrigerantes: " + (pedidosNaoServidos[Pedido.REFRIGERANTE] * 100) / totalPedidos) + "%";
		
		return resultado;
	}
}
