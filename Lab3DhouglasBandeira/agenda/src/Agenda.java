package src;

/**
 * Uma agenda que mantém uma lista de contatos com posições. Podem existir 100 contatos. 
 * 
 * @author nazareno
 * @author Dhouglas Bandeira
 *
 */
public class Agenda {
	
	// Por algum motivo os Arrays estão começando a contagem em 1.
	private static final int TAMANHO_AGENDA = 101;
	
	private static final int TAMANHO_FAVORITO = 11;
	
	private Contato[] contatos;
	
	private Contato[] favoritos;

	/**
	 * Cria uma agenda.
	 */
	public Agenda() {
		this.contatos = new Contato[TAMANHO_AGENDA];
		this.favoritos = new Contato[TAMANHO_FAVORITO];
	}
	
	/**
	 * Acessa a lista de contatos mantida.
	 * @return O array de contatos.
	 */
	public Contato[] getContatos() {
		return this.contatos.clone();
	}
	
	public Contato getContato(int posicao) {
		return this.contatos[posicao];
	}

	/**
	 * Retorna uma lista formatada dos contatos.
	 * @return lista com nomes e sobrenomes.
	 */
	public String pegaListaContatos() {
		String retorno = "";
		for (int index = 1; index < this.contatos.length; index++) {
			if (this.contatos[index] != null) {
				retorno += (index) + " - " + this.contatos[index].pegaNome() + 
						" " + this.contatos[index].pegaSobrenome() + "\n";				
			}
		}
		return retorno;
	}
	
	/**
	 * Acessa os dados de um contato específico.
	 * @param posicao Posição do contato na agenda.
	 * @return Dados do contato. Null se não há contato na posição.
	 */
	public String exibeContato(int posicao) {
		if (this.posicaoIncadastravel(posicao)) {
			return "";
		}
		if (this.contatos[posicao] == null) {
			return "";
		}
		return contatos[posicao].toString();
	}

	/**
	 * Cadastra um contato em uma posição. Um cadastro em uma posição que já existe sobrescreve o anterior. 
	 * @param posicao Posição do contato.
	 * @param nome Nome do contato.
	 * @param sobrenome Sobrenome do contato.
	 * @param telefone Telefone do contato.
	 */
	public boolean cadastraContato(int posicao, String nome, String sobrenome, String telefone) {
		if (this.posicaoIncadastravel(posicao)) {
			System.out.println("POSIÇÃO INVÁLIDA");
			return false;
		}
		if (this.contatoJaCadastrado(nome, sobrenome)) {
			System.out.println("CONTATO JA CADASTRADO");
			return false;
		}
		if (nome.isBlank()) {
			System.out.println("CONTATO INVALIDO");
			return false;
		}
		if (telefone.isBlank()) {
			System.out.println("CONTATO INVALIDO");
			return false;
		}
		
		this.contatos[posicao] = new Contato(nome, sobrenome, telefone);
		return true;
	}
	
	/**
	 * Verifica se já existe um contato com o mesmo nome e sobrenome
	 * 
	 * @param nome
	 * @param sobrenome
	 * @return se ja existir o nome e sobrenome - true, else - false
	 */
	public boolean contatoJaCadastrado(String nome, String sobrenome) {
		Contato oContato = new Contato(nome, sobrenome, "");
		for (Contato contato: this.contatos) {
			if (oContato.equals(contato)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adiciona um contato aos favoritos.
	 * 
	 * @param contato
	 * @param posicao
	 */
	public boolean adicionaFavorito(int contato, int posicao) {
		if (this.favoritoIncadastravel(posicao)) {
			System.out.println("POSIÇÃO INVÁLIDA");
			return false;
		}
		if (this.posicaoIncadastravel(contato)) {
			System.out.println("POSIÇÃO INVÁLIDA");
			return false;
		}
		if (contatoJaFavoritado(contato)) {
			System.out.println("CONTATO JÁ É FAVORITO");
			return false;
		}
		
		this.contatos[contato].setFavorito(true);
		
		if (this.favoritos[posicao] != null) {
			this.favoritos[posicao].setFavorito(false);
		}
		
		this.favoritos[posicao] = this.contatos[contato];
		System.out.println("CONTATO FAVORITADO NA POSIÇÃO " + posicao + "!\n");
		return true;
	}
	
	/**
	 * Remove um contato dos favoritos.
	 * 
	 * @param posicao
	 */
	public boolean removeFavorito(int posicao) {
		if (this.favoritoIncadastravel(posicao)) {
			return false;
		}
		if (this.favoritos[posicao] == null) {
			return false;
		}
		this.favoritos[posicao].setFavorito(false);
		this.favoritos[posicao] = null;
		return true;
	}
	
	/**
	 * Retorna uma lista dos contatos favoritos formatada.
	 * @return lista com nomes e sobrenomes.
	 */
	public String pegaFavoritosFormatado() {
		String retorno = "";
		for (int index = 1; index < this.favoritos.length; index++) {
			if (this.favoritos[index] != null) {
				retorno += index + " - " + this.favoritos[index].pegaNome() +
						" " + this.favoritos[index].pegaSobrenome() + "\n";
			}
		}
		return retorno;
	}
	
	/**
	 * Verifica se o contato já está na lista de favoritos.
	 * 
	 * @param posicao
	 * @return true - contato já está nos favoritos, false - não está.
	 */
	public boolean contatoJaFavoritado(int posicao) {
		for (int index = 1; index < TAMANHO_FAVORITO; index++) {
			if (this.favoritos[index] != null) {
				if (this.favoritos[index].equals(this.contatos[posicao])) {
					return true;
				}				
			}
		}
		return false;
	}
	
	/**
	 * Verifica se a posição esta dentro do intervalo de numeros cadastráveis.
	 * 
	 * @param posicao
	 * @return pode cadastrar - true, não pode - false
	 */
	private boolean posicaoIncadastravel(int posicao) {
		if (posicao < 1 || posicao >= TAMANHO_AGENDA) {
				return true;
		}
		return false;
	}
	
	/**
	 * Verifica se a posição esta dentro do intervalo de favoritos cadastráveis.
	 * 
	 * @param posicao
	 * @return pode cadastrar - true, não pode - false
	 */
	private boolean favoritoIncadastravel(int posicao) {
		if (posicao < 1 || posicao >= TAMANHO_FAVORITO) {
				return true;
		}
		return false;
	}
}
