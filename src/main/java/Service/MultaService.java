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
		int diasMulta=0;
		if (diaAtual.getDate() > dataDevolucao) {
			multa = new MultaModel();
			diasTotaisDaNaoDevolucao = diaAtual.getDate() - dataDevolucao;
			while (diasTotaisDaNaoDevolucao > 0) {
				Date dataTemp = new Date();
				dataTemp.setDate(dataTemp.getDate() + diasTotaisDaNaoDevolucao);
				if((dataTemp.getDay() != 0) && (dataTemp.getDay() != 6)) {
					// se der '0' é domingo
					// se der '6' é sábado
					multa.setValorDaMulta(multa.getValorDaMulta() + 0.5);
					diasMulta++;
				}
				diasTotaisDaNaoDevolucao--;
			}
			
			//20% off
			if ((diasMulta >= 10) && (diasMulta <= 15)) {
				multa.setValorDaMulta(multa.getValorDaMulta() - (multa.getValorDaMulta() * 0.2) );
				enviarEmail(codigoEmprestimo, "Desconto de 20% disponivel");
			}
			
			//25% off
			if ((diasMulta >= 16) && (diasMulta <= 29)) {
				multa.setValorDaMulta(multa.getValorDaMulta() - (multa.getValorDaMulta() * 0.25) );
				enviarEmail(codigoEmprestimo, "Desconto de 25% disponivel");
			}
			
			//30% off
			if (diasMulta >= 30) {
				multa.setValorDaMulta(multa.getValorDaMulta() - (multa.getValorDaMulta() * 0.3) );
				enviarEmail(codigoEmprestimo, "Desconto de 30% disponivel");
			}
		}
	}
	
	public String enviarEmail (int codigoEmprestimo, String msg) {
		if(msg != null) {
			return msg;
		}
		int dataDevolucao = emprestimos.get(codigoEmprestimo).getDataDaDevolucao().getDate();
		if (diaAtual.getDate() == dataDevolucao - 1) {
			return "AVISO: Você tem um dia para devolver o livro " + emprestimos.get(codigoEmprestimo).getLivro().getNomeLivro() + " a biblioteca.";
		} else if (diaAtual.getDate() == dataDevolucao - 2) {
			return "AVISO: Você tem dois dias para devolver o livro " + emprestimos.get(codigoEmprestimo).getLivro().getNomeLivro() + " a biblioteca.";
		}
		return null;
	}
}
