package filaRecreio;

import java.util.Comparator;

public class EstudanteComparator implements Comparator<Estudante> {

	@Override
	public int compare(Estudante e1, Estudante e2) {
		double notaE1 = e1.getNota();
		double notaE2 = e2.getNota();

		if (notaE1 > notaE2) {
			return -1;
		} else if (notaE2 < notaE1) {
			return +1;
		} else {
			return 0;
		}
	}

}
