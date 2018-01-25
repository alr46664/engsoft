package engsoft.command;

import engsoft.biblioteca.Biblioteca;

public class NotificacaoCommand implements Command {

	/**
	 * Realiza consulta de notificacoes recebidas por um usuario Observer
	 */
	@Override
	public void execute(String[] args) throws Exception {
		if (args.length != 2) {
			throw new Exception("O comando \"ntf\" requer o codigo do usuario. Sintaxe:\n" +
				"ntf cod_usuario\n");				
		}
		Biblioteca recv = Biblioteca.getInstance();
		recv.consultarNotificacao(args[1]);
	}

}
