package Model;

public class AlunoModel {
	
	private String nome;
	private int matricula;
	private boolean ativo;
	
	
	
	
	public AlunoModel() {
		super();
	}

	public AlunoModel(String nome, int matricula) {
		this.nome = nome;
		this.matricula = matricula;
		this.ativo = true;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getMatricula() {
		return matricula;
	}
	
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public boolean isAtivo() {
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}


	
	
	
}
