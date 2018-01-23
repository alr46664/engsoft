package engsoft.biblioteca;

import engsoft.usuario.Usuario;

public abstract class Status {
	
	protected Exemplar exemplar;
	
	public Status(Exemplar exemplar) {
		this.exemplar = exemplar;		
	}		
	
	public abstract String toString();
	public abstract void pegarEmprestado(Usuario usuario, int dias) throws Exception;
	public abstract void devolver() throws Exception;		
	public abstract Usuario isEmprestado();
	public abstract boolean isEmprestimoAtrasado() throws Exception;
	
}
