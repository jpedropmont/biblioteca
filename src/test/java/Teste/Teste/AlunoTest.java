package Teste.Teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Model.AlunoModel;
import Service.AlunoService;

public class AlunoTest {
	
	AlunoModel alunoModel;
	AlunoService alunoService;
	
	@Before
	public void instaciaAluno() {
		
		this.alunoModel = new AlunoModel("João", 1710336);
		this.alunoService = new AlunoService();
		
	}
	
	@Test
	public void salvarAluno () {
		assertEquals("Quando for salvo um aluno, retornar mensagem de sucesso", "Aluno adicionado com sucesso.", alunoService.salvarAluno(alunoModel.getNome(), alunoModel.getMatricula()));
	}
	
	@Test
	public void editarAluno () {
		assertEquals("Se for editar um aluno e ele não existir, retornar mensagem de erro.", "Aluno " + alunoModel.getNome() + " não existe.", alunoService.editarAluno(alunoModel.getNome(), alunoModel.getMatricula()));
		assertEquals("Para editar um aluno, primeiramente ele tem que existir no sistema.", "Aluno adicionado com sucesso.", alunoService.salvarAluno(alunoModel.getNome(), alunoModel.getMatricula()));
		assertEquals("Quando editado um aluno, retornar mensagem de sucesso.", "Aluno " + alunoModel.getNome() + " editado com sucesso.", alunoService.editarAluno(alunoModel.getNome(), alunoModel.getMatricula()));
	}
	
	@Test
	public void inativarAluno () {
		assertEquals("Se for inativar um aluno e ele não existir, retornar mensagem de erro.", false, alunoService.inativarAluno(alunoModel.getNome()));
		assertEquals("Para inativar um aluno, primeiramente ele tem que existir no sistema.", "Aluno adicionado com sucesso.", alunoService.salvarAluno(alunoModel.getNome(), alunoModel.getMatricula()));
		assertTrue("Quando inativado um aluno, retornar true.", alunoService.inativarAluno(alunoModel.getNome()));
	}
}
