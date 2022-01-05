package ufcg.ccc.domino;

/**
 * Uma peÃ§a de dominÃ³ com dois lados.
 *
 */
public class Peca {

	private int numEsquerdo;
	private int numDireito;

	/**
	 * Cria uma peÃ§a.
	 * 
	 * @param numEsquerdo NÃºmero do lado esquerdo.
	 * @param numDireito  NÃºmero do lado direito.
	 */
	public Peca(int numEsquerdo, int numDireito) {
		this.numEsquerdo = numEsquerdo;
		this.numDireito = numDireito;
	}

	/**
	 * Inverte os lados dos números na peça.
	 */
	public void gira() {
		int tmp = numEsquerdo;
		numEsquerdo = numDireito;
		numDireito = tmp;
	}

	/**
	 * 
	 * @return O número da direita.
	 */
	public int getNumDireito() {
		return numDireito;
	}

	/**
	 * 
	 * @return O nÃºmero da esquerda.
	 */
	public int getNumEsquerdo() {
		return numEsquerdo;
	}

	@Override
	public String toString() {
		return this.getNumEsquerdo() + ":" + this.getNumDireito();
	}

	/**
	 * Testa se a peÃ§a encaixa com um nÃºmero.
	 * 
	 * @param numero O nÃºmero a testar.
	 * @return true se um dos lados ao menos combinar com o nÃºmer.
	 */
	public boolean encaixa(int numero) {
		return this.numDireito == numero || this.numEsquerdo == numero;
	}
	
	/**
	 * Verifica se a peça é uma carroça. 
	 * @return Retorna true se for carroça.
	 */
	public boolean ehCarroca() {
		return this.numDireito == this.numEsquerdo;
	}

}
