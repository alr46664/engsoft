package engsoft.usuario;

import engsoft.biblioteca.Exemplar;
import engsoft.biblioteca.Livro;

public class EmprestimoComum implements Emprestimo {

	private int diasEmprestimo;
	private int maxEmprestimo;
	
	public EmprestimoComum(int diasEmprestimo, int maxEmprestimo) {
		this.diasEmprestimo = diasEmprestimo;
		this.maxEmprestimo = maxEmprestimo;
	}
	
	@Override
	public Exemplar pegarEmprestado(Livro l, Usuario u) throws Exception {
		if (u.temEmprestimoAtrasado() || u.getQtdEmprestimos() >= this.maxEmprestimo) {
			throw new Exception("Usuario: " + u.getNome() + "\n" +
					"\nNao foi possivel pegar o livro emprestado." +
					" O usuario tem emprestimos atrasados ou ultrapassou sua cota maxima de emprestimos.\n");
		}        
		return l.pegarEmprestado(u, this.diasEmprestimo);		
	}


}
