package arvore;

import java.util.NoSuchElementException;

public class ArvBinBusca<Chave extends Comparable<Chave>, Valor> {
	private No raiz;

	private class No {
		private Chave chave;
		private Valor valor;
		private No esq, dir;

		public No(Chave chave, Valor valor) {
			this.chave = chave;
			this.valor = valor;
			this.esq = null;
			this.dir = null;
		}

		public No(Chave chave, Valor valor, No esq, No dir) {
			this.chave = chave;
			this.valor = valor;
			this.esq = esq;
			this.dir = dir;
		}
	}

	public ArvBinBusca() {
		raiz = null;
	}

	public boolean vazia() {
		return (raiz == null);
	}

	public void mostra() {
		mostra(raiz);
	}

	private void mostra(No x) {
		if (x == null)
			return;
		System.out.print("[");
		mostra(x.esq);
		System.out.print("<" + x.chave + ">");
		mostra(x.dir);
		System.out.print("]");
	}

	public Chave min() {
		if (vazia())
			throw new NoSuchElementException("Árvore está vazia!");
		return min(raiz).chave;
	}

	private No min(No x) {
		if (x.esq == null)
			return x;
		else
			return min(x.esq);
	}

	public Chave max() {
		if (vazia())
			throw new NoSuchElementException("A árvore está vazia!");
		return max(raiz).chave;
	}

	private No max(No x) {
		if (x.dir == null)
			return x;
		else
			return max(x.dir);
	}

	public int tamanho() {
		return tamanho(raiz);
	}

	private int tamanho(No x) {
		if (x == null)
			return 0;
		return 1 + tamanho(x.esq) + tamanho(x.dir);
	}

	public boolean contem(Chave chave) {
		if (chave == null)
			throw new IllegalArgumentException("A chave fornecida é null!");
		return get(chave) != null;
	}

	public Valor get(Chave chave) {
		return get(raiz, chave);
	}

	private Valor get(No x, Chave chave) {
		if (chave == null)
			throw new IllegalArgumentException("A chave fornecida é null!");
		if (x == null)
			return null;
		int cmp = chave.compareTo(x.chave);
		if (cmp < 0)
			return get(x.esq, chave);
		else if (cmp > 0)
			return get(x.dir, chave);
		else
			return x.valor;
	}

	public void put(Chave chave, Valor valor) {
		if (chave == null)
			throw new IllegalArgumentException("A chave fornecida é null!");
		if (valor == null) {
			delete(chave);
			return;
		}
		raiz = put(raiz, chave, valor);
	}

	private No put(No x, Chave chave, Valor valor) {
		if (x == null)
			return new No(chave, valor);
		int cmp = chave.compareTo(x.chave);
		if (cmp < 0)
			x.esq = put(x.esq, chave, valor);
		else if (cmp > 0)
			x.dir = put(x.dir, chave, valor);
		else
			x.valor = valor;
		return x;
	}

	public void deleteMin() {
		if (vazia())
			throw new NoSuchElementException("A árvore está vazia!");
		raiz = deleteMin(raiz);
	}

	private No deleteMin(No x) {
		if (x.esq == null)
			return x.dir;
		x.esq = deleteMin(x.esq);
		return x;
	}

	public void deleteMax() {
		if (vazia())
			throw new NoSuchElementException("A árvore está vazia!");
		raiz = deleteMax(raiz);
	}

	private No deleteMax(No x) {
		if (x.dir == null)
			return x.esq;
		x.dir = deleteMax(x.dir);
		return x;
	}

	public boolean delete(Chave chave) {
		raiz = delete(raiz, chave);
		if (raiz != null)
			return true;
		else
			return false;
	}

	private No delete(No x, Chave chave) {
		if (x == null)
			return null;
		int cmp = chave.compareTo(x.chave);
		if (cmp < 0)
			x.esq = delete(x.esq, chave);
		else if (cmp > 0)
			x.dir = delete(x.dir, chave);
		else {
			if (x.dir == null)
				return x.esq;
			if (x.esq == null)
				return x.dir;
			No t = x;
			x = min(t.dir);
			x.dir = deleteMin(t.dir);
			x.esq = t.esq;
		}
		return x;
	}

	public Chave piso(Chave chave) {
		if (chave == null)
			throw new IllegalArgumentException("A chave fornecida é null!");
		if (vazia())
			throw new NoSuchElementException("A árvore está vazia!");
		No x = piso(raiz, chave);
		if (x == null)
			return null;
		else
			return x.chave;
	}

	private No piso(No x, Chave chave) {
		if (x == null)
			return null;
		int cmp = chave.compareTo(x.chave);
		if (cmp == 0)
			return x;
		if (cmp < 0)
			return piso(x.esq, chave);
		No t = piso(x.dir, chave);
		if (t != null)
			return t;
		else
			return x;
	}

	public Chave topo(Chave chave) {
		if (chave == null)
			throw new IllegalArgumentException("A chave fornecida é null!");
		if (vazia())
			throw new NoSuchElementException("A árvore está vazia!");
		No x = topo(raiz, chave);
		if (x == null)
			return null;
		else
			return x.chave;
	}

	private No topo(No x, Chave chave) {
		if (x == null)
			return null;

		int cmp = chave.compareTo(x.chave);
		if (cmp == 0)
			return x;
		if (cmp < 0) {
			No t = topo(x.esq, chave);
			if (t != null)
				return t;
			else
				return x;
		}
		return topo(x.dir, chave);
	}

	public int rank(Chave chave) {
		if (chave == null)
			throw new IllegalArgumentException("A chave fornecida é nula");
		return rank(chave, raiz);
	}

	private int rank(Chave chave, No x) {
		if (x == null)
			return 0;
		if (chave.compareTo(x.chave) > 0)
			return 1 + tamanho(x.esq) + rank(chave, x.dir);
		else
			return rank(chave, x.esq);
	}

	public int height() {
		return height(raiz);
	}

	private int height(No x) {
		if (x == null)
			return -1;
		int maxHeight = Math.max(height(x.esq), height(x.dir));
		return 1 + maxHeight;
	}
	
	/*
	 * Metodo que gera uma árvore balanceada a partir de um array ordenado.
	 * Chama o metodo recursivo para inserir os nós em uma árvore cuja chave é
	 * um inteiro.
	 */
	
	public void geraArvBal(Valor[] arrayOrdenado) {
		this.raiz = criaArvBal(arrayOrdenado,0, arrayOrdenado.length-1);
	}
	
	private No criaArvBal(Valor[] array, int inicio, int fim) {
		if (inicio > fim)
			return null;
		Integer meio = (inicio + fim) /2;
		No novo = new No((Chave)meio,array[meio]);
		novo.esq = criaArvBal(array,inicio,meio-1);
		novo.dir = criaArvBal(array,meio+1,fim);
		return novo;
	}
}