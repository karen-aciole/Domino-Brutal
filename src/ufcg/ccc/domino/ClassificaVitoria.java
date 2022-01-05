package ufcg.ccc.domino;

/**
 * Classe criada para definir os diferentes tipos de vit�rias que podem ter, e a pontua��o sobre elas.
 * 
 * @author Karen Anne Aciole Alves - 119210934
 *
 */
public class ClassificaVitoria {
	
	private int laELo; 		// 3 pontos
	private int carroca; 	// 2 pontos
	private int normal; 	// 1 ponto
	private int cruzada; 	// 6 pontos 
	private int total;
	
	/**
	 * Inicializa os tipos de vit�rias poss�veis. 
	 */
	public ClassificaVitoria() {
		this.laELo = 0;
		this.carroca = 0;
		this.normal = 0;
		this.cruzada = 0;
		this.total = 0;
	}
	
	/**
	 * Retorna quantas vezes o jogador ganhou com o "l� e l�". 
	 * @return Contagem de vit�rias com "l� e l�". 
	 */
	public int getLaELo() {
		return laELo;
	}
	
	/**
	 * Retorna quantas vezes o jogador ganhou com a carro�a. 
	 * @return Contagem de vit�rias com a carro�a. 
	 */
	public int getCarroca() {
		return carroca;
	}
	
	/**
	 * Retorna quantas vezes o jogador ganhou com uma pe�a simples. 
	 * @return Contagem de vit�rias normais. 
	 */
	public int getNormal() {
		return normal;
	}
	
	/**
	 * Retorna quantas vezes o jogador ganhou com a cruzada. 
	 * @return Contagem de vit�rias com cruzada. 
	 */
	public int getCruzada() {
		return cruzada;
	}
	
	/**
	 * Retorna o total de pontos.
	 * @return Pontua��o total de um jogador.
	 */
	public int getTotal() {
		return total;
	} 
	
	/**
	 * Adiciona pontos de acordo com o tipo de vit�ria.
	 * @param pontos Pontua��o no jogo.
	 */
	public void adicionaPontuacao(int pontos) {
		if (pontos == 3) {
			this.laELo++;
			total += pontos;
			
		}else if (pontos == 2) {
			this.carroca++;
			total += pontos;
			
		}else if (pontos == 1) {
			this.normal++;
			total += pontos;
		
		}else if (pontos == 6) {
			this.cruzada++;
			total += pontos;
		}
	}

}
