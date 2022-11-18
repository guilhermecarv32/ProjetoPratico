package filaRecreio;

import java.util.Scanner;

public class FilaUtil {

	public static void main(String[] args) {
		FilaRecreio cantina = new FilaRecreio();
		Scanner scanner = new Scanner(System.in);
		int op = 0;

		do {
			System.out.println("Qual operação deseja realizar(1-9): ");
			System.out.println();
			System.out.println("********************************MENU******************************** ");
			System.out.println("** 1 - Adicionar estudante à fila                                 ** ");
			System.out.println("** 2 - Chamar próximo estudante da fila                           ** ");
			System.out.println("** 3 - Verificar próximo estudante a ser chamado                  ** ");
			System.out.println("** 4 - Estudantes ainda na fila                                   ** ");
			System.out.println("** 5 - Percentual de estudantes por sexo ainda na fila            ** ");
			System.out.println("** 6 - Tempo médio de permanência na fila                         ** ");
			System.out.println("** 7 - Quantidade de pedidos atendidos por opção de cardápio      ** ");
			System.out.println("** 8 - Percentual de pedidos não atendidos por opção de cardápio  ** ");
			System.out.println("** 9 - Encerrar                                                   ** ");
			System.out.println("******************************************************************** ");
			op = scanner.nextInt();
			scanner.nextLine();

			switch(op) {
			case 1:	

				System.out.println("Digite o nome do estudante: ");
				String nome = scanner.nextLine();
				System.out.println("Qual o sexo do estudante? (H/M/Outro) ");
				String sexo = scanner.nextLine();

				System.out.println("Escolha um dos seguintes pedidos: ");
				imprimirCardapio();
				int pedido1 = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Deseja mais alguma coisa? (S/N)");
				String resposta = scanner.nextLine();
				int pedido2 = 0;

				System.out.println("Informe o outro pedido.");
				if (resposta.equalsIgnoreCase("S")) {
					pedido2 = scanner.nextInt();
				}

				System.out.println("Qual a nota do aluno? ");
				double nota = scanner.nextDouble();

				cantina.cadastrar(new Estudante(nome, sexo, pedido1, pedido2, nota));
				scanner.nextLine();
				
				break;

			case 2:
				System.out.println(cantina.chamarProximo());

				break;

			case 3:
				System.out.println(cantina.verificarProx());

				break;

			case 4:
				System.out.println(cantina.imprimirFila());

				break;

			case 5:
				System.out.println(cantina.qtdEstudantesSexo());

				break;

			case 6:
				System.out.println(cantina.tempoMedio());

				break;

			case 7:
				System.out.println(cantina.lanchesServidos());

				break;
				
			case 8: 
				System.out.println(cantina.lanchesNaoServidos());
				
				break;
			}

		}while (op != 9);

	}


	private static void imprimirCardapio() {
		System.out.println("*********CARDÁPIO********* ");
		System.out.println("** 1 - Misto            ** ");
		System.out.println("** 2 - Cachorro-Quente  ** ");
		System.out.println("** 3 - Pastel           ** ");
		System.out.println("** 4 - Coxinha          ** ");
		System.out.println("** 5 - Suco             ** ");
		System.out.println("** 6 - Refrigerante     ** ");
		System.out.println("************************** ");
	}
}
