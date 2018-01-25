package engsoft.command;

import engsoft.biblioteca.Biblioteca;

public class LivroCommand implements Command {

	/**
	 * Realiza consulta de um livro
	 */
	@Override
	public void execute(String[] args) throws Exception {
		if (args.length != 2) {
			throw new Exception("O comando \"liv\" requer o codigo do livro. Sintaxe:\n" +
				"liv cod_livro\n");				
		}
		Biblioteca recv = Biblioteca.getInstance();
		recv.consultarLivro(args[1]);
	}

}
