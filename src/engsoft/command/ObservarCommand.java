package engsoft.command;

import engsoft.biblioteca.Biblioteca;

public class ObservarCommand implements Command {

	/**
	 * Registra um usuario como observador de um livro
	 */
	@Override
	public void execute(String[] args) throws Exception {
		if (args.length != 3) {
			throw new Exception("O comando \"obs\" requer o codigo do livro e do usuario. Sintaxe:\n" +
				"obs cod_usuario cod_livro\n");				
		}
		Biblioteca recv = Biblioteca.getInstance();
		recv.registrarObservar(args[1], args[2]);
	}

}
