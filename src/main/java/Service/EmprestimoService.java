package Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import Model.AlunoModel;
import Model.EmprestimoModel;
import Model.LivroModel;

public class EmprestimoService {
	
	private int codigoEmprestimo = 0;
	
	// Chave: Código do empréstimo - Valor: EmprestimoModel 
	private static HashMap<Integer, EmprestimoModel> emprestimos = new HashMap<Integer, EmprestimoModel>();
	private EmprestimoModel emprestimo;

	// Chave: Código do livro - Valor: Matrícula do aluno
	private HashMap<Integer, Integer> livrosReservados = new HashMap<Integer, Integer>();
	
	private AlunoService alunoService;
	private List<AlunoModel> alunos = alunoService.getAlunos();
	
	private LivroService livroService;
	private List<LivroModel> livros = livroService.getLivros();
	
	
	public String alugarLivro (int matricula, int codigo) {
		if (alunoExiste(matricula) && livroExiste(codigo)) {
			if (qtdDeLivrosAlugadosPeloAluno(matricula) < 3) {
				if (livrosReservados.get(codigo) != null) {
					livrosReservados.remove(codigo);
				}
				criaEmprestimo(matricula, codigo);
				return "Livro alugado com sucesso";
			} else {
				return "Aluno já está com o limite de livros alugados";
			}
		} else {
			return "Erro";
		}
	}
	
	
	public String reservarLivro (int matricula, String nomeLivro) {
		LivroModel livroASerReservado = null;
		for (LivroModel livro : livros) {
			if (livro.getNomeLivro() == nomeLivro) {
				if (livro.isDisponivel()) {
					return "Livro disponível para ser alugado";
				}
				livroASerReservado = livro;
			}
		}
		if (livroASerReservado != null) {
			livrosReservados.put(livroASerReservado.getCodigo(), matricula);
			return "Livro reservado";
		}
		return "Erro";	
	}
	
	
	public void renovarEmprestimo (int codigoLivro) {
		if (!livrosReservados.containsKey(codigoLivro)) {
			for (Entry<Integer, EmprestimoModel> emprestimo : emprestimos.entrySet()) {
				if (emprestimo.getValue().getLivro().getCodigo() == codigoLivro) {
					emprestimo.getValue().setDataDaDevolucao(emprestimo.getValue().getDataDaDevolucao().getDate() + 10);
				}
			}
		}
	}
	
	public AlunoModel aluno (int matricula) {
		for (AlunoModel aluno : alunos) {
			if (
					aluno.getMatricula() == matricula) {
				return aluno;
			}
		}
		return null;
	}
	
	public LivroModel livro (int codigo) {
		for (LivroModel livro : livros) {
			if (livro.getCodigo() == codigo) {
				return livro;
			}
		}
		return null;
	}
	
	public boolean alunoExiste (int matricula) {
		for (AlunoModel aluno : alunos) {
			if (aluno.getMatricula() == matricula) {
				return true;
			}
		}
		return false;
	}
	
	public boolean livroExiste (int codigo) {
		for (LivroModel livro : livros) {
			if (livro.getCodigo() == codigo) {
				return true;
			}
		}
		return false;
	}
	
	public int qtdDeLivrosAlugadosPeloAluno (int matricula) {
		int quantidadeDeLivrosAlugadosDoAluno = 0;
		for (Entry<Integer, EmprestimoModel> emprestimo : emprestimos.entrySet()) {
			if (emprestimo.getValue().getAluno().getMatricula() == matricula) {
				quantidadeDeLivrosAlugadosDoAluno++;
			}
		}
		return quantidadeDeLivrosAlugadosDoAluno;
	}
	
	public void criaEmprestimo (int matricula, int codigo) {
		Date dataDoEmprestimo = new Date();
		Date dataDaDevolucao = new Date(dataDoEmprestimo.getDate() + 10);
		codigoEmprestimo += 1;
		livro(codigo).setDisponivel(false);
		emprestimo = new EmprestimoModel(aluno(matricula), livro(codigo), codigoEmprestimo, dataDoEmprestimo, dataDaDevolucao);
		emprestimos.put(codigoEmprestimo,emprestimo);
	}

	public static HashMap<Integer, EmprestimoModel> getEmprestimos() {
		return emprestimos;
	}

	
}
