package arvore;

import java.util.Scanner;

public class Teste {
	public static void main(String[] args) {
		ArvoreAVL<Integer,Integer> testeAVL = new ArvoreAVL<>();
		Scanner in = new Scanner(System.in);
		int entrada;
		do {
			entrada = in.nextInt();
			if (entrada > 0)
				testeAVL.put(entrada, entrada);
			else if (entrada < 0)
				testeAVL.delete(-entrada);
			testeAVL.mostra();
		} while (entrada != 0);
		in.close();
	}
}
