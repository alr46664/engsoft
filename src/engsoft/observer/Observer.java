package engsoft.observer;

import engsoft.biblioteca.Livro;

/**
 * Interface que gerencia quem podem observar os subjects
 * @author Andre Madureira, Felipe Ribeiro, Dhene Arlis
 *
 */
public interface Observer {
	
	/**
	 * Realiza a notificacao do observer com relacao ao livro
	 * @param livro livro observado que emitiu a notificacao
	 */
    public void update(Livro livro);
    
    /**
     * Informa a quantidade de notificacoes que o observador recebeu
     * @return quantidade de notificacoes que o observador recebeu
     */
    public int getQtdNotificacao();
}