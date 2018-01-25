package engsoft.command;

/**
 * Classe que gerencia o Comando a ser executado pelo Programa
 * @author Andre Madureira, Felipe Ribeiro, Dhene Arlis
 *
 */
public interface Command {
	/**
	 * Execute o comando de acordo com os parametros recebidos
	 * @param args parametros para o comando
	 * @throws Exception caso o procedimento nao seja bem sucedido (vide especificacao do trabalho para maiores detalhes), um Exception sera gerado
	 */
	public void execute(String[] args) throws Exception;
}
