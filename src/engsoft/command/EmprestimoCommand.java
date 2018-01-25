package engsoft.command;

import engsoft.biblioteca.Biblioteca;

public class EmprestimoCommand implements Command {

	/**
	 * Realiza emprestimo de um livro emprestado para um usuario
	 */
	@Override
	public void execute(String[] args) throws Exception {
		if (args.length != 3) {
			throw new Exception("O comando \"emp\" requer o codigo do livro e do usuario. Sintaxe:\n" +
				"emp cod_usuario cod_livro\n");				
		}
		Biblioteca recv = Biblioteca.getInstance();
		recv.emprestar(args[1], args[2]);
	}

}
