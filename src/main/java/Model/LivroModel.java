package Model;

public class LivroModel {
	
	private String nomeLivro;
	private String autor;
	private boolean disponivel = false;
	private static int quantidadeDeExemplares = 0;
	private int codigo;
	
	public int getQuantidadeDeExemplares() {
		return quantidadeDeExemplares;
	}

	public void setQuantidadeDeExemplares(int quantidadeDeExemplares) {
		LivroModel.quantidadeDeExemplares = quantidadeDeExemplares;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public LivroModel(String nomeLivro, String autor) {
		this.nomeLivro = nomeLivro;
		this.autor = autor;
	}

	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	
}
