package engsoft.command;

import engsoft.biblioteca.Biblioteca;

public class SairCommand implements Command {

	/**
	 * Termina o programa
	 */
	@Override
	public void execute(String[] args) throws Exception {		
		Biblioteca recv = Biblioteca.getInstance();
		recv.sair();
	}


}
