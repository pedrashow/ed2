package grafo;

import java.util.Arrays;

import grafo.Lista.Elo;

public class GrafoLista {
	
	public static class Aresta {
		private int v1, v2, peso;

		public Aresta(int v1, int v2, int peso) {
			this.v1 = v1;
			this.v2 = v2;
			this.peso = peso;
		}

		public int peso() {
			return this.peso;
		}

		public int v1() {
			return this.v1;
		}

		public int v2() {
			return this.v2;
		}
	}

	private static class Celula {
		int vertice, peso;

		public Celula(int v, int p) {
			this.vertice = v;
			this.peso = p;
		}

		public boolean equals(Object obj) {
			Celula item = (Celula) obj;
			return (this.vertice == item.vertice);
		}
		
		public String toString() {
			return String.valueOf(this.vertice);
		}
	}

	private Lista<Celula> adj[];
	private int numVertices;
	
	private int numArestas;

	public GrafoLista(int numVertices) {
		this.adj = new Lista[numVertices];
		this.numVertices = numVertices;
		for (int i = 0; i < this.numVertices; i++)
			this.adj[i] = new Lista<>();
		this.numArestas = 0;
	}

	public void insereAresta(int v1, int v2, int peso) {
		Celula item = new Celula(v2, peso);
		this.adj[v1].insere(item);
		item = new Celula(v1, peso);
		this.adj[v2].insere(item);
		this.numArestas++;
	}

	public boolean existeAresta(int v1, int v2) {
		Celula item = new Celula(v2, 0);
		return (this.adj[v1].busca(item));
	}

	public boolean listaAdjVazia(int v) {
		return this.adj[v].vazia();
	}

	public boolean retiraAresta(int v1, int v2) throws Exception {
		Celula chave = new Celula(v2, 0);
		if (!this.adj[v1].remove(chave))
			return false;
			else {
				chave = new Celula(v1,0);
				if (!this.adj[v2].remove(chave))
					return false;
			}
		this.numArestas--;
		return true;
	}

	public void imprime() {
		for (int i = 0; i < this.numVertices; i++) {
			System.out.print("Vertice " + i + " : ");
			this.adj[i].imprimeLista();
		}
	}

	public int numVertices() {
		return this.numVertices;
	}
	
	public boolean ehNulo() {
		return this.numArestas == 0;
	}
	
	public boolean ehRegular() {
		int i = 0;
		int grau = this.adj[i].tamanho;
		while (i < numVertices -1) {
			i++;
			if (grau != this.adj[i].tamanho)
				return false;
			grau = (this.adj[i].tamanho);
		}
		return true;
	}
	
	public boolean ehCompleto() {
		for (int i = 0; i < numVertices; i++) {
			if (this.adj[i].tamanho < numVertices -1)
				return false;
		}
		return true;
	}
	
	public boolean ehConexo() {
		boolean[] visitado = new boolean[numVertices];
		Arrays.fill(visitado, false);
		andarNoGrafo(0,visitado);
		for (int i =0; i< numVertices; i++)
			if (!visitado[i])
				return false;
		return true;
	}
	
	public void andarNoGrafo(int v, boolean[] visitado) {
		if (visitado[v])
			return;
		visitado[v] = true;
		Elo corrente = this.adj[v].primeiro;
		while (corrente != null) {
			Celula cel = (Celula) corrente.dado;
			if (!visitado[cel.vertice]) {
				andarNoGrafo(cel.vertice,visitado);
			}
			corrente = corrente.proximo;
		}
	}
	
}
