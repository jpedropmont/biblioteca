package Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.AlunoModel;
import Model.EmprestimoModel;
import Model.LivroModel;

public class EmprestimoService {
	
	private List<EmprestimoModel> emprestimos = new ArrayList<EmprestimoModel>();
	private EmprestimoModel emprestimo;
	private int codigoEmprestimo = 0;
	private Date dataDoEmprestimo;
	private Date dataDaDevolucao;
	
	private HashMap<Integer, Integer> livrosReservados = new HashMap<Integer, Integer>();
	
	private AlunoService alunoService;
	private List<AlunoModel> alunos = alunoService.getAlunos();
	
	private LivroService livroService;
	private List<LivroModel> livros = livroService.getLivros();
	
	//matricula ao invez de nome
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
	
	public AlunoModel aluno (int matricula) {
		for (AlunoModel aluno : alunos) {
			if (aluno.getMatricula() == matricula) {
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
		for (EmprestimoModel emprestimo : emprestimos) {
			if (emprestimo.getAluno().getMatricula() == matricula) {
				quantidadeDeLivrosAlugadosDoAluno++;
			}
		}
		return quantidadeDeLivrosAlugadosDoAluno;
	}
	
	public void criaEmprestimo (int matricula, int codigo) {
		codigoEmprestimo += 1;
		dataDoEmprestimo = new Date();
		dataDaDevolucao = new Date(dataDoEmprestimo.getDay() + 10);
		livro(codigo).setDisponivel(false);
		emprestimo = new EmprestimoModel(aluno(matricula), livro(codigo), codigoEmprestimo);
		emprestimos.add(emprestimo);
	}

	
}
