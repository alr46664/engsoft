package engsoft.command;

import engsoft.biblioteca.Biblioteca;

public interface Command {
	public void execute(String[] args, Biblioteca recv) throws Exception;
}
