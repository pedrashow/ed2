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
		testeAVL.put(40,40);
		testeAVL.mostra();
		testeAVL.put(30,30);
		testeAVL.mostra();
		testeAVL.put(50,50);
		testeAVL.mostra();
		testeAVL.put(20,20);
		testeAVL.mostra();
		testeAVL.put(60,60);
		testeAVL.mostra();
		testeAVL.put(70,70);
		testeAVL.mostra();
		testeAVL.put(80,80);
		testeAVL.mostra();

	}
}
