package engsoft.command;

import engsoft.biblioteca.Biblioteca;

public class ReservaCommand implements Command {

	@Override
	public void execute(String[] args, Biblioteca recv) throws Exception {
		if (args.length != 3) {
			throw new Exception("O comando \"res\" requer o codigo do livro e do usuario. Sintaxe:\n" +
				"res cod_usuario cod_livro\n");				
		}
		recv.reservar(args[1], args[2]);
	}

}
