package engsoft.command;

import engsoft.biblioteca.Biblioteca;

public class EmprestimoCommand implements Command {

	@Override
	public void execute(String[] args, Biblioteca recv) throws Exception {
		if (args.length != 3) {
			throw new Exception("O comando \"emp\" requer o codigo do livro e do usuario. Sintaxe:\n" +
				"emp cod_usuario cod_livro\n");				
		}
		recv.emprestar(args[1], args[2]);
	}

}
