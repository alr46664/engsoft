package engsoft.usuario;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import engsoft.biblioteca.Exemplar;
import engsoft.biblioteca.Livro;

public abstract class  Usuario {
    public static final int MAX_RESERVA = 3;	
    
    private Emprestimo emprestimoStrategy;
    
	private String codigo;
    private String nome;    
    
    private Set<Livro> emprestimos;
    private Set<Livro> reservas;
    private ArrayList<Livro> historico;

    public Usuario(String codigo,String nome){    	
    	this.emprestimos = new HashSet<Livro>();
        this.reservas = new HashSet<Livro>();
        this.historico= new ArrayList<Livro>();
    	
        this.codigo = codigo;
        this.nome = nome;
        this.emprestimoStrategy = null;
    }
    
    @Override
    public String toString(){
        String s = "\tNome: "+getNome()+"\n";
		s += "\tCodigo: " +getCodigo() + "\n";
		s += "\n\t-------------- RESERVAS -----------------\n";
		for (Livro liv: this.reservas) {
			s += "Livro: " + liv.getTitulo() + "\n";
		}
		s += "\n\t-------------- EMPRESTIMOS -----------------\n";
		for (Livro liv: this.emprestimos) {
			s += liv.getExemplarEmprestado(this) + "\n";
		}
		s += "\n\t-------------- HISTORICO DE OPERACOES (RESERVA / EMPRESTIMO PASSADOS) -----------------\n";
		for (Livro liv: this.historico) {
			s += liv.getHistorico(this) + "\n";
		}
    	return s;
    }   

    public final String getCodigo(){
        return this.codigo;
    }

    public final String getNome(){
        return this.nome;
    }    
    
    public final int getQtdEmprestimos() {
    	return this.emprestimos.size();
    }
    
    public final int getQtdReservas() {
    	return this.reservas.size();
    }
    
    public final String getHistorico() {
    	String s = "";
    	for (Livro l: this.historico) {
    		s += l.getHistorico(this) + "\n";
    	}
    	return s;
    }
    
    public final boolean isEmprestado(Livro livro){
        return this.emprestimos.contains(livro);
    }
    
    public final boolean isReservado(Livro livro){
    	return this.reservas.contains(livro);
    }
    
    public final Exemplar devolver(Livro l) throws Exception {    	
    	Exemplar e = l.devolver(this);    	
    	this.emprestimos.remove(l);
    	this.historico.add(l);
    	return e;
    }
    
    public final void desreservar(Livro l) {
    	l.desreservar(this);
    	this.reservas.remove(l);  	
    }
    
    public final boolean temEmprestimoAtrasado() throws Exception {
    	boolean res = false;
    	for (Livro l: this.emprestimos) {
    		res |= l.isEmprestimoAtrasado(this);
    	}
    	return res;
    }
        
    public final void reservar(Livro l) throws Exception {
    	if (getQtdReservas() >= MAX_RESERVA) {
    		throw new Exception("Usuario: " + this.getNome() + "\n" +
    				"\nO usuario ultrapassou o limite maximo de reservas (" + MAX_RESERVA +
    			").\n" + this);
    	}
        if (isReservado(l) || isEmprestado(l)) {
            throw new Exception("Usuario: " + this.getNome() + "\n" +
            		"\nO usuario ja reservou ou pegou emprestado o livro.\n");
    	}
    	l.reservar(this);
        this.reservas.add(l);
    }
    
    public final Exemplar pegarEmprestado(Livro l) throws Exception {
        if (isEmprestado(l)) {
            throw new Exception("Usuario: " + this.getNome() + "\n" +
            		"\nO usuario ja pegou emprestado o livro.\n");
        }
        Exemplar exemplar = this.emprestimoStrategy.pegarEmprestado(l, this);
        this.desreservar(l);
    	this.emprestimos.add(l);
    	return exemplar;
    }           
    
    protected final void setEmprestimoStrategy(Emprestimo e) {
    	this.emprestimoStrategy = e;
    }
    
}