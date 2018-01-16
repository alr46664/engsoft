package engsoft.command;

import engsoft.biblioteca.Biblioteca;

public class NotificacaoCommand implements Command {

	@Override
	public void execute(String[] args, Biblioteca recv) throws Exception {
		if (args.length != 2) {
			throw new Exception("O comando \"ntf\" requer o codigo do usuario. Sintaxe:\n" +
				"ntf cod_usuario\n");				
		}
		recv.consultarNotificacao(args[1]);
	}

}
