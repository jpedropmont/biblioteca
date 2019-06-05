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
	AlunoModel alunoModel;
	AlunoService alunoService;
	LivroService livroService;
	LivroModel livroModel;
	
	
	@Before
	public void instacias() {
		this.emprestimoService = new EmprestimoService();
		emprestimoModel = new EmprestimoModel();
		this.alunoService = new AlunoService();
		this.livroService = new LivroService();
		
		this.livroModel = new LivroModel("Dom Casmurro", "Machado de Assis");
		this.livroService.editarLivro(1, "Java", "João Pedro");
		this.alunoModel = new AlunoModel("João", 1710336);
		
		alunoService.salvarAluno(this.alunoModel);
		livroService.salvarLivro(livroModel);
		
		System.out.println(livroModel.getCodigo() + "  " + alunoModel.getMatricula());
	}
	
	@Test 
	public void alugarLivro() {
		assertEquals("Quando um aluno alugar um livro", "Livro alugado com sucesso", emprestimoService.alugarLivro(this.alunoModel.getMatricula(), this.livroModel.getCodigo()));
		assertEquals("Quando uma matricula ou codigo não existir", "Erro", emprestimoService.alugarLivro(-1, -1));
	}



}
