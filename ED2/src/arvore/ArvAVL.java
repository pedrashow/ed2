package arvore;

import java.util.NoSuchElementException;

public class ArvAVL<Chave extends Comparable<Chave>, Valor> {
	private No raiz;
	private boolean mudouAltura;

	private class No {
		private Chave chave;
		private Valor valor;
		private No esq, dir;
		private int bal;

		public No(Chave chave, Valor valor) {
			this.chave = chave;
			this.valor = valor;
			this.esq = null;
			this.dir = null;
			this.bal = 0;
		}
	}

	public ArvAVL() {
		raiz = null;
	}

	public boolean vazia() {
		return (raiz == null);
	}

	public void mostra() {
		mostra(raiz);
		System.out.println();
	}

	private void mostra(No x) {
		if (x == null)
			return;
		System.out.print("[");
		mostra(x.esq);
		System.out.print("<" + x.chave + "(" + x.bal + ")>");
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
		if (x == null) {
			mudouAltura = true;
			return new No(chave, valor);
		}
		int cmp = chave.compareTo(x.chave);
		if (cmp < 0) {
			x.esq = put(x.esq, chave, valor);
			if (mudouAltura)
				x.bal++;
			if (x.bal == 0)
				mudouAltura = false;
		}
		else if (cmp > 0) {
			x.dir = put(x.dir, chave, valor);
			if (mudouAltura)
				x.bal--;
			if (x.bal == 0)
				mudouAltura = false;
		}
		else {
			x.valor = valor;
			return x;
		}
		return balanceia(x);
	}
	
	private No balanceia(No x) {
		if (x.bal < -1) {
			if (x.dir.bal == 1)
				x.dir = rotacionaDireita(x.dir);
			x = rotacionaEsquerda(x);
			mudouAltura = false;
		} else if (x.bal > 1) {
			if (x.esq.bal == -1)
				x.esq = rotacionaEsquerda(x.esq);
			x = rotacionaDireita(x);
			mudouAltura = false;
		}
		return x;
	}
	
	private No rotacionaDireita(No x) {
		No raizParcial = x.esq;
		x.esq = x.esq.dir;
		raizParcial.dir = x;
		x.bal = raizParcial.bal - 1;
		raizParcial.bal = 0;
		System.out.println("D " + x.valor);
		return raizParcial;
	}
	
	private No rotacionaEsquerda(No x) {
		No raizParcial = x.dir;
		x.dir = x.dir.esq;
		raizParcial.esq = x;
		x.bal = raizParcial.bal + 1 ;
		raizParcial.bal = 0;
		System.out.println("E " + x.valor);
		return raizParcial;
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
}