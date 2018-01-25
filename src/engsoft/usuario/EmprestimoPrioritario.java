package engsoft.usuario;

import engsoft.biblioteca.Exemplar;
import engsoft.biblioteca.Livro;

/**
 * Classe que indica que o usuario possui prioridade alta para emprestimos
 * @author Andre Madureira, Felipe Ribeiro, Dhene Arlis
 *
 */
public class EmprestimoPrioritario implements Emprestimo {

	private int diasEmprestimo;
	
	/**
	 * Cria um emprestimo de prioridade alta
	 * @param diasEmprestimo numero maximo de dias que o usuario pode permanecer com um livro emprestado
	 */
	public EmprestimoPrioritario(int diasEmprestimo) {
		this.diasEmprestimo = diasEmprestimo;		
	}
	
	@Override
	public Exemplar pegarEmprestado(Livro l, Usuario u) throws Exception {	       
		return l.pegarEmprestado(u, this.diasEmprestimo);				
	}

}
