package engsoft.usuario;

import engsoft.biblioteca.Exemplar;
import engsoft.biblioteca.Livro;

public interface Emprestimo {
	public Exemplar pegarEmprestado(Livro l, Usuario u) throws Exception;
}
