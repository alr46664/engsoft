package engsoft.biblioteca;

import java.util.Date;

import engsoft.usuario.Usuario;

public abstract class Status {
	protected Date dataEmprestimoReserva;
	protected Date dataDevolucaoEsperada;
	protected Date dataDevolucaoFeita;	
	protected Usuario usuario;
	protected Exemplar exemplar;
	
	public Status(Exemplar exemplar, Usuario usuario, Date dataEmprestimoReserva, Date dataDevolucaoEsperada) {
		this.exemplar = exemplar;
		this.usuario = usuario;
		this.dataEmprestimoReserva = dataEmprestimoReserva;
		this.dataDevolucaoEsperada = this.dataDevolucaoEsperada;
	}	
	
	public abstract String toString();
	public abstract void pegarEmprestado(Usuario usuario, int dias, boolean force) throws Exception;
	public abstract void devolver() throws Exception;
	public abstract void reservar(Usuario usuario, boolean force) throws Exception;
	public abstract void desreservar(Usuario u) throws Exception;
	public abstract Usuario isReservado();
	public abstract Usuario isEmprestado();
	public abstract boolean isEmprestimoAtrasado() throws Exception ;
}
