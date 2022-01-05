package ufcg.ccc.domino;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClassificaVitoriaTest {
	
	ClassificaVitoria J1;
	ClassificaVitoria J2;
	
	@BeforeEach
	void setUp() {
		this.J1 = new ClassificaVitoria();
		this.J2 = new ClassificaVitoria();		
	}
	
	@Test
	void testGetLaELo() {
		this.J1.adicionaPontuacao(3);
		assertEquals(1, this.J1.getLaELo());
		
		this.J2.adicionaPontuacao(3);
		this.J2.adicionaPontuacao(3);
		assertEquals(2, this.J2.getLaELo());
	}
	
	@Test
	void testGetCarroca() {
		this.J1.adicionaPontuacao(2);
		this.J1.adicionaPontuacao(2);
		assertEquals(2, this.J1.getCarroca());
		
		this.J2.adicionaPontuacao(2);
		this.J2.adicionaPontuacao(2);
		this.J2.adicionaPontuacao(2);
		assertEquals(3, this.J2.getCarroca());		
	}
	
	@Test
	void testGetNormal() {
		this.J1.adicionaPontuacao(1);
		this.J1.adicionaPontuacao(1);
		assertEquals(2, this.J1.getNormal());
		
		this.J2.adicionaPontuacao(1);
		this.J2.adicionaPontuacao(1);
		this.J2.adicionaPontuacao(1);
		this.J2.adicionaPontuacao(1);
		assertEquals(4, this.J2.getNormal());	
	}
	
	@Test 
	void testGetCruzada() {
		this.J1.adicionaPontuacao(6);
		assertEquals(1, this.J1.getCruzada());
		
		this.J2.adicionaPontuacao(6);
		this.J2.adicionaPontuacao(6);
		assertEquals(2, this.J2.getCruzada());
	}
	
	@Test
	void testGetTotal() {
		this.J1.adicionaPontuacao(2);
		this.J1.adicionaPontuacao(6);
		this.J1.adicionaPontuacao(1);
		assertEquals(9, this.J1.getTotal());
		
		this.J2.adicionaPontuacao(3);
		this.J2.adicionaPontuacao(1);
		this.J2.adicionaPontuacao(6);
		assertEquals(10, this.J2.getTotal());
	}
	
}
