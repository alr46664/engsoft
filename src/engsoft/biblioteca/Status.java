package engsoft.biblioteca;

import java.util.Calendar;
import java.util.Date;

import engsoft.usuario.Usuario;

public abstract class Status {
	protected Date dataEmprestimo;
	protected Date dataDevolucaoEsperada;
	protected Date dataDevolucaoFeita;	
	protected Usuario usuario;
	protected Exemplar exemplar;
	
	public Status(Exemplar exemplar, Usuario usuario, int diasEmprestimo) {
		this.exemplar = exemplar;
		this.usuario = usuario;
		// emprestimo feito hoje
		this.dataEmprestimo = new Date();
		// defina quando o usuario deve devolver o livro
		Calendar c = Calendar.getInstance();
		c.setTime(this.dataEmprestimo);
		c.add(Calendar.DATE, diasEmprestimo);					
		this.dataDevolucaoEsperada = c.getTime();
		// ainda nao devolvido
		this.dataDevolucaoFeita = null;
	}		
	
	public abstract String toString();
	public abstract void pegarEmprestado(Usuario usuario, int dias) throws Exception;
	public abstract void devolver() throws Exception;		
	public abstract Usuario isEmprestado();
	public abstract boolean isEmprestimoAtrasado() throws Exception;
	
}
