package engsoft.biblioteca;

import engsoft.usuario.Usuario;

/**
 * Classe que indica o Status do Exemplar de Livro
 * @author Andre Madureira, Felipe Ribeiro, Dhene Arlis
 *
 */
public abstract class Status {
	
	protected Exemplar exemplar;
	
	public Status(Exemplar exemplar) {
		this.exemplar = exemplar;		
	}		
	
	public abstract String toString();
	
	/**
	 * Realiza o emprestimo do Exemplar para o Usuario, caso isso seja possivel (vide especificacao do trabalho)
     * @param usuario usuario que deseja pegar um Exemplar do Livro emprestado
     * @param dias numero de dias maximo que o usuario pode permanecer com o Livro emprestado     * 
     * @throws Exception caso o procedimento nao seja bem sucedido (vide especificacao do trabalho para maiores detalhes), um Exception sera gerado
     * 
	 */
	public abstract void pegarEmprestado(Usuario usuario, int dias) throws Exception;
	
	/**
     * Devolve o Exemplar 
     * @throws Exception caso o procedimento nao seja bem sucedido (vide especificacao do trabalho para maiores detalhes), um Exception sera gerado
     */
	public abstract void devolver() throws Exception;		
	
	/**
     * Retorna o usuario que pegou o exemplar emprestado
     * @return usuario que pegou o exemplar emprestado. Caso o exemplar nao esteja emprestado, o metodo retorna null.
     */
	public abstract Usuario isEmprestado();
	
	/**
     * Verifica se o emprestimo do exemplar esta atrasado
     * @return true caso emprestimo atrasado, false do contrario
     * @throws Exception caso o exemplar nao esteja emprestado, um Exception sera gerado
     */
	public abstract boolean isEmprestimoAtrasado() throws Exception;
	
}
