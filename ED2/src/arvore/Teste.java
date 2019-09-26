package arvore;

public class Teste {
	public static void main(String[] args) {
		/*
		ArvBinBusca<Integer,Integer> teste = new ArvBinBusca<>();
		Integer[] arrayOrdenado = new Integer[15];
		for (int i = 0; i < arrayOrdenado.length; i++)
			arrayOrdenado[i] = i;
		teste.geraArvBal(arrayOrdenado);
		teste.mostra();
		*/
		ArvAVL<Integer,Integer> testeAVL = new ArvAVL<>();
		int[] sequencia = new int[19];
		sequencia[0] = 40;
		sequencia[1] = 30;
		sequencia[2] = 20;
		sequencia[3] = 25;
		sequencia[4] = 28;
		sequencia[5] = 50;
		sequencia[6] = 60;
		sequencia[7] = 80;
		sequencia[8] = 70;
		sequencia[9] = 65;
		sequencia[10] = 62;
		sequencia[11] = 61;
		sequencia[12] = 100;
		sequencia[13] = 110;
		sequencia[14] = 145;
		sequencia[15] = 125;
		sequencia[16] = 200;
		sequencia[17] = 0;
		sequencia[18] = -6;
		for (int i = 0; i <sequencia.length; i++) {
			testeAVL.put(sequencia[i],sequencia[i]);
			System.out.println(sequencia[i]);
			testeAVL.mostra();
		}

	}
}
