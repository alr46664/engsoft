package engsoft.command;

import engsoft.biblioteca.Biblioteca;

public class ListarCommand implements Command {

	@Override
	public void execute(String[] args, Biblioteca recv) throws Exception {
		if (args.length != 2) {
			throw new Exception("O comando \"usu\" requer o codigo do usuario. Sintaxe:\n" +
				"usu cod_usuario\n");				
		}
		recv.exibirListagem(args[1]);
	}

}
