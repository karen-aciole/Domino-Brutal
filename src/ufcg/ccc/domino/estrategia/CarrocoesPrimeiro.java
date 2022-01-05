package ufcg.ccc.domino.estrategia;

import java.util.List;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Peca;
import ufcg.ccc.domino.Jogada.TipoJogada;

public class CarrocoesPrimeiro implements EstrategiaDeJogo {
	
	@Override
	public Jogada decideJogada(List<Peca> mao, VisaoDaMesa mesa) {
		
	
		 // Verifica a possibilidade de jogar o primeiro o carro��o.		 
		if (mesa.getNumPecas() == 0) {
			for(Peca peca : mao) {
				if (peca.ehCarroca())
					return new Jogada(mao.get(0), TipoJogada.NA_DIREITA);
			}
			return new Jogada(mao.get(0), TipoJogada.NA_DIREITA);
		}
		
		// Estrat�gia de jogar primeiro todos os carro��es
		for (Peca peca : mao) {
			if(peca.ehCarroca())
				if (peca.encaixa(mesa.getNumNaDireita())) 
					return new Jogada(peca, TipoJogada.NA_DIREITA);
		
				if (peca.encaixa(mesa.getNumNaEsquerda())) 
					return new Jogada(peca, TipoJogada.NA_ESQUERDA);
		}
		
		// Joga outras pelas se não houver carroção.
		for (Peca peca : mao) {
			if (peca.encaixa(mesa.getNumNaDireita()))
				return new Jogada(peca, TipoJogada.NA_DIREITA);
			
			if (peca.encaixa(mesa.getNumNaEsquerda())) 
				return new Jogada(peca, TipoJogada.NA_ESQUERDA);		
		}


		return new Jogada();
	}
	
	public String toString() {
		return "Carro��es Primeiro";
	}
}
