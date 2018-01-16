package engsoft.biblioteca;

import java.util.Calendar;
import java.util.Date;

import engsoft.usuario.Usuario;

public class StatusDisponivel extends Status {


	public StatusDisponivel(Exemplar exemplar, Usuario usuario, Date dataEmprestimoReserva, Date dataDevolucaoEsperada) {
		super(exemplar, usuario, dataEmprestimoReserva, dataDevolucaoEsperada);		
	}

	@Override
	public void pegarEmprestado(Usuario usuario, int dias, boolean force) throws Exception {		
		Date today = new Date();	
		Date devolucao;
		Status s;
		// defina quando o usuario deve devolver o livro
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(Calendar.DATE, dias);					
		devolucao = c.getTime();
		// mude o status do exemplar para emprestado
		s = new StatusEmprestado(this.exemplar, usuario, today, devolucao);
		this.exemplar.setState(s);
	}

	@Override
	public void devolver() throws Exception {
		throw new Exception("Nao foi possivel completar a devolucao do exemplar. Exemplar nao esta emprestado.");
	}

	@Override
	public void reservar(Usuario usuario, boolean force) throws Exception {		
		Date today = new Date();			
		Status s = new StatusReservado(this.exemplar, usuario, today, null);
		this.exemplar.setState(s);
	}

	@Override
	public void desreservar(Usuario u) throws Exception {
		throw new Exception("Nao foi possivel completar a desreserva do exemplar. Exemplar nao esta reservado.");
	}

	@Override
	public Usuario isReservado() {	
		return null;
	}

	@Override
	public Usuario isEmprestado() {		
		return null;
	}

	@Override
	public boolean isEmprestimoAtrasado() throws Exception {		
		throw new Exception("Exemplar nao esta emprestado. Logo o emprestimo nao pode estar atrasado");		
	}

	@Override
	public String toString() {
		String s = "Status: Disponivel\n";
		return s;
	}

}
