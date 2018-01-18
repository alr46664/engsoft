package engsoft.biblioteca;

import java.util.Calendar;
import java.util.Date;

import engsoft.usuario.Usuario;

public class StatusDisponivel extends Status {


	public StatusDisponivel(Exemplar exemplar, Usuario usuario, int diasEmprestimo) {
		super(exemplar, usuario, diasEmprestimo);		
	}

	@Override
	public void pegarEmprestado(Usuario usuario, int dias) throws Exception {				
		// mude o status do exemplar para emprestado
		Status s = new StatusEmprestado(this.exemplar, usuario, dias);
		this.exemplar.setState(s);
	}

	@Override
	public void devolver() throws Exception {
		throw new Exception("Nao foi possivel completar a devolucao do exemplar. Exemplar nao esta emprestado.");
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
