package agenda;

import java.util.Objects;

public class Contato {
	
	private String nome;

	private String sobrenome;

	private String telefone;

	public Contato(String nome, String sobrenome, String telefone) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefone = telefone;
	}
	
	
	public String pegaNome() {
		return nome;
	}
	
	
	public void alteraNome(String nome) {
		this.nome = nome;
	}
	
	public String pegaSobrenome() {
		return sobrenome;
	}
	
	
	public void alteraSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String pegaTelefone() {
		return telefone;
	}


	public void alteraTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public boolean equals(Object contato) {
		if (this == contato) {
			return true;			
		}
		if (contato == null) {
			return false;			
		}
		if (getClass() != contato.getClass()) {
			return false;			
		}
		Contato other = (Contato) contato;
		return Objects.equals(nome, other.nome)
				&& Objects.equals(sobrenome, other.sobrenome);
	}
}
