package hash;

public class EncadeamentoExterno {
	private int n; //quantidade de elementos
	private int m; //tamanho da tabela hash
	private No[] tabela; //tabela hash
	
	private class No {
		private int valor;
		private No prox;
		
		public No(int x) {
			this.valor = x;
			this.prox = null;
		}
	}
	
	public EncadeamentoExterno(int m) {
		this.m = m;
		this.n = 0;
		this.tabela = new No[m];
	}
	
	public int tamanho() {
		return n;
	}
	
	public int hash(int x) {
		return x % m;
	}
	
	public int buscar(int x) {
		/*
		 * retorna o menor valor inteiro disponível quando não encontra a chave.
		 */
		
		No retorno = buscar(tabela[hash(x)], x);
		
		if (retorno == null)
			return Integer.MIN_VALUE;
		
		return retorno.valor;
	}
	
	private No buscar(No x, int y) {
		if (x == null)
			return null;
		if (x.valor == y ) 
			return x;
		return buscar(x.prox,y);
	}
	
	public boolean inserir(int x) {
		/*
		 * Retorna falso se o elemento já existe e verdadeiro se ele foi inserido
		 */
		
		//se estiver vazio
		if (tabela[hash(x)] == null) {
			tabela[hash(x)] = new No(x);
			this.n++;
			return true;
		}
		
		//se não estiver vazia
		else {
			No corrente = tabela[hash(x)];
			while (!(corrente == null || corrente.valor == x)) {
				if (corrente.valor == x)
					return false;
				else if (corrente.prox == null) {
					corrente.prox = new No(x);
					this.n++;
					return true;
				}
				else
				corrente = corrente.prox;
			}
		}
		
		return false;
	}

	public boolean remover(int x) {
		/*
		 * Retorna falso se o elemento não foi encontrado e verdadeiro se ele foi removido
		 */
		
		//se estiver vazio
		if (tabela[hash(x)] == null)
			return false;
		
		//se não estiver vazia
		No corrente = tabela[hash(x)];
		
		//se for o primeiro		
		if (corrente.valor == x) {
			tabela[hash(x)] = corrente.prox;
			this.n--;
			return true;
		}
		
		//se estiver no meio ou no fim da lista
		while (!(corrente.prox == null)) {
			No anterior = corrente;
			corrente = corrente.prox;
			if (corrente.valor == x) {
				anterior.prox = corrente.prox;
				this.n--;
				return true;
			}
		}
		return false;
	}
	
	public void imprimir() {
		System.out.println("Quantidade de elementos: " + this.n);
		for (int i = 0; i < m; i++) {
			System.out.print(i+ ": ");
			No corrente = tabela[hash(i)];
			while (corrente != null) {
				System.out.print(corrente.valor + "->");
				corrente = corrente.prox;
			}
			System.out.println("null");
		}
	}
	
	public static void main(String[] args) {
		EncadeamentoExterno hashtable = new EncadeamentoExterno(13);
		for (int i = 4; i < 632; i+= 7) {
			hashtable.inserir(i);
		}
		hashtable.imprimir();

		System.out.println(hashtable.remover(53));
		System.out.println(hashtable.remover(305));
		hashtable.remover(571);

		hashtable.imprimir();
		System.out.println(hashtable.buscar(223));
		System.out.println(hashtable.buscar(4));
		
	}
	
}
