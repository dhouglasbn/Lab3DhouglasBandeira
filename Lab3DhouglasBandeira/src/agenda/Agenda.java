package agenda;

/**
 * Uma agenda que mantém uma lista de contatos com posições. Podem existir 100 contatos. 
 * 
 * @author nazareno
 * @author Dhouglas Bandeira
 *
 */
public class Agenda {
	
	private static final int TAMANHO_AGENDA = 100;
	
	private static final int TAMANHO_FAVORITO = 10;
	
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
	public Contato getContato(int posicao) {
		return contatos[posicao];
	}

	/**
	 * Cadastra um contato em uma posição. Um cadastro em uma posição que já existe sobrescreve o anterior. 
	 * @param posicao Posição do contato.
	 * @param nome Nome do contato.
	 * @param sobrenome Sobrenome do contato.
	 * @param telefone Telefone do contato.
	 */
	public void cadastraContato(int posicao, String nome, String sobrenome, String telefone) {
		
		this.contatos[posicao] = new Contato(nome, sobrenome, telefone);
	}
	
	/**
	 * Verifica se já existe um contato com o mesmo nome e sobrenome
	 * 
	 * @param nome
	 * @param sobrenome
	 * @return se ja existir o nome e sobrenome - true, else - false
	 */
	public boolean contatoJaExiste(String nome, String sobrenome) {
		Contato oContato = new Contato(nome, sobrenome, "");
		for (Contato contato: this.contatos) {
			if (oContato.equals(contato)) {
				return true;
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
	public boolean contatoIncadastravel(int posicao) {
		if ((posicao - 1) < 0 || (posicao - 1) > TAMANHO_AGENDA) {
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
	public boolean favoritoIncadastravel(int posicao) {
		if ((posicao - 1) < 0 || (posicao - 1) > TAMANHO_FAVORITO) {
				return true;
		}
		return false;
	}
	
	public void adicionaFavorito(int contato, int posicao) {
		if (contatoJaFavoritado(contato)) {
			return;
		}
		
		this.contatos[contato].setFavorito(true);
		
		if (this.favoritos[posicao] != null) {
			this.favoritos[posicao].setFavorito(false);
		}
		
		this.favoritos[posicao] = this.contatos[contato];
	}
	
	public void removeFavorito(int posicao) {
		if (this.favoritos[posicao] == null) {
			return;
		}
		this.favoritos[posicao].setFavorito(false);
		this.favoritos[posicao] = null;
	}
	
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
}
