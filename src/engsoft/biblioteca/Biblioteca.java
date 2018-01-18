package engsoft.biblioteca;

import java.util.ArrayList;

import engsoft.Programa;
import engsoft.observer.Observer;
import engsoft.usuario.Usuario;

public class Biblioteca {	
	
	// faca de biblioteca um singleton e receiver de comandos
	private static Biblioteca self;
	// atributos da biblioteca
	private ArrayList<Livro> livros;
	private ArrayList<Usuario> usuarios;
	
	public static Biblioteca getInstance() {
		if (self == null) {
			self = new Biblioteca();
		}
		return self;
	}
	
	
	private Biblioteca() {
		livros = new ArrayList<Livro>();
		usuarios = new ArrayList<Usuario>();
	}
	
	private Livro getLivro(String codLivro) throws Exception {
		for (Livro l: this.livros) {
			if (l.getCodLivro() != null && l.getCodLivro().equals(codLivro)) {
				return l;
			}
		}
		throw new Exception("Livro de codigo \"" + codLivro + "\" nao encontrado.\n");
	}
	
	private Usuario getUsuario(String codUsuario) throws Exception {
		for (Usuario u: this.usuarios) {
			if (u.getCodigo() != null && u.getCodigo().equals(codUsuario)) {
				return u;
			}
		}
		throw new Exception("Usuario de codigo \"" + codUsuario + "\" nao encontrado.\n");
	}
	
	
	public void addLivro(Livro l) {
		livros.add(l);
	}
	
	public void addUsuario(Usuario u) {
		usuarios.add(u);
	}
	
	
	public void emprestar(String codUsuario, String codLivro) throws Exception {
		Usuario usuario = getUsuario(codUsuario);
		Livro livro = getLivro(codLivro);
		Exemplar exemplar = usuario.pegarEmprestado(livro);		
		System.out.println(exemplar + "\nSucesso - Emprestimo do livro realizado com sucesso!\n");
	}
	
	public void devolver(String codUsuario, String codLivro) throws Exception {
		Usuario usuario = getUsuario(codUsuario);
		Livro livro = getLivro(codLivro);
		Exemplar exemplar = usuario.devolver(livro);
		System.out.println(exemplar + "\nSucesso - Devolucao do livro realizada com sucesso!\n");
	}
	
	public void reservar(String codUsuario, String codLivro) throws Exception {
		Usuario usuario = getUsuario(codUsuario);
		Livro livro = getLivro(codLivro);
		usuario.reservar(livro);
		System.out.println("Nome do Usuario: " + usuario.getNome() + "\n" +
                "Livro: " + livro.getTitulo() + "\n" +
				"\nSucesso - Reserva do livro realizada com sucesso!\n");
	}
	
	public void registrarObservar(String codUsuario, String codLivro) throws Exception {
		Usuario usuario = getUsuario(codUsuario);
		Livro livro = getLivro(codLivro);
		if (!Observer.class.isInstance(usuario)) {
			throw new Exception("Nome do Usuario: " + usuario.getNome() + "\n" +
	                "Livro: " + livro.getTitulo() + "\n" +
					"\nO usuario nao pode observar as reservas do livro.\n");
		}	
		Observer obs = (Observer) usuario;
		livro.registerObserver(obs);
		System.out.println(
            "Nome do Usuario: " + usuario.getNome() + "\n" +
            "Livro: " + livro.getTitulo() + "\n" +
            "\nSucesso - Registro de novo observador realizado com sucesso!\n");
	}
	
	public void exibirListagem(String codUsuario) throws Exception {
		Usuario usuario = getUsuario(codUsuario);
		System.out.println(usuario);		
	}
	
	public void consultarLivro(String codLivro) throws Exception {
		Livro livro = getLivro(codLivro);
		System.out.println(livro);
	}
	
	public void consultarNotificacao(String codUsuario) throws Exception {
		Usuario usuario = getUsuario(codUsuario);
		if (!Observer.class.isInstance(usuario)) {
			throw new Exception("Nome do Usuario: " + usuario.getNome() + "\n" +	                
					"\nO usuario nao pode observar as reservas do livro. Logo ele nao possui notificacoes.\n");
		}	
		Observer obs = (Observer) usuario;
		System.out.println("Nome do Usuario: " + usuario.getNome() + "\n" +
				"\nO usuario teve \"" + obs.getQtdNotificacao() + "\" notificacoes de reserva de livros.\n");
	}
	
	public void sair() {
		Programa.stop();
	}
}
