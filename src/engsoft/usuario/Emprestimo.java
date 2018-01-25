package engsoft.usuario;

import engsoft.biblioteca.Exemplar;
import engsoft.biblioteca.Livro;

/**
 * Interface que indica o nivel de prioridade de um Usuario com relacao a Emprestimos, usando o padrao de projeto Strategy
 * @author Andre Madureira, Felipe Ribeiro, Dhene Arlis
 *
 */
public interface Emprestimo {
	
	/**
	 * Realiza o emprestimo do livro para o usuario
	 * @param l livro a ser emprestado
	 * @param u usuario
	 * @return Exemplar emprestado
	 * @throws Exception caso o procedimento nao seja bem sucedido (vide especificacao do trabalho para maiores detalhes), um Exception sera gerado
	 */
	public Exemplar pegarEmprestado(Livro l, Usuario u) throws Exception;
}
