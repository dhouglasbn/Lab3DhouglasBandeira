package src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AgendaTest {
	
	private Agenda agenda;

	@BeforeEach
	void preparaAgenda() {
		this.agenda = new Agenda();
	}

	@Test
	void cadastroPosInfTest() {
		String msg = "Espera-se que não possa cadastrar uma posição abaixo de 1.";
		
		assertFalse(this.agenda.cadastraContato(0, "Sir Criston", "Cole", "111"), msg);
	}

	@Test
	void cadastroPosSupTest() {
		String msg = "Espera-se que não possa cadastrar uma posição acima de 100.";
		
		assertFalse(this.agenda.cadastraContato(101, "Sir Criston", "Cole", "111"), msg);
	}
	
	@Test
	void cadastroPos1Test() {
		String msg = "Espera-se que possa cadastrar um contato na posição 1.";
		
		assertTrue(this
				.agenda
				.cadastraContato(
						1,
						"Lucerys",
						"Velaryon",
						"111"
						), msg);
	}
	
	@Test
	void cadastroPos100Test() {
		String msg = "Espera-se que possa cadastrar um contato na posição 100.";
		
		assertTrue(this
				.agenda
				.cadastraContato(
						100,
						"Aemond",
						"Targaryen",
						"111"
						), msg);
	}
	
	@Test
	void cadastroPosExistenteTest() {
		String msg = "Espera-se que ao cadastrar em uma posição existente,"
				+ "o contato seja substituido.";
		String expected = "Joffrey Baratheon\n222";
		agenda.cadastraContato(1, "Eddard", "Stark", "111");
		agenda.cadastraContato(1, "Joffrey", "Baratheon", "222");
		
		assertEquals(expected, agenda.exibeContato(1), msg);
	}
	
	@Test
	void cadastroJaCadastradoTest() {
		String msg = "Espera-se que não cadastre um contato duas vezes";
		agenda.cadastraContato(1, "Sir Loras", "Tyrell", "111");
		
		assertFalse(this
				.agenda
				.cadastraContato(
						1,
						"Sir Loras",
						"Tyrell",
						"111"
						), msg);
	}
	
	@Test
	void cadastroNomeVazioTest() {
		String msg = "Espera-se que não cadastre um contato com nome vazio";
		
		assertFalse(this
				.agenda
				.cadastraContato(
						1,
						"     ",
						"da Silva",
						"111"
						), msg);
	}
	
	@Test
	void cadastroTelefoneVazioTest() {
		String msg = "Espera-se que não cadastre um contato sem telefone";
		
		assertFalse(this
				.agenda
				.cadastraContato(
						1,
						"Arya",
						"Stark",
						""
						), msg);
	}
	
	@Test
	void pegaListaContatosTest() {
		String msg = "Espera-se que a lista de contatos produza o resultado.";
		String expected = "1 - Brienne de Tarth\n"
				+ "2 - Visenya Targaryen\n"
				+ "3 - Laenor Velaryon\n";
		this.agenda.cadastraContato(1, "Brienne", "de Tarth", "111");
		this.agenda.cadastraContato(2, "Visenya", "Targaryen", "222");
		this.agenda.cadastraContato(3, "Laenor", "Velaryon", "333");
		
		assertEquals(expected, this.agenda.pegaListaContatos(), msg);
	}
	
	@Test
	void exibeContatoPosInexistenteTest() {
		String msg = "Espera-se que, em posição inexistente, não exiba nada.";
		
		assertEquals("", this.agenda.exibeContato(0), msg);
	}
	
	@Test
	void exibeNaoCadastradoTest() {
		String msg = "Espera-se que, em posição não cadastrada, não exiba nada.";
		
		assertEquals("", this.agenda.exibeContato(73), msg);
	}
	
	@Test
	void exibeContatoTest() {
		String msg = "Espera-se que os dados do contato sejam exibidos no formato.";
		String expected = "Theon Greyjoy\n111";
		this.agenda.cadastraContato(1, "Theon", "Greyjoy", "111");
		
		assertEquals(expected, this.agenda.exibeContato(1), msg);
	}
	
	@Test
	void contatoJaCadastradoTest() {
		String msg = "Espera-se que a agenda identifique um contato cadastrado.";
		this.agenda.cadastraContato(1, "Rhaenys", "Targaryen", "111");
		
		assertTrue(this.agenda.contatoJaCadastrado("Rhaenys", "Targaryen"), msg);
	}
	
	@Test
	void contatoNaoCadastradoTest() {
		String msg = "Espera-se que a agenda identifique"
				+ " um contato que não foi cadastrado.";
		
		assertFalse(this.agenda.contatoJaCadastrado("Robert", "Baratheon"), msg);
	}
	
	@Test
	void favoritaPosInfTest() {
		String msg = "Espera-se que não possa favoritar em uma posição abaixo de 1.";
		this.agenda.cadastraContato(1, "Otto", "HighTower", "111");
		
		assertFalse(this.agenda.adicionaFavorito(1, 0), msg);
	}

	@Test
	void favoritaPosSupTest() {
		String msg = "Espera-se que não possa favoritar em uma posição cima de 10.";
		this.agenda.cadastraContato(1, "Myrcella", "Baratheon", "111");
		
		assertFalse(this.agenda.adicionaFavorito(1, 11), msg);
	}
	
	@Test
	void favoritaPos1Test() {
		String msg = "Espera-se que a agenda favorite um contato na posição 1.";
		this.agenda.cadastraContato(1, "Aerys", "Targaryen", "111");
		
		assertTrue(this.agenda.adicionaFavorito(1, 1), msg);
		assertTrue(this.agenda.getContato(1).isFavorito());
	}
	
	@Test
	void favoritaPos10Test() {
		String msg = "Espera-se que a agenda favorite um contato na posição 10.";
		
		this.agenda.cadastraContato(1, "Baela", "Velaryon", "111");
		
		assertTrue(this.agenda.adicionaFavorito(1, 10), msg);
		assertTrue(this.agenda.getContato(1).isFavorito());
	}
	
	@Test
	void favoritaContatoFavoritoTest() {
		String msg = "Espera-se que a agenda não favorite um contato já favoritado.";
		this.agenda.cadastraContato(1, "Jahaerys", "Targaryen", "111");
		this.agenda.adicionaFavorito(1, 2);
		
		assertFalse(this.agenda.adicionaFavorito(1, 1), msg);
	}
	
	@Test
	void substituiFavoritoTest() {
		String msg = "Espera-se que a agenda substitua um contato favorito.";
		String msg2 = "Espera-se que o contato 2 seja favorito.";
		String msg3 = "Espera-se que o contato 1 não seja favorito";
		this.agenda.cadastraContato(1, "Margaery", "Tyrell", "111");
		this.agenda.cadastraContato(2, "Oberyn", "Martell", "222");
		this.agenda.adicionaFavorito(1, 1);
		
		assertTrue(this.agenda.adicionaFavorito(2, 1), msg);
		assertTrue(this.agenda.getContato(2).isFavorito(), msg2);
		assertFalse(this.agenda.getContato(1).isFavorito(), msg3);
	}
	
	@Test
	void pegaFavoritosFormatadoTest() {
		String msg = "Espera-se que a agenda retorne a lista esperada.";
		String expected = "1 - Tiwin Lannister\n"
				+ "2 - Arryk Cargyll\n"
				+ "3 - Ramsay Bolton\n";
		
		this.agenda.cadastraContato(1, "Tiwin", "Lannister", "111");
		this.agenda.cadastraContato(2, "Arryk", "Cargyll", "222");
		this.agenda.cadastraContato(3, "Ramsay", "Bolton", "333");
		this.agenda.adicionaFavorito(1, 1);
		this.agenda.adicionaFavorito(2, 2);
		this.agenda.adicionaFavorito(3, 3);
		
		assertEquals(expected, this.agenda.pegaFavoritosFormatado(), msg);
	}
	
	@Test
	void removeFavPosNulaTest() {
		String msg = "Espera-se que a agenda não remova alguém que ainda não é favorito.";
		
		assertFalse(this.agenda.removeFavorito(1), msg);
	}
	
	@Test
	void removeFavPosInfTest() {
		String msg = "Espera-se que a agenda não remova "
				+ "alguém em posição abaixo de 1.";
		
		assertFalse(this.agenda.removeFavorito(0), msg);
	}
	
	@Test
	void removeFavPosSupTest() {
		String msg = "Espera-se que a agenda não remova "
				+ "alguém em posição acima de 10.";
		
		assertFalse(this.agenda.removeFavorito(11), msg);
	}
	
	@Test
	void removeFavPos1Test() {
		String msg = "Espera-se que a agenda remova "
				+ "alguém na posição 1.";
		this.agenda.cadastraContato(1, "Rhaegar", "Targaryen", "111");
		this.agenda.adicionaFavorito(1, 1);
		
		assertTrue(this.agenda.removeFavorito(1), msg);
		assertFalse(this.agenda.getContato(1).isFavorito(), msg);
	}
	
	@Test
	void removeFavPos10Test() {
		String msg = "Espera-se que a agenda remova "
				+ "alguém na posição 10.";
		this.agenda.cadastraContato(1, "Tyrion", "Lannister", "111");
		this.agenda.adicionaFavorito(1, 10);
		
		assertTrue(this.agenda.removeFavorito(10));
		assertFalse(this.agenda.getContato(1).isFavorito(), msg);
	}
	
	@Test
	void contatoJaFavoritadoTest() {
		String msg = "Espera-se que a agenda encontre um contato favorito.";
		this.agenda.cadastraContato(1, "Stannis", "Baratheon", "111");
		this.agenda.adicionaFavorito(1, 7);
		
		assertTrue(this.agenda.contatoJaFavoritado(1), msg);
	}
	
	@Test
	void contatoNaoFavoritadoTest() {
		String msg = "Espera-se que a agenda não encontre um contato favorito.";
		this.agenda.cadastraContato(1, "Haelena", "Targaryen", "111");
		
		assertFalse(this.agenda.removeFavorito(1), msg);
	}
}
