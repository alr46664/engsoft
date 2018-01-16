package engsoft.command;

import engsoft.biblioteca.Biblioteca;

public class LivroCommand implements Command {

	@Override
	public void execute(String[] args, Biblioteca recv) throws Exception {
		if (args.length != 2) {
			throw new Exception("O comando \"liv\" requer o codigo do livro. Sintaxe:\n" +
				"liv cod_livro\n");				
		}
		recv.consultarLivro(args[1]);
	}

}
