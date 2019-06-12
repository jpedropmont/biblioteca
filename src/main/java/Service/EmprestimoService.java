package Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import Model.AlunoModel;
import Model.EmprestimoModel;
import Model.LivroModel;

@SuppressWarnings("deprecation")
public class EmprestimoService {
	
	private int codigoEmprestimo = 0;
	
	// Chave: Código do empréstimo - Valor: EmprestimoModel 
	private static HashMap<Integer, EmprestimoModel> emprestimos = new HashMap<Integer, EmprestimoModel>();
	private EmprestimoModel emprestimo;

	// Chave: Código do livro - Valor: Matrícula do aluno
	private HashMap<Integer, Integer> livrosReservados = new HashMap<Integer, Integer>();
	
	private AlunoService alunoService = new AlunoService();
	
	private LivroService livroService = new LivroService();
	private List<LivroModel> livros = livroService.getLivros();
	
	
	public String alugarLivro (AlunoModel alunoModel, LivroModel livroModel) {
		if (alunoService.alunoExiste(alunoModel) && livroService.livroExiste(livroModel.getCodigo())) {
			if (qtdDeLivrosAlugadosPeloAluno(alunoModel.getMatricula()) < 3) {
				if(livro(livroModel.getCodigo()).isDisponivel()) {
					if (livrosReservados.get(livroModel.getCodigo()) != null) {
						livrosReservados.remove(livroModel.getCodigo());
					}
					criaEmprestimo(alunoModel, livroModel);
					return "Livro alugado com sucesso";
				} else {
					return "O livro não está disponível no momento";
				}
				
			} else {
				return "Aluno já está com o limite de livros alugados";
			}
		} else {
			return "Erro";
		}
	}
	
	
	public String reservarLivro (AlunoModel alunoModel, LivroModel livroModel) {
		LivroModel livroASerReservado = null;
		for (LivroModel livro : livros) {
			if (livro.getNomeLivro() == livroModel.getNomeLivro()) {
				if (livroModel.isDisponivel()) {
					return "Livro disponível para ser alugado";
				}
				livroASerReservado = livro;
			}
		}
		if (livroASerReservado != null) {
			livrosReservados.put(livroASerReservado.getCodigo(), alunoModel.getMatricula());
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
		for (AlunoModel aluno : AlunoService.alunos) {
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
	
	
	public int qtdDeLivrosAlugadosPeloAluno (int matricula) {
		int quantidadeDeLivrosAlugadosDoAluno = 0;
		for (Entry<Integer, EmprestimoModel> emprestimo : emprestimos.entrySet()) {
			if (emprestimo.getValue().getAluno().getMatricula() == matricula) {
				quantidadeDeLivrosAlugadosDoAluno++;
			}
		}
		return quantidadeDeLivrosAlugadosDoAluno;
	}
	
	public void criaEmprestimo (AlunoModel alunoModel, LivroModel livroModel) {
		
		Date dataDoEmprestimo = new Date();
		Date dataDaDevolucao = new Date(dataDoEmprestimo.getDate() + 10);
		codigoEmprestimo += 1;
		livro(livroModel.getCodigo()).setDisponivel(false);
		emprestimo = new EmprestimoModel(aluno(alunoModel.getMatricula()), livro(livroModel.getCodigo()), codigoEmprestimo, dataDoEmprestimo, dataDaDevolucao);
		emprestimos.put(codigoEmprestimo,emprestimo);
	}

	public static HashMap<Integer, EmprestimoModel> getEmprestimos() {
		return emprestimos;
	}

	
}
