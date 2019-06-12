package Teste.Teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Model.AlunoModel;
import Service.AlunoService;

public class AlunoTest {
	
	AlunoModel alunoModel, alunoModel2;
	AlunoService alunoService;
	
	@Before
	public void instaciaAluno() {
		
		this.alunoModel = new AlunoModel("João", 1710336);
		this.alunoModel2 = new AlunoModel("João 2", 17103362);
		this.alunoService = new AlunoService();
		
	}
	
	@Test
	public void salvarAluno () {
		assertEquals("Quando for salvo um aluno, retornar mensagem de sucesso", "Aluno adicionado com sucesso.", alunoService.salvarAluno(alunoModel));
	}
	
	@Test
	public void editarAluno () {
		assertEquals("Se for editar um aluno e ele não existir, retornar mensagem de erro.", "Aluno " + alunoModel2.getNome() + " não existe.", alunoService.editarAluno(alunoModel2));
		assertEquals("Para editar um aluno, primeiramente ele tem que existir no sistema.", "Aluno adicionado com sucesso.", alunoService.salvarAluno(alunoModel));
		assertEquals("Quando editado um aluno, retornar mensagem de sucesso.", "Aluno " + alunoModel.getNome() + " editado com sucesso.", alunoService.editarAluno(alunoModel));
	}
	
	@Test
	public void inativarAluno () {
		assertEquals("Se for inativar um aluno e ele não existir, retornar mensagem de erro.", false, alunoService.inativarAluno(alunoModel2));
		assertEquals("Para inativar um aluno, primeiramente ele tem que existir no sistema.", "Aluno adicionado com sucesso.", alunoService.salvarAluno(alunoModel));
		assertTrue("Quando inativado um aluno, retornar true.", alunoService.inativarAluno(alunoModel));
	}
}
