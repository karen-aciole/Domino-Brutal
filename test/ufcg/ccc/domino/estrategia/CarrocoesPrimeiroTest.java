package ufcg.ccc.domino.estrategia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Jogada.TipoJogada;
import ufcg.ccc.domino.Mesa;
import ufcg.ccc.domino.Peca;

public class CarrocoesPrimeiroTest {
	
	private Mesa mesa;
	
	@BeforeEach
	void setUp() throws Exception {
		mesa = new Mesa();
		mesa.jogaNaDireita(new Peca(1, 2));
		mesa.jogaNaEsquerda(new Peca(1, 1));
	}
	
	@Test
	void testaPassa() {
		CarrocoesPrimeiro estrategia = new CarrocoesPrimeiro();
		List<Peca> mao =  List.of(new Peca(3,3), new Peca(6,6));
		Jogada j1 = estrategia.decideJogada(mao, mesa);

		assertEquals(TipoJogada.PASSA, j1.getTipo());
	}
	
	@Test
	void testPrefereDireita() {
		CarrocoesPrimeiro estrategia = new CarrocoesPrimeiro();
		List<Peca> mao =  List.of(new Peca(2, 2), new Peca(1, 1), new Peca(5, 3), new Peca(0, 1));
		
		Jogada j1 = estrategia.decideJogada(mao, mesa);

		assertEquals(TipoJogada.NA_DIREITA, j1.getTipo());
		assertEquals(2, j1.getPeca().getNumEsquerdo());
		assertEquals(2, j1.getPeca().getNumDireito());
	}	
	
	@Test
	void testJogaNaEsquerda() {
		CarrocoesPrimeiro estrategia = new CarrocoesPrimeiro();

		Jogada j1 = estrategia.decideJogada(List.of(new Peca(1, 1), new Peca(1, 4), new Peca(5, 6)), mesa);

		assertEquals(TipoJogada.NA_ESQUERDA, j1.getTipo());
		assertEquals(1, j1.getPeca().getNumEsquerdo());
		assertEquals(1, j1.getPeca().getNumDireito());
	}

}
