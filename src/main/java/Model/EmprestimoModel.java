package Model;

import java.util.Date;

public class EmprestimoModel {
	
	private LivroModel livro;
	private AlunoModel aluno;
	private int codigoEmprestimo;
	private Date dataDoEmprestimo;
	private Date dataDaDevolucao;

	
	
	public EmprestimoModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EmprestimoModel(AlunoModel aluno, LivroModel livro, int codigoEmprestimo, Date dataDoEmprestimo, Date dataDaDevolucao) {
		super();
		this.livro = livro;
		this.aluno = aluno;
		this.codigoEmprestimo = codigoEmprestimo;
		this.dataDoEmprestimo = dataDoEmprestimo;
		this.dataDaDevolucao = dataDaDevolucao;
	}

	public Date getDataDoEmprestimo() {
		return dataDoEmprestimo;
	}

	public void setDataDoEmprestimo(Date dataDoEmprestimo) {
		this.dataDoEmprestimo = dataDoEmprestimo;
	}
	
	public Date getDataDaDevolucao() {
		return dataDaDevolucao;
	}

	@SuppressWarnings("deprecation")
	public void setDataDaDevolucao(int i) {
		this.dataDaDevolucao.setDate(i);
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
