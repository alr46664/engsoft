package engsoft.command;

import engsoft.biblioteca.Biblioteca;

public class SairCommand implements Command {

	@Override
	public void execute(String[] args, Biblioteca recv) throws Exception {		
		recv.sair();
	}


}
