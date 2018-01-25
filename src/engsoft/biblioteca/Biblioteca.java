package engsoft.biblioteca;

import java.util.HashSet;
import java.util.Set;

import engsoft.Programa;
import engsoft.observer.Observer;
import engsoft.usuario.Usuario;

/**
 * Classe Facade e Singleton que gerencia a execucao do sistema biblioteca, 
 * armazenando os Livros e Usuarios do sistema e realizando as chamadas dos metodos dessas classes.
 * @author Andre Madureira, Felipe Ribeiro, Dhene Arlis
 *
 */
public class Biblioteca {	
	
	// faca de biblioteca um singleton
	private static Biblioteca self;
	
	// atributos da biblioteca
	private Set<Livro> livros;
	private Set<Usuario> usuarios;
	
	/**
	 * Metodo que permite o recebimento de uma instancia da classe Biblioteca, seguindo o padrao de projeto Singleton.
	 * @return instancia de Biblioteca
	 */
	public static Biblioteca getInstance() {
		if (self == null) {
			self = new Biblioteca();
		}
		return self;
	}
	
	
	private Biblioteca() {
		livros = new HashSet<Livro>();
		usuarios = new HashSet<Usuario>();
	}
	
	/**
	 * Procura por um Livro usando como criterio de pesquisa o seu codigo 
	 * @param codLivro codigo do livro 
	 * @return Livro encontrado
	 * @throws Exception caso o livro nao seja encontrado, um Exception sera gerado
	 */
	private Livro getLivro(String codLivro) throws Exception {
		for (Livro l: this.livros) {
			if (l.getCodLivro() != null && l.getCodLivro().equals(codLivro)) {
				return l;
			}
		}
		throw new Exception("Livro de codigo \"" + codLivro + "\" nao encontrado.\n");
	}
	
	/**
	 * Procura por um Usuario usando como criterio de pesquisa o seu codigo
	 * @param codUsuario codigo do usuario
	 * @return Usuario encontrado
	 * @throws Exception caso o usuario nao seja encontrado, um Exception sera gerado
	 */
	private Usuario getUsuario(String codUsuario) throws Exception {
		for (Usuario u: this.usuarios) {
			if (u.getCodigo() != null && u.getCodigo().equals(codUsuario)) {
				return u;
			}
		}
		throw new Exception("Usuario de codigo \"" + codUsuario + "\" nao encontrado.\n");
	}
	
	/**
	 * Adiciona um Livro novo na Biblioteca
	 * @param l Livro a ser adicionado
	 */
	public void addLivro(Livro l) {
		livros.add(l);
	}
	
	/**
	 * Adiciona um Usuario novo na Biblioteca
	 * @param u usuario a ser adicionado
	 */
	public void addUsuario(Usuario u) {
		usuarios.add(u);
	}
	
	/**
	 * Realiza o emprestimo de um livro para o usuario 
	 * @param codUsuario codigo do usuario que deseja pegar emprestado o livro
	 * @param codLivro codigo do livro a ser emprestado
	 * @throws Exception caso o procedimento nao seja bem sucedido (vide especificacao do trabalho para maiores detalhes), um Exception sera gerado
	 */
	public void emprestar(String codUsuario, String codLivro) throws Exception {
		Usuario usuario = getUsuario(codUsuario);
		Livro livro = getLivro(codLivro);
		Exemplar exemplar = usuario.pegarEmprestado(livro);		
		System.out.println(exemplar + "\nSucesso - Emprestimo do livro realizado com sucesso!\n");
	}
	
	/**
	 * Realiza uma devolucao de um emprestimo de livro, feito pelo usuario
	 * @param codUsuario codigo do usuario que deseja devolver o livro
	 * @param codLivro codigo do livro a ser devolvido
	 * @throws Exception caso o procedimento nao seja bem sucedido (vide especificacao do trabalho para maiores detalhes), um Exception sera gerado
	 */
	public void devolver(String codUsuario, String codLivro) throws Exception {
		Usuario usuario = getUsuario(codUsuario);
		Livro livro = getLivro(codLivro);
		Exemplar exemplar = usuario.devolver(livro);
		System.out.println(exemplar + "\nSucesso - Devolucao do livro realizada com sucesso!\n");
	}
	
	/**
	 * Realiza a reserva de um livro para o usuario
	 * @param codUsuario codigo do usuario que deseja reservar o livro
	 * @param codLivro codigo do livro a ser reservado
	 * @throws Exception caso o procedimento nao seja bem sucedido (vide especificacao do trabalho para maiores detalhes), um Exception sera gerado
	 */
	public void reservar(String codUsuario, String codLivro) throws Exception {
		Usuario usuario = getUsuario(codUsuario);
		Livro livro = getLivro(codLivro);
		Reserva res = usuario.reservar(livro);
		System.out.println("Livro: " + livro.getTitulo() + "\n" +
				res +
				"\nSucesso - Reserva do livro realizada com sucesso!\n");
	}
	
	/**
	 * Realiza o registro de um observador (usuario) para o livro
	 * @param codUsuario codigo do usuario que deseja observar o livro
	 * @param codLivro codigo do livro a ser observado
	 * @throws Exception caso o procedimento nao seja bem sucedido (vide especificacao do trabalho para maiores detalhes), um Exception sera gerado
	 */
	public void registrarObservar(String codUsuario, String codLivro) throws Exception {
		Usuario usuario = getUsuario(codUsuario);
		Livro livro = getLivro(codLivro);
		// verifique se o usuario pode observar um livro
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
	
	/**
	 * Mostra os detalhes acerca do usuario de acordo com a especificacao do trabalho
	 * @param codUsuario codigo do usuario que se deseja consultar detalhes
	 * @throws Exception caso o procedimento nao seja bem sucedido (vide especificacao do trabalho para maiores detalhes), um Exception sera gerado
	 */
	public void exibirListagem(String codUsuario) throws Exception {
		Usuario usuario = getUsuario(codUsuario);
		System.out.println(usuario);		
	}
	
	/**
	 * Mostra os detalhes acerca do livro de acordo com a especificacao do trabalho
	 * @param codLivro codigo do livro que se deseja consultar detalhes
	 * @throws Exception caso o procedimento nao seja bem sucedido (vide especificacao do trabalho para maiores detalhes), um Exception sera gerado
	 */
	public void consultarLivro(String codLivro) throws Exception {
		Livro livro = getLivro(codLivro);
		System.out.println(livro);
	}
	
	/**
	 * Consulta quantas notificacoes o usuario teve desde quando ele comecou a observar livros da Biblioteca
	 * @param codUsuario codigo do usuario que se deseja consultar o numero de notificacoes
	 * @throws Exception caso o procedimento nao seja bem sucedido (vide especificacao do trabalho para maiores detalhes), um Exception sera gerado
	 */
	public void consultarNotificacao(String codUsuario) throws Exception {
		Usuario usuario = getUsuario(codUsuario);
		// verifique se o usuario pode observar um livro
		if (!Observer.class.isInstance(usuario)) {
			throw new Exception("Nome do Usuario: " + usuario.getNome() + "\n" +	                
					"\nO usuario nao pode observar as reservas do livro. Logo ele nao possui notificacoes.\n");
		}	
		Observer obs = (Observer) usuario;
		System.out.println("Nome do Usuario: " + usuario.getNome() + "\n" +
				"\nO usuario teve \"" + obs.getQtdNotificacao() + "\" notificacoes de reserva de livros.\n");
	}
	
	/**
	 * Realiza a parada do programa
	 */
	public void sair() {
		Programa.stop();
	}
}
