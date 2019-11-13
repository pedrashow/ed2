package hash;

public class TentativaLinear {
	private int n; //quantidade de elementos
	private int m; //tamanho da tabela hash
	private Integer[] tabela; //tabela hash
	
	public TentativaLinear(int tamanho) {  
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
		Integer valor = tabela[indice];
		if (valor == null)
			return Integer.MIN_VALUE;
		do {
			if (valor == x)
				return x;
			indice = (indice + 1) % m;
			valor = tabela[indice];
		} while (!(valor == null || indice == hash(x) ));
		return Integer.MIN_VALUE;
	}
	
	public void inserir(int x) {
		int indice = hash(x);
		do {
			if (tabela[indice] == null) {
				tabela[indice] = x;
				this.n++;
				return;
			}
			indice = (indice + 1) % m;
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
		Integer valor = tabela[indice];
		if (valor == null)
			return false;
		do {
			if (valor == x) {
				tabela[indice] = null;
				indice = (indice + 1) % m;
				while (tabela[indice] != null) {
					int numReinserir = tabela[indice];
					tabela[indice] = null;
					inserir(numReinserir);
					indice = (indice + 1) % m;
				}
				n--;
				return true;
			}
			indice = (indice + 1) % m;
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
		TentativaLinear hashLinear = new TentativaLinear(17);
		hashLinear.inserir(17);
		hashLinear.inserir(82);
		hashLinear.inserir(31);
		hashLinear.inserir(85);
		hashLinear.inserir(28);
		hashLinear.inserir(4);
		hashLinear.inserir(34);
		hashLinear.inserir(18);
		hashLinear.inserir(45);
		hashLinear.inserir(27);
		hashLinear.inserir(59);
		hashLinear.inserir(79);
		hashLinear.inserir(35);
		hashLinear.inserir(94);
		hashLinear.imprimir();
		System.out.println(hashLinear.buscar(35));
		hashLinear.remover(34);
		hashLinear.imprimir();
		System.out.println(hashLinear.buscar(35));
	}
	
	
}
