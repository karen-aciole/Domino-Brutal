package ufcg.ccc.domino;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ufcg.ccc.domino.Jogada.TipoJogada;
import ufcg.ccc.domino.estrategia.EstrategiaDeJogo;
import ufcg.ccc.domino.estrategia.EstrategiaInvalidaException;

/**
 * Um jogo de dominÛ envolvendo 2 jogadores.
 *
 */
public class Jogo {

	private Jogador jogador1;
	private Jogador jogador2;

	private Mesa mesa;
	private int rodadasJogadas;

	private boolean finalizado;
	private String vencedor;	
	private int pontuacao;

	/**
	 * Fatora cÛdigo comum de inicializaÁ„o.
	 */
	private Jogo() {
		this.pontuacao = 0;
		this.rodadasJogadas = 0;
		this.finalizado = false;
		this.vencedor = null;
		this.mesa = new Mesa();
	}

	/**
	 * Para uso em testes apenas: cria, embaralha e distribui peÁas entre dois
	 * jogadores de maneira reprodutÌvel. Sempre que o mesmo objeto Random for
	 * passado, as peÁas com cada jogador acabar„o sendo as mesmas.
	 * 
	 * @param nomeJogador1            Id do jogador 1.
	 * @param estrategia1             Estrat√©gia para o jogador 1.
	 * @param nomeJogador2            Id do jogador 2.
	 * @param estrategia2             Estrat√©gia para o jogador 2.
	 * @param numPecasInicial         N√∫mero de pe√ßas a dar para cada jogador no
	 *                                in√≠cio.
	 * @param geradorDeNumsAleatorios Objeto que determina as pe√ßas que cada um
	 *                                receber√° ap√≥s embaralhamento.
	 */
	protected Jogo(String nomeJogador1, EstrategiaDeJogo estrategia1, String nomeJogador2, EstrategiaDeJogo estrategia2,
			int numPecasInicial, Random geradorDeNumsAleatorios) {
		this();
		List<Peca> pecas = criaPecas();
		Collections.shuffle(pecas, geradorDeNumsAleatorios);

		List<Peca> maoJ1 = sorteiaMao(pecas, numPecasInicial);
		List<Peca> maoJ2 = sorteiaMao(pecas, numPecasInicial);

		this.jogador1 = new Jogador(nomeJogador1, estrategia1, maoJ1);
		this.jogador2 = new Jogador(nomeJogador2, estrategia2, maoJ2);
	}

	/**
	 * Cria, embaralha e distribui pe√ßas entre dois jogadores.
	 * 
	 * @param nomeJogador1    Id do jogador 1.
	 * @param estrategia1     Estrat√©gia para o jogador 1.
	 * @param nomeJogador2    Id do jogador 2.
	 * @param estrategia2     Estrat√©gia para o jogador 2.
	 * @param numPecasInicial N√∫mero de pe√ßas a dar para cada jogador no in√≠cio.
	 */
	public Jogo(String nomeJogador1, EstrategiaDeJogo estrategia1, String nomeJogador2, EstrategiaDeJogo estrategia2,
			int numPecasInicial) {
		this(nomeJogador1, estrategia1, nomeJogador2, estrategia2, numPecasInicial, new Random());
	}

	/**
	 * 
	 * Para uso em testes: cria um jogo com pe√ßas escolhidas para a m√£o dos
	 * jogadores. Isso permite criar situa√ß√µes para testes de unidade mais
	 * facilmente.
	 * 
	 * @param nomeJogador1 Id do jogador 1.
	 * @param estrategia1  Estrat√©gia para o jogador 1.
	 * @param nomeJogador2 Id do jogador 2.
	 * @param estrategia2  Estrat√©gia para o jogador 2.
	 * @param maoJogador1  M√£o do jogador 1.
	 * @param maoJogador2  M√£o do jogador 2
	 */
	public Jogo(String nomeJogador1, EstrategiaDeJogo estrategia1, String nomeJogador2, EstrategiaDeJogo estrategia2,
			List<Peca> maoJogador1, List<Peca> maoJogador2) {
		this();
		this.jogador1 = new Jogador(nomeJogador1, estrategia1, new LinkedList<Peca>(maoJogador1));
		this.jogador2 = new Jogador(nomeJogador2, estrategia2, new LinkedList<Peca>(maoJogador2));
	}

	/**
	 * Sorteia pe√ßas para um jogador.
	 * 
	 * @param pecas           conjunto de pe√ßas total (ser√° alterado)
	 * @param numPecasInicial N√∫mero de pe√ßas a retirar
	 * @return PeÁas retiradas.
	 */
	private List<Peca> sorteiaMao(List<Peca> pecas, int numPecasInicial) {
		List<Peca> mao = new LinkedList<Peca>();
		for (int i = 0; i < numPecasInicial; i++) {
			mao.add(pecas.remove(0));
		}
		return mao;
	}

	/**
	 * Cria o domin√≥.
	 * 
	 * @return Conjunto de 28 pe√ßas de 0:0 a 6:6
	 */
	private List<Peca> criaPecas() {
		List<Peca> pecas = new LinkedList<Peca>();

		for (int i = 0; i <= 6; i++) {
			for (int j = i; j <= 6; j++) {
				pecas.add(new Peca(i, j));
			}
		}

		return pecas;
	}

	/**
	 * @return N√∫mero de pe√ßas na m√£o do jogador 1.
	 */
	public int getNumPecasJ1() {
		return this.jogador1.getNumPecas();
	}

	/**
	 * @return N√∫mero de pe√ßas na m√£o do jogador 2.
	 */
	public int getNumPecasJ2() {
		return this.jogador2.getNumPecas();
	}

	/**
	 * Joga uma rodada do jogo. Ambos os jogadores fazem 1 jogada, iniciando pelo
	 * jogador 1. As exce√ß√µes abaixo s√£o necess√°rias para proteger o jogo de
	 * estrat√©gias com bugs.
	 * 
	 * @throws EstrategiaInvalidaException Se a estrat√©gia de um dos jogadores
	 *                                     decidir jogar uma pe√ßa que ele n√£o
	 *                                     possui.
	 * @throws JogadaInvalidaException     Se a pe√ßa escolhida por algum dos
	 *                                     jogadores n√£o encaixar na mesa.
	 */
	public void rodada() throws EstrategiaInvalidaException, JogadaInvalidaException {
		rodadasJogadas += 1;

		Jogada jogadaJ1 = jogador1.decideJogada(mesa);
		jogaJogada(jogador1, jogadaJ1);

		if (jogador1.getNumPecas() == 0) {
			// J1 venceu
			this.finalizado = true;
			this.vencedor = this.jogador1.getNome();
			classificaVitoria(this.jogador1);
			return;
		}

		Jogada jogadaJ2 = jogador2.decideJogada(mesa);
		jogaJogada(jogador2, jogadaJ2);

		if (jogador2.getNumPecas() == 0) {
			// J2 venceu
			this.finalizado = true;
			this.vencedor = this.jogador2.getNome();
			classificaVitoria(this.jogador2);
			return;
		}

		
		if (jogadaJ1.getTipo() == TipoJogada.PASSA && jogadaJ2.getTipo() == TipoJogada.PASSA) {
		
			// US1: Desempate // 
			if (getJogadorQuePossuiMenosPecas() != null) {
				this.vencedor = this.getJogadorQuePossuiMenosPecas();
				this.pontuacao = 1;
				this.finalizado = true;
			
			}else if (getJogadorComSomatorioDePecasMenor() != null){
				this.vencedor = this.getJogadorComSomatorioDePecasMenor();
				this.pontuacao = 1;
				this.finalizado = true;
			
			}else {					
				this.finalizado = true;
				this.vencedor = null;
			}
		}		
	}
	
	
	
	/**
	 * Pega o jogador que possui menos peÁas na m„o. 
	 * @return Retorna uma String com o nome do jogador que possui menos peÁas ou null se ambos tiverem o mesmo n˙mero de peÁas.
	 */
	public String getJogadorQuePossuiMenosPecas() {
		if (jogador1.getNumPecas() < jogador2.getNumPecas()) {
			return this.jogador1.getNome();
		}else if (jogador2.getNumPecas() < jogador1.getNumPecas()) {
			return this.jogador2.getNome();
		} else {
			return null;
		}
	}
		
	
	/**
	 * MÈtodo pra classificar o tipo de vitÛria do jogador e dar a pontuaÁ„o conforme o tipo da vitÛria.
	 * @param vencedor Vencedor do jogo.
	 */
	public void classificaVitoria(Jogador vencedor) {
		Peca ultimaPeca = vencedor.getUltimaJogada().getPeca();
		boolean la = mesa.getNumNaEsquerda() == ultimaPeca.getNumEsquerdo() || mesa.getNumNaDireita() == ultimaPeca.getNumEsquerdo();
		boolean lo = mesa.getNumNaEsquerda() == ultimaPeca.getNumDireito() || mesa.getNumNaDireita() == ultimaPeca.getNumDireito();
		
		if(ultimaPeca.ehCarroca()) {
			if(la && lo) {
				this.pontuacao = 6;	// CarroÁa cruzada
			}else {
				this.pontuacao = 2;	// CarroÁa
			}
		
		}else {
			if(la && lo) {
				this.pontuacao = 3;	// L· e lÙ simples
			}else {
				this.pontuacao = 1; // Normal
			}
		}		
	}
	
	/**
	 *  Pega jogador que tem o menor somatÛrio das peÁas que possui na m„o.
	 *  @return Retorna uma String com o jogador que possui a menor soma. Null se ambos tiverem a mesma soma. 
	 */
	public String getJogadorComSomatorioDePecasMenor() {
		if(jogador1.getSomatorioDasPecas() < jogador2.getSomatorioDasPecas()) {
			return this.jogador1.getNome();
		}else if (jogador2.getSomatorioDasPecas() < jogador1.getSomatorioDasPecas()) {
			return this.jogador2.getNome();
		}else {
			return null;
		}
	}

	/**
	 * Joga o jogo do ponto atual atÈ o seu fim.
	 * @return 
	 * 
	 * @throws EstrategiaInvalidaException Se a estratÈgia de um dos jogadores
	 *                                     decidir jogar uma pe„o que ele n„o
	 *                                     possui.
	 * @throws JogadaInvalidaException     Se a pe„o escolhida por algum dos
	 *                                     jogadores n„o encaixar na mesa.
	 */
	public HistoricoDeJogo jogaJogoCompleto() throws EstrategiaInvalidaException, JogadaInvalidaException {
		HistoricoDeJogo jogado = new HistoricoDeJogo(jogador1, jogador2);
		while (!this.isFinalizado()) {
			this.rodada();
			jogado.addRodada(jogador1.getUltimaJogada(), jogador2.getUltimaJogada(), mesa);
		}
		
		if(this.isResultadoEmpate()) {
			jogado.setResultadoEmpate();
		} else {
			jogado.setVencedor(getVencedor());
		}
		
		return jogado;
//		System.out.println("==> final: " + (venceu == null ? "EMPATE" : venceu + " VENCEU") + "\n");
	}

	/**
	 * Faz a jogada decidida por um dos jogadores ter efeito. Quem realiza de fato
	 * as jogadas È essa classe (Jogo), e n√†o o Jogador ou a estrat√©gia, para evitar
	 * que estrat√©gias com erro modifiquem indevidamente a mesa ou dados do jogador.
	 * 
	 * @param jogador Jogador jogando
	 * @param jogada  A jogada a colocar em pr√°tica
	 * @throws JogadaInvalidaException Caso ela n√£o possa ser jogada na mesa atual
	 */
	private void jogaJogada(Jogador jogador, Jogada jogada) throws JogadaInvalidaException {
		if (jogada.getTipo() == TipoJogada.PASSA) {
			return;
		}

		switch (jogada.getTipo()) {
		case NA_ESQUERDA: {
			this.mesa.jogaNaEsquerda(jogada.getPeca());
			break;
		}
		case NA_DIREITA: {
			this.mesa.jogaNaDireita(jogada.getPeca());
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + jogada.getTipo());
		}

		jogador.removeDaMao(jogada.getPeca());
	}

	@Override
	public String toString() {
		String o = jogador1.toString() + "\n" + jogador2.toString() + "\nMesa: " + mesa.toString();
		return o;
	}

	/**
	 * @return N√∫mero de jogadas j√° jogadas.
	 */
	public int getNumRodadas() {
		return rodadasJogadas;
	}

	/**
	 * O jogo est√° finalizado quando um dos jogadores n√£o tem mais pe√ßas ou n√£o √©
	 * mais poss√≠vel jogar para ambos.
	 * 
	 * @return Se o jogo est· encerrado
	 */
	public boolean isFinalizado() {
		return this.finalizado;
	}

	/**
	 * Informa se o jogo terminou e foi empate. Retorna false se o jogo ainda n√£o
	 * acabou.
	 * 
	 * @return true se o jogo acabou com empate.
	 */
	public boolean isResultadoEmpate() {
		return this.isFinalizado() && this.vencedor == null;
	}

	/**
	 * @return Id do vencedor, ou null caso o jogo n„o esteja finalizado.
	 */
	public String getVencedor() {
		return vencedor;
	}
	
	/**
	 * Pega a pontuaÁ„o do jogador
	 * @return Retorna quantos pontos o jogador ganhou.
	 */
	public int getPontuacao() {
		return this.pontuacao;
	}
}
