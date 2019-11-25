package grafo;

import java.util.Arrays;

public class GrafoMatriz {
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

	private int mat[][];// pesos do tipo inteiro
	private int numVertices;
	private int pos[];// posição atual ao se percorrer os adjs de um vértice v
	private int numArestas;

	public GrafoMatriz(int numVertices) {
		this.mat = new int[numVertices][numVertices];
		this.pos = new int[numVertices];
		this.numVertices = numVertices;
		for (int i = 0; i < this.numVertices; i++) {
			for (int j = 0; j < this.numVertices; j++)
				this.mat[i][j] = 0;
			this.pos[i] = -1;
		}
	}

	public void insereAresta(int v1, int v2, int peso) {
		this.mat[v1][v2] = peso;
		this.numArestas++;
	}

	public boolean existeAresta(int v1, int v2) {
		return (this.mat[v1][v2] > 0);
	}

	public boolean listaAdjVazia(int v) {
		for (int i = 0; i < this.numVertices; i++)

			if (this.mat[v][i] > 0)
				return false;
		return true;
	}

	public Aresta primeiroListaAdj(int v) {
		// Retorna a primeira aresta que o vértice v participa ou
		// null se a lista de adjacência de v for vazia
		this.pos[v] = -1;
		return this.proxAdj(v);
	}

	public Aresta proxAdj(int v) {
		// Retorna a próxima aresta que o vértice v participa ou
		// null se a lista de adjacência de v estiver no fim
		this.pos[v]++;
		while ((this.pos[v] < this.numVertices) && (this.mat[v][this.pos[v]] == 0))
			this.pos[v]++;

		if (this.pos[v] == this.numVertices)
			return null;
		else
			return new Aresta(v, this.pos[v], this.mat[v][this.pos[v]]);
	}

	public Aresta retiraAresta(int v1, int v2) {

		if (this.mat[v1][v2] == 0)
			return null; // Aresta não existe
		else {
			Aresta aresta = new Aresta(v1, v2, this.mat[v1][v2]);
			this.mat[v1][v2] = 0;
			this.numArestas++;
			return aresta;
		}
	}

	public void imprime() {
		System.out.print(" ");
		for (int i = 0; i < this.numVertices; i++)
			System.out.print(i + " ");
		System.out.println();
		for (int i = 0; i < this.numVertices; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < this.numVertices; j++)
				System.out.print(this.mat[i][j] + " ");
			System.out.println();
		}
	}

	public int numVertices() {
		return this.numVertices;
	}
	
	public boolean ehNulo() {
		for (int i = 0; i < numVertices; i++)
			if (!listaAdjVazia(i))
				return false;
		return true;
	}
	
	public int grau(int v) {
		int contador=0;
		for (int i = 0; i < numVertices; i++)
			if (mat[v][i] > 0)
				contador++;
		return contador;
	}
	
	public boolean ehRegular() {
		int i = 0;
		int grau = grau(0);
		while (i < numVertices-1) {
			i++;
			if (grau != grau(i))
				return false;
			grau = (grau(i));
		}
		return true;
	}
	
	public boolean ehCompleto() {
		for (int i = 0; i < numVertices; i++)
			for (int j = 0; j < numVertices; j++)
				if (mat[i][j] == 0)
					return false;
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
		for (int i =0; i < numVertices; i++) {
			if (mat[v][i] > 0 && !visitado[i])
				andarNoGrafo(i,visitado);
		}
	}
}
