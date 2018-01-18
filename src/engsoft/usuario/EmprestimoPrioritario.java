package engsoft.usuario;

import engsoft.biblioteca.Exemplar;
import engsoft.biblioteca.Livro;

public class EmprestimoPrioritario implements Emprestimo {

	private int diasEmprestimo;
	
	public EmprestimoPrioritario(int diasEmprestimo) {
		this.diasEmprestimo = diasEmprestimo;		
	}
	
	@Override
	public Exemplar pegarEmprestado(Livro l, Usuario u) throws Exception {	       
		return l.pegarEmprestado(u, this.diasEmprestimo);				
	}

}
