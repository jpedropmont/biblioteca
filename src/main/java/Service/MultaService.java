package Service;

import java.util.Date;
import java.util.HashMap;

import Model.EmprestimoModel;
import Model.MultaModel;

public class MultaService {
	
	// Chave: Código do empréstimo - Valor: EmprestimoModel 
	private HashMap<Integer, EmprestimoModel> emprestimos = EmprestimoService.getEmprestimos();
	private Date diaAtual = new Date();
	
	private MultaModel multa;
	
	public void multar (int codigoEmprestimo) {
		int diasTotaisDaNaoDevolucao;
		int dataDevolucao = emprestimos.get(codigoEmprestimo).getDataDaDevolucao().getDate();
		if (diaAtual.getDate() > dataDevolucao) {
			multa = new MultaModel();
			diasTotaisDaNaoDevolucao = diaAtual.getDate() - dataDevolucao;
			if (diaAtual.getDate() > dataDevolucao + 10 && diaAtual.getDate() > dataDevolucao + 15) {
				
			}
			while (diaAtual.getDate() > dataDevolucao) {
				multa.setValorDaMulta(multa.getValorDaMulta() + 0.5);
			}
		}
	}
	
	public String enviarEmail (int codigoEmprestimo) {
		int dataDevolucao = emprestimos.get(codigoEmprestimo).getDataDaDevolucao().getDate();
		if (diaAtual.getDate() == dataDevolucao - 1) {
			return "AVISO: Você tem um dia para devolver o livro " + emprestimos.get(codigoEmprestimo).getLivro().getNomeLivro() + " a biblioteca.";
		} else if (diaAtual.getDate() == dataDevolucao - 2) {
			return "AVISO: Você tem dois dias para devolver o livro " + emprestimos.get(codigoEmprestimo).getLivro().getNomeLivro() + " a biblioteca.";
		}
		return null;
	}
}
