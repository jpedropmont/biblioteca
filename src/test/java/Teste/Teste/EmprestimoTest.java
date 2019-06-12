package Teste.Teste;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Model.AlunoModel;
import Model.EmprestimoModel;
import Model.LivroModel;
import Service.AlunoService;
import Service.EmprestimoService;
import Service.LivroService;

public class EmprestimoTest {
	
	EmprestimoModel emprestimoModel;
	EmprestimoService emprestimoService;
	AlunoModel alunoModel, alunoModel2, alunoModel3;
	AlunoService alunoService;
	LivroService livroService;
	LivroModel livroModel, livroModel2;
	
	
	@Before
	public void instacias() {
		this.emprestimoService = new EmprestimoService();
		emprestimoModel = new EmprestimoModel();
		this.alunoService = new AlunoService();
		this.livroService = new LivroService();
		this.alunoService = new AlunoService();
		this.livroService = new LivroService();
		
		this.livroModel = new LivroModel("Dom Casmurro", "Machado de Assis");
		this.livroModel2 = new LivroModel("Dom Casmurro", "Machado de Assis");
		this.livroService.editarLivro(1, "Java", "João Pedro");
		this.alunoModel = new AlunoModel("João", 1710336);
		this.alunoModel2 = new AlunoModel("João", 1710336);
		this.alunoModel3 = new AlunoModel();
		
		alunoService.salvarAluno(this.alunoModel);
		livroService.salvarLivro(livroModel);
		
		System.out.println(livroModel.getCodigo() + "  " + alunoModel.getMatricula());
	}
	
	@Test 
	public void alugarLivro() {
		assertEquals("Quando um aluno alugar um livro", "Livro alugado com sucesso", emprestimoService.alugarLivro(this.alunoModel, this.livroModel));
		assertEquals("Quando uma matricula ou codigo não existir", "O livro não está disponível no momento", emprestimoService.alugarLivro(this.alunoModel2, this.livroModel2));
		assertEquals("Quando um dos dados estiver incorreto", "Erro", emprestimoService.alugarLivro(alunoModel3, livroModel2));
}

	@Test 
	public void reservarLivro() {
		assertEquals("Quando um aluno reservar um livro e ele estiver disponivel", "Livro disponível para ser alugado", emprestimoService.reservarLivro(this.alunoModel, this.livroModel));
		livroModel2.setDisponivel(false);
		assertEquals("Quando um aluno reservar um livro e ele não estiver disponivel", "Livro reservado", emprestimoService.reservarLivro(this.alunoModel, this.livroModel2));
		assertEquals("Quando um dos dados estiver incorreto", "Erro", emprestimoService.alugarLivro(alunoModel3, livroModel2));
}


}
