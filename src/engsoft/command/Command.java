package engsoft.command;

import engsoft.biblioteca.Biblioteca;

public interface Command {
	public void execute(String[] args) throws Exception;
}
