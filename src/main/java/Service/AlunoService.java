package Service;

import java.util.ArrayList;
import java.util.List;

import Model.AlunoModel;
import Model.LivroModel;

public class AlunoService {
	
	List<AlunoModel> alunos = new ArrayList<AlunoModel>();
	AlunoModel aluno;
	
	public String salvarAluno (String nomeAluno, int matricula) {
		aluno = new AlunoModel(nomeAluno, matricula);
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
	
	public List<AlunoModel> getAlunos() {
		return alunos;
	}
	
}
