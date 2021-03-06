package engsoft;

import java.util.HashMap;
import java.util.Scanner;

import engsoft.biblioteca.Biblioteca;
import engsoft.biblioteca.Livro;
import engsoft.command.Command;
import engsoft.command.DevolucaoCommand;
import engsoft.command.EmprestimoCommand;
import engsoft.command.ListarCommand;
import engsoft.command.LivroCommand;
import engsoft.command.NotificacaoCommand;
import engsoft.command.ObservarCommand;
import engsoft.command.ReservaCommand;
import engsoft.command.SairCommand;
import engsoft.usuario.Aluno;
import engsoft.usuario.Funcionario;
import engsoft.usuario.Professor;

/**
 * Gerencia a execucao do programa, chamando os Commands necessarios e gerando os dados de testes na Biblioteca
 * @author Andre Madureira, Felipe Ribeiro, Dhene Arlis
 *
 */
public class Programa {	
	
	// controle de execucao
	private static boolean continueRunning = true;		
	
	/**
	 * Para a execucao do programa de forma segura
	 */
	public static void stop() {
		continueRunning = false;
	}	
	
	/**
	 * O programa iniciara aqui
	 * @param args argumentos de inicializacao do console para o programa
	 */
	public static void main(String[] args) {
		
		// inicializacao da biblioteca		
		// crie objetos para podermos operar com a biblioteca
		getData();
		
		// inicializacao dos comandos do programa
		HashMap<String, Command> commands = new HashMap<String, Command>();
		commands.put("emp", new EmprestimoCommand());
		commands.put("dev", new DevolucaoCommand());
		commands.put("res", new ReservaCommand());
		commands.put("obs", new ObservarCommand());
		commands.put("liv", new LivroCommand());
		commands.put("usu", new ListarCommand());
		commands.put("ntf", new NotificacaoCommand());
		commands.put("sai", new SairCommand());
		
		// leia o terminal
		System.out.println("\t-------------- BIBLIO SYSTEM ----------------\n");		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in).useDelimiter("\\s");
		while(continueRunning) {
			try {		
				// aguarde o usuario digitar o comando e, entao, execute-o
				System.out.println("Digite o commando abaixo: ");
				String[] argArray = sc.nextLine().split("\\s+");
				System.out.println("");
				Command command = commands.get(argArray[0]);			
				command.execute(argArray);
			} catch(Exception e) {
				// Houve um erro na execucao do comando, reportar erro ao usuario
				System.out.println("\nERRO:");
				System.out.println(e.getMessage());
				System.out.println("");
			}
		}
		sc.close();			
	}
	
	/**
	 * Cria os dados de teste da biblioteca
	 */
	private static void getData() {
		Biblioteca biblio = Biblioteca.getInstance();
		Livro l;

		// Cria usuarios
		biblio.addUsuario(new Funcionario("123", "João da Silva"));
		biblio.addUsuario(new Aluno("456", "Luiz Fernando Rodrigues"));
		biblio.addUsuario(new Funcionario("789", "Pedro Paulo"));
		biblio.addUsuario(new Professor("100", "Carlos Lucena"));
		
		// Criacao dos livros
		l = new Livro("100","Engenharia de Software","AddisonWesley","Ian Sommervile","6","2000");
		l.addExemplar("01");
		l.addExemplar("02");
		biblio.addLivro(l);
		
		l = new Livro("101","UML – Guia do Usuário","  Campus", "Grady Booch, James Rumbaugh, Ivar Jacobson","7","2000");
		l.addExemplar("03");		
		biblio.addLivro(l);
		
		l = new Livro("200","Code Complete","Microsoft Press", "Steve McConnell","2","2014");
		l.addExemplar("04");		
		biblio.addLivro(l);
		
		l = new Livro("201","Agile Software Development,Principles, Patterns,and Practices","Prentice Hall", "Robert Martin","1","2002");
		l.addExemplar("05");		
		biblio.addLivro(l);
		
		l = new Livro("300","Refactoring: Improving the Design of Existing Code", " Addison-Wesley Professional", " Martin Fowler","1","1999");
		l.addExemplar("06");
		l.addExemplar("07");
		biblio.addLivro(l);
		
		l = new Livro("301","Software Metrics: A Rigorous and Practical Approach", "CRC Press", "Norman Fenton, James Bieman","3","2014");		
		biblio.addLivro(l);
		
		l = new Livro("400","Design Patterns: Elements of Reusable Object-Oriented Software", "Addison-Wesley Professional", 
				"Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides","1","1994");
		l.addExemplar("08");
		l.addExemplar("09");
		biblio.addLivro(l);		
		
		l = new Livro("401","UML Distilled: A Brief Guide to the Standard Object Modeling Language", "Addison-Wesley Professional", 
				"Martin Fowler","3","2003");
		biblio.addLivro(l);
	}

}
