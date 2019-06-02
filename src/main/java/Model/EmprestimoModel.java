package Model;

public class EmprestimoModel {
	
	private LivroModel livro;
	private AlunoModel aluno;
	private int codigoEmprestimo;
	
	public EmprestimoModel(AlunoModel aluno, LivroModel livro, int codigoEmprestimo) {
		this.aluno = aluno;
		this.livro = livro;
		this.codigoEmprestimo = codigoEmprestimo;
	}
	
	public LivroModel getLivro() {
		return livro;
	}
	public void setLivro(LivroModel livro) {
		this.livro = livro;
	}
	public AlunoModel getAluno() {
		return aluno;
	}
	public void setAluno(AlunoModel aluno) {
		this.aluno = aluno;
	}
	public int getCodigoEmprestimo() {
		return codigoEmprestimo;
	}
	public void setCodigoEmprestimo(int codigoEmprestimo) {
		this.codigoEmprestimo = codigoEmprestimo;
	}
	
	
	
}
