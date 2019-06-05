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

	public String editarAluno (String nomeAluno, int matricula) {
		for (AlunoModel aluno : alunos) {
			if (aluno.getNome() == nomeAluno) {
				aluno.setNome(nomeAluno);
				aluno.setMatricula(matricula);
				return "Aluno " + aluno.getNome() + " editado com sucesso.";
			}
		}
		return "Aluno " + nomeAluno + " não existe.";
	}
	
	public boolean inativarAluno (String nomeAluno) {
		for (AlunoModel aluno : alunos) {
			if (aluno.getNome() == nomeAluno) {
				aluno.setAtivo(false);
				return true;
			}
		}
		return false;
	}
	
	public boolean alunoExiste (int matricula) {
		for (AlunoModel aluno : alunos) {
			//System.out.println(aluno.getMatricula() + " = " + matricula);
			if (aluno.getMatricula() == matricula) {
				return true;
			}
		}
		return false;
	}

	
	
	
}
