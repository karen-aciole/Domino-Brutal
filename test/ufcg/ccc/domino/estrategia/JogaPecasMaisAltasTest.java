package ufcg.ccc.domino.estrategia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Mesa;
import ufcg.ccc.domino.Peca;
import ufcg.ccc.domino.Jogada.TipoJogada;


public class JogaPecasMaisAltasTest {

	private Mesa mesa; 
	
	@BeforeEach
	void setUp() throws Exception {
		mesa = new Mesa();
		mesa.jogaNaDireita(new Peca(1, 2));
		mesa.jogaNaEsquerda(new Peca(1, 1));
	}
	
	@Test
	void testPassa() {
		JogaPecasMaisAltas estrategia = new JogaPecasMaisAltas();
		List<Peca> mao =  List.of(new Peca(3,3), new Peca(6,6));
		Jogada j1 = estrategia.decideJogada(mao, mesa);

		assertEquals(TipoJogada.PASSA, j1.getTipo());
	}
	
	@Test
	void testPrefereDireita() {
		JogaPecasMaisAltas estrategia = new JogaPecasMaisAltas();
		List<Peca> mao =  List.of(new Peca(2,4), new Peca(1,2));
		Jogada j1 = estrategia.decideJogada(mao, mesa);
		assertEquals(TipoJogada.NA_DIREITA, j1.getTipo());
		assertEquals(2, j1.getPeca().getNumEsquerdo());
		assertEquals(4, j1.getPeca().getNumDireito());
	}
	
	@Test
	void testJogaNaEsquerda() {
		JogaPecasMaisAltas estrategia = new JogaPecasMaisAltas();
		List<Peca> mao =  List.of(new Peca(2,4), new Peca(1,6));
		Jogada j1 = estrategia.decideJogada(mao, mesa);
		assertEquals(TipoJogada.NA_ESQUERDA, j1.getTipo());
		assertEquals(1, j1.getPeca().getNumEsquerdo());
		assertEquals(6, j1.getPeca().getNumDireito());
	}
	
}

