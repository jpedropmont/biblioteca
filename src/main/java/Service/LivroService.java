package Service;

import java.util.ArrayList;
import java.util.List;
import Model.LivroModel;

public class LivroService {
	
	private List<LivroModel> livros = new ArrayList<LivroModel>();
	private LivroModel livro;
	private int codigoDoLivro = 0;
	

	public String salvarLivro (String nomeLivro, String autor) {
		livro = new LivroModel(nomeLivro, autor);
		codigoDoLivro++;
		livro.setDisponivel(true);
		livro.setCodigo(codigoDoLivro);
		livros.add(livro);
		return "Livro adicionado com sucesso.";		
	}
	
	public String editarLivro (int codigo, String nomeLivro, String autor) {
		for (LivroModel livro : livros) {
			System.out.println("codigo passado "+ codigo);
			if (livro.getCodigo() == codigo) {
				livro.setNomeLivro(nomeLivro);
				livro.setAutor(autor);
				return "Livro " + livro.getNomeLivro() + " editado com sucesso.";
			}
		}
		System.out.println("codigo real do livro "+ livros.get(0).getCodigo());
		return "Livro " + nomeLivro + " não existe.";
	}
	
	public boolean inativarLivro (int codigo) {
		for (LivroModel livro : livros) {
			if (livro.getCodigo() == codigo) {
				livro.setDisponivel(false);
				return true;
			}
		}
		return false;
	}
	
	public boolean inativarLivro (String nomeLivro) {
		for (LivroModel livro : livros) {
			if (livro.getNomeLivro() == nomeLivro) {
				livro.setDisponivel(false);
				return true;
			}
		}
		return false;
	}
	
	public String atualizarNumeroDeExemplaresLivro (String nomeLivro, int qtd) {
		int qtdFinal;
		for(LivroModel livro : livros) {
			if(livro.getNomeLivro() == nomeLivro) {
				if (qtd == livro.getQuantidadeDeExemplares()) {
					return "Erro";
				} else if (qtd > livro.getQuantidadeDeExemplares()) {
					qtdFinal = qtd - livro.getQuantidadeDeExemplares();
					while (qtdFinal > 0) {
						livros.add(new LivroModel(livro.getNomeLivro(), livro.getAutor()));
						qtdFinal--;
					}
					livro.setQuantidadeDeExemplares(qtd);
					return "Quantidade de livros atualizada para "+qtd;
				} else {
					qtdFinal = livro.getQuantidadeDeExemplares() - qtd;
					while(qtdFinal > 0) {
						livros.remove(livro);
						qtdFinal--;
					}
					livro.setQuantidadeDeExemplares(qtd);
					return "Quantidade de livros atualizada para "+qtd;
				}
			}
		}
		return "Erro";
	}

	public List<LivroModel> getLivros() {
		return livros;
	}

	
}
