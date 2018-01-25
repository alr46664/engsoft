package engsoft.usuario;

import engsoft.biblioteca.Exemplar;
import engsoft.biblioteca.Livro;

/**
 * Classe que indica que o usuario possui prioridade normal para emprestimos
 * @author Andre Madureira, Felipe Ribeiro, Dhene Arlis
 *
 */
public class EmprestimoComum implements Emprestimo {
	
    // indica quantidade de dias que o usuario pode permanecer com um livro emprestado
	private int diasEmprestimo;

	// numero maximo de emprestimos que o usuario pode realizar
	private int maxEmprestimo;
	
	/**
	 * Cria um emprestimo de prioridade normal
	 * @param diasEmprestimo numero maximo de dias que o usuario pode permanecer com um livro emprestado
	 * @param maxEmprestimo numero maximo de emprestimos que o usuario pode realizar
	 */
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
