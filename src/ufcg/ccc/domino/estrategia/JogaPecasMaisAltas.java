package ufcg.ccc.domino.estrategia;

import java.util.List;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Peca;
import ufcg.ccc.domino.Jogada.TipoJogada;

public class JogaPecasMaisAltas implements EstrategiaDeJogo {

	@Override
	public Jogada decideJogada(List<Peca> mao, VisaoDaMesa mesa) {
		
		int ordemPecaMaisAlta = 0;
		int somaPecaMaisAlta = 0; 
		
		for (int i = 0; i < mao.size(); i++) {
			boolean encaixaNaDireita = mao.get(i).encaixa(mesa.getNumNaDireita());
			boolean encaixaNaEsquerda = mao.get(i).encaixa(mesa.getNumNaEsquerda());
			
			if(mesa.getNumPecas() == 0 || encaixaNaDireita || encaixaNaEsquerda) {
				int somaLadosDaPeca = mao.get(i).getNumDireito() + mao.get(i).getNumEsquerdo();
				if(somaLadosDaPeca > somaPecaMaisAlta) {
					somaPecaMaisAlta = somaLadosDaPeca;
					 ordemPecaMaisAlta = i;
				}
			}
		}
		
		Peca pecaMaisAlta = mao.get(ordemPecaMaisAlta); 
		if (pecaMaisAlta.encaixa(mesa.getNumNaDireita()))
			return new Jogada(pecaMaisAlta, TipoJogada.NA_DIREITA);
	
		if (pecaMaisAlta.encaixa(mesa.getNumNaEsquerda())) 
			return new Jogada(pecaMaisAlta, TipoJogada.NA_ESQUERDA);	
		
		return new Jogada();
	}
	
	public String toString() {
		return "Joga Peças Mais Altas";
	}
	
}
