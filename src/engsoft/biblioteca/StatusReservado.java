package engsoft.biblioteca;

import java.util.Calendar;
import java.util.Date;

import engsoft.usuario.Usuario;

public class StatusReservado extends Status {


	public StatusReservado(Exemplar exemplar, Usuario usuario, Date dataEmprestimoReserva, Date dataDevolucaoEsperada) {
		super(exemplar, usuario, dataEmprestimoReserva, dataDevolucaoEsperada);
	}

	@Override
	public void pegarEmprestado(Usuario usuario, int dias, boolean force) throws Exception {
		if (usuario != this.usuario && !force) {
			throw new Exception("O exemplar do livro em questao ja esta reservado a outro usuario.\n" + this.usuario);
		}
		Date today = new Date();
		Date devolucao;
		Status s;
		// defina quando o usuario deve devolver o livro
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(Calendar.DATE, dias);					
		devolucao = c.getTime();
		// defina novo status
		s = new StatusEmprestado(this.exemplar, usuario, today, devolucao);
		this.exemplar.setState(s);
	}

	@Override
	public void devolver() throws Exception {
		throw new Exception("Nao foi possivel completar a devolucao do exemplar. Exemplar nao esta emprestado.");
	}

	@Override
	public void reservar(Usuario usuario, boolean force) throws Exception {		
		if (usuario != this.usuario && !force) {
			throw new Exception("Nao foi possivel completar a reserva do exemplar. Exemplar ja reservado em nome de outro usuario.");
		}
		Date today = new Date();
		Status s = new StatusReservado(this.exemplar, usuario, today, null);
		this.exemplar.setState(s);
	}

	@Override
	public void desreservar(Usuario u) throws Exception {		
		if (this.usuario != u) {
			throw new Exception("Nao foi possivel completar a desreservar o exemplar. Exemplar reservado por outro usuario." + 
				this.usuario);
		}
		Status s = new StatusDisponivel(this.exemplar, null, null, null);
		this.exemplar.setState(s);
	}

	@Override
	public Usuario isReservado() {
		return this.usuario;
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
		String  s = "Status: Reservado\n";
		s += "Usuario Requisitor da Reserva: " + this.usuario.getNome() + "\n";
		s += "Data da Reserva: " + this.dataEmprestimoReserva + "\n";		
		return s;
	}

}
