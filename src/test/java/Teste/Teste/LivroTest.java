package Teste.Teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Model.LivroModel;
import Service.LivroService;

public class LivroTest {
	
	
	LivroService livroService;
	LivroModel livroModel;
	
	@Before
	public void instanciarLivro () {
		this.livroService = new LivroService();
		this.livroModel = new LivroModel("Dom Casmurro", "Machado de Assis");
	}
	
	@Test
	public void salvarLivro () {
		assertEquals("Quando for cadastrado um livro, a mensagem deve ser de ", "Livro adicionado com sucesso.", livroService.salvarLivro(livroModel.getNomeLivro(), livroModel.getAutor()));
	}
	
	@Test
	public void editarLivro () {
		assertEquals("Quando for editar um livro, e ele não existir, retornar uma mensagem de erro", "Livro " + livroModel.getNomeLivro() + " não existe.", livroService.editarLivro(livroModel.getNomeLivro(), livroModel.getAutor()));
		assertEquals("Quando for editar um livro, primeiro ele tem que ser adicionado", "Livro adicionado com sucesso.", livroService.salvarLivro(livroModel.getNomeLivro(), livroModel.getAutor()));
		assertEquals("Quando for editar um livro, retornar uma mensagem de sucesso", "Livro " + livroModel.getNomeLivro() + " editado com sucesso.", livroService.editarLivro(livroModel.getNomeLivro(), livroModel.getAutor()));
	}

	@Test
	public void inativarLivro() {
		assertEquals("Quando for inativar um livro, e ele não existir, retornar uma mensagem de erro", false, livroService.inativarLivro(livroModel.getNomeLivro()));
		assertEquals("Primeiramente salvamos o livro para inativá-lo.", "Livro adicionado com sucesso.", livroService.salvarLivro(livroModel.getNomeLivro(), livroModel.getAutor()));
		assertTrue("Quando inativado um livro, retornar true.", livroService.inativarLivro(livroModel.getNomeLivro()));
	}
	
	@Test
	public void atualizarNumeroDeExemplaresLivro() {
		assertEquals("Primeiramente salvamos o livro para inativá-lo.", "Livro adicionado com sucesso.", livroService.salvarLivro(livroModel.getNomeLivro(), livroModel.getAutor()));
		assertEquals("Quando for atualizar o numero de exemplares do livro, retornar a quantidade atualizada.", "Quantidade do livro incrementada", livroService.atualizarNumeroDeExemplaresLivro(livroModel.getNomeLivro()));
	}
	

}
