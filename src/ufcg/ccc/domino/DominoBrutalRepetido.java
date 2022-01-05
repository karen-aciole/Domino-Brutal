package ufcg.ccc.domino;

import ufcg.ccc.domino.estrategia.EstrategiaDeJogo;
import ufcg.ccc.domino.estrategia.EstrategiaInvalidaException;
import ufcg.ccc.domino.estrategia.JogaPrimeiraPossivel;

/**
 * Exemplo de como fazer um um main com uma disputa de muuuitos jogos entre duas
 * estratÃ©gias.
 * 
 */
public class DominoBrutalRepetido {
	
	private static final int NUM_PECAS_INICIAL = 12;
	private static final int REPETICOES = 50000;

	public static void main(String[] args) throws EstrategiaInvalidaException, JogadaInvalidaException {
		float vitoriasJ1 = 0, vitoriasJ2 = 0, empates = 0;

		ClassificaVitoria J1 = new ClassificaVitoria(), J2 = new ClassificaVitoria();
		
		EstrategiaDeJogo estrategia1 = new JogaPrimeiraPossivel(), estrategia2 = new JogaPrimeiraPossivel();
		//estrategia2 = new CarrocoesPrimeiro()
		//estrategia2 = new JogaPecasMaisAltas()
		
		for (int i = 0; i < REPETICOES; i++) {

			
			Jogo j;
			
			// Cada estratÃ©gia comeÃ§a jogando metade das partidas.
			if( i < REPETICOES / 2) {
				j = new Jogo("J1", estrategia1, "J2", estrategia2,  NUM_PECAS_INICIAL);
			} else {
				j = new Jogo("J2", estrategia2, "J1",  estrategia1, NUM_PECAS_INICIAL);	
			}
			
			HistoricoDeJogo historico = j.jogaJogoCompleto();
			if (historico.isEmpate()) {
				empates++;
			} else if (historico.getVencedor() == "J1") {
				vitoriasJ1++;
				J1.adicionaPontuacao(j.getPontuacao());
			} else if (historico.getVencedor() == "J2") {
				vitoriasJ2++;
				J2.adicionaPontuacao(j.getPontuacao());			
			}
		}
		
		String vencedor = "";
		
		if (J1.getTotal() > J2.getTotal()) {
			vencedor = "J1";
		} else if (J2.getTotal() > J1.getTotal()) {
			vencedor = "J2";
		} else {
			vencedor = "Empate";
		}
		
	
			System.out.println(
					"E1: " + estrategia1.toString() 
					+ "\nE2: " + estrategia2.toString()
					//+ "\nE3: " + estrategia3.toString()
	 				+ "\nJogos:\t" + (REPETICOES) 
					+ "\n- Vitórias E1:\t" + vitoriasJ1 + " vitórias (" + Math.round(vitoriasJ1 / REPETICOES * 100) + "%)" 
					+ "\n- Vitórias E2:\t" + vitoriasJ2 + " vitórias (" + Math.round(vitoriasJ2 / REPETICOES * 100) + "%)"
					+ "\n- Empates:\t" + empates + "\t\t(" + Math.round(empates / REPETICOES * 100) + "%)"
					+ "\n- Vencedor:\t" + vencedor + "\t\t");
		
		
			
		System.out.println("\n-------- J1 --------\t" + imprimePontuacao(J1));
		System.out.println("\n-------- J2 --------\t" + imprimePontuacao(J2));

	}
		
	public static String imprimePontuacao(ClassificaVitoria vencedor) {
		return 	"\n- Vitórias normais:\t" + vencedor.getNormal() 
					+ "\n- Vitórias de carroça:\t" + vencedor.getCarroca()
					+ "\n- Vitórias de lá e lô:\t" + vencedor.getLaELo()
					+ "\n- Vitórias com cruzadas:\t" + vencedor.getCruzada()
					+ "\n\nPontuação" + ":\t" + vencedor.getTotal();	
	}

}
