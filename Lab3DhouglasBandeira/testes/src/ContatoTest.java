package src;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

class ContatoTest {
	
	private Contato contato;
	
	@BeforeEach
	void criaContato() {
		this.contato = new Contato("Dhouglas", "Bandeira", "111");
	}

	@Test
	void alteraNomeTest() {
		String msg = "Espera-se que o nome agora seja Rhaenyra.";
		this.contato.alteraNome("Rhaenyra");
		assertEquals("Rhaenyra", this.contato.pegaNome(), msg);
	}
	
	@Test
	void alteraSobrenomeTest() {
		String msg = "Espera-se que o sobrenome seja alterado para Targaryen.";
		this.contato.alteraSobrenome("Targaryen");
		assertEquals("Targaryen", this.contato.pegaSobrenome(), msg);
	}
	
	@Test
	void equalsTest() {
		String msg = "Espera-se que os contatos sejam iguais (nome e sobrenome).";
		Contato contatoTeste = new Contato("Dhouglas", "Bandeira", "1");
		assertTrue(this.contato.equals(contatoTeste), msg);
	}
	
	@Test
	void notEqualsTest() {
		String msg = "Espera-se que os contatos sejam diferentes(nome e sobrenome).";
		Contato contatoTeste = new Contato("Alicent", "HighTower", "1");
		assertFalse(this.contato.equals(contatoTeste), msg);
	}
	
	@Test
	void toStringTest() {
		String msg = "Espera-se que o toString produza a saída esperada.";
		String expected = "Dhouglas Bandeira\n111";
		assertEquals(expected, this.contato.toString(), msg);
	}

	@Test
	void favoritoToStringTest() {
		String msg = "Espera-se que o toString produza a saída esperada para favorito.";
		String expected = "❤️ Dhouglas Bandeira\n111";
		this.contato.setFavorito(true);
		assertEquals(expected, this.contato.toString(), msg);
	}
}
