package engsoft.biblioteca;

import java.util.Calendar;
import java.util.Date;

import engsoft.usuario.Usuario;

public class StatusEmprestado extends Status {	
	protected Date dataEmprestimo;
	protected Date dataDevolucaoEsperada;
	protected Date dataDevolucaoFeita;	
	protected Usuario usuario;

	public StatusEmprestado(Exemplar exemplar, Usuario usuario, int diasEmprestimo) {
		super(exemplar);	
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

	@Override
	public void pegarEmprestado(Usuario usuario, int dias) throws Exception {
		throw new Exception("Nao foi possivel completar o emprestimo do exemplar. Exemplar ja emprestado.");
	}

	@Override
	public void devolver() throws Exception {
		this.dataDevolucaoFeita = new Date();
		Status s = new StatusDisponivel(this.exemplar);		
		this.exemplar.setState(s);
	}

	@Override
	public Usuario isEmprestado() {
		return this.usuario;
	}
	
	@Override
	public boolean isEmprestimoAtrasado() throws Exception {		
		if (this.dataDevolucaoFeita != null) {
			return (this.dataDevolucaoFeita.compareTo(this.dataDevolucaoEsperada) > 0);
		} 
		return this.dataDevolucaoEsperada.compareTo(new Date()) < 0; 
		
	}

	@Override
	public String toString() {
		String  s = "Status: Emprestado\n";
		s += "Usuario Em Posse do Exemplar: " + this.usuario.getNome() + "\n";
		s += "Data do Emprestimo: " + this.dataEmprestimo + "\n";
		s += "Data Prevista para Devolucao: " + this.dataDevolucaoEsperada + "\n";
		if (this.dataDevolucaoFeita != null) {
			s += "Data da Efetiva Devolucao: " + this.dataDevolucaoFeita + "\n";
		}
		return s;
	}

}
