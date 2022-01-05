package ufcg.ccc.domino;

/**
 * Classe criada para definir os diferentes tipos de vitórias que podem ter, e a pontuação sobre elas.
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
	 * Inicializa os tipos de vitórias possíveis. 
	 */
	public ClassificaVitoria() {
		this.laELo = 0;
		this.carroca = 0;
		this.normal = 0;
		this.cruzada = 0;
		this.total = 0;
	}
	
	/**
	 * Retorna quantas vezes o jogador ganhou com o "lá e lô". 
	 * @return Contagem de vitórias com "lá e lô". 
	 */
	public int getLaELo() {
		return laELo;
	}
	
	/**
	 * Retorna quantas vezes o jogador ganhou com a carroça. 
	 * @return Contagem de vitórias com a carroça. 
	 */
	public int getCarroca() {
		return carroca;
	}
	
	/**
	 * Retorna quantas vezes o jogador ganhou com uma peça simples. 
	 * @return Contagem de vitórias normais. 
	 */
	public int getNormal() {
		return normal;
	}
	
	/**
	 * Retorna quantas vezes o jogador ganhou com a cruzada. 
	 * @return Contagem de vitórias com cruzada. 
	 */
	public int getCruzada() {
		return cruzada;
	}
	
	/**
	 * Retorna o total de pontos.
	 * @return Pontuação total de um jogador.
	 */
	public int getTotal() {
		return total;
	} 
	
	/**
	 * Adiciona pontos de acordo com o tipo de vitória.
	 * @param pontos Pontuação no jogo.
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
