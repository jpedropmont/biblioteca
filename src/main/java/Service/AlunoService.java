package Service;

import java.util.ArrayList;
import java.util.List;

import Model.AlunoModel;

public class AlunoService {
	
	public AlunoModel aluno = new AlunoModel();
	static List<AlunoModel> alunos = new ArrayList<AlunoModel>();
	
	public String salvarAluno (AlunoModel aluno) {
		alunos.add(aluno);
		return "Aluno adicionado com sucesso.";		
	}

	public String editarAluno (AlunoModel alunoModel) {
		for (AlunoModel aluno : alunos) {
			if (aluno.getNome() == alunoModel.getNome()) {
				aluno.setNome(alunoModel.getNome());
				aluno.setMatricula(alunoModel.getMatricula());
				return "Aluno " + aluno.getNome() + " editado com sucesso.";
			}
		}
		return "Aluno " + alunoModel.getNome() + " não existe.";
	}
	
	public boolean inativarAluno (AlunoModel alunoModel) {
		for (AlunoModel aluno : alunos) {
			if (aluno.getNome() == alunoModel.getNome()) {
				alunoModel.setAtivo(false);
				return true;
			}
		}
		return false;
	}
	
	public boolean alunoExiste (AlunoModel alunoModel) {
		for (AlunoModel aluno : alunos) {
			//System.out.println(aluno.getMatricula() + " = " + matricula);
			if (aluno.getMatricula() == alunoModel.getMatricula()) {
				return true;
			}
		}
		return false;
	}

	
	
	
}
