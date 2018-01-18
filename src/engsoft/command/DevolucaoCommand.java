package engsoft.command;

import engsoft.biblioteca.Biblioteca;

public class DevolucaoCommand implements Command {

	@Override
	public void execute(String[] args) throws Exception {
		if (args.length != 3) {
			throw new Exception("O comando \"dev\" requer o codigo do livro e do usuario. Sintaxe:\n" +
				"dev cod_usuario cod_livro\n");				
		}
		Biblioteca recv = Biblioteca.getInstance();
		recv.devolver(args[1], args[2]);
	}

}
