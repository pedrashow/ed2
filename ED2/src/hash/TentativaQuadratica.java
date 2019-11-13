package hash;

public class TentativaQuadratica {
	private int n; //quantidade de elementos
	private int m; //tamanho da tabela hash
	private Integer[] tabela; //tabela hash
	
	public TentativaQuadratica(int tamanho) {  
		this.n = 0;
		this.m = tamanho;
		this.tabela = new Integer[m];
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
		int indice = hash(x);
		int passo = 1;
		Integer valor = tabela[indice];
		if (valor == null)
			return Integer.MIN_VALUE;
		do {
			if (valor == x)
				return x;
			indice = (hash(x) + (passo*passo)) % m;
			valor = tabela[indice];
			passo++;
		} while (!(valor == null || indice == hash(x) ));
		return Integer.MIN_VALUE;
	}
	
	public void inserir(int x) {
		int indice = hash(x);
		int passo = 1;
		do {
			if (tabela[indice] == null) {
				tabela[indice] = x;
				this.n++;
				return;
			}
			indice = (hash(x) + (passo*passo)) % m;
			passo++;
		} while (indice != hash(x));
		throw new IllegalStateException("Tabela Hash está cheia");
	}
	
	public boolean remover(int x) {
		/*
		 * Para remover é necessário re-inserir todos os valores entre o removido e o proximo nulo,
		 * uma vez que apenas remover deixaria a tabela inconsistente
		 * 
		 * Retorna falso quando o elemento não esta na tabela, verdadeiro se o elemento foi removido
		 */
		int indice = hash(x);
		int passo = 1;
		Integer valor = tabela[indice];
		if (valor == null)
			return false;
		do {
			if (valor == x) {
				tabela[indice] = null;
				passo = 1;
				indice = (indice + 1) % m;
				while (tabela[indice] != null) {
					int numReinserir = tabela[indice];
					tabela[indice] = null;
					inserir(numReinserir);
					indice = (hash(x) + (passo * passo)) % m;
					passo++;
				}
				n--;
				return true;
			}
			indice = (hash(x) + (passo*passo)) % m;
			passo++;
			valor = tabela[indice];
		} while (!(valor == null || indice == hash(x) ));
		return false;
		
	}
	
	public void imprimir() {
		for (int i = 0; i<m ; i++) {
			System.out.println(i + " " + tabela[i]);
		}
	}
	
	public static void main(String[] args) {
		TentativaQuadratica hashQuadratico = new TentativaQuadratica(29);
		hashQuadratico.inserir(17);
		hashQuadratico.inserir(82);
		hashQuadratico.inserir(31);
		hashQuadratico.inserir(85);
		hashQuadratico.inserir(28);
		hashQuadratico.inserir(4);
		hashQuadratico.inserir(34);
		hashQuadratico.inserir(18);
		hashQuadratico.inserir(45);
		hashQuadratico.inserir(27);
		hashQuadratico.inserir(59);
		hashQuadratico.imprimir();
		System.out.println(hashQuadratico.buscar(28));
		hashQuadratico.remover(85);
		hashQuadratico.imprimir();
		System.out.println(hashQuadratico.buscar(27));
	}
	
	
}
