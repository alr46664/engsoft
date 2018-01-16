package engsoft.usuario;
import java.util.ArrayList;

import engsoft.biblioteca.Livro;
import engsoft.observer.Observer;

public abstract class  Usuario implements Observer {
    public static final int MAX_RESERVA = 3;
	
    protected int qtdNotificado;
	private String codigo;
    private String nome;  
    private int diasEmprestimo;
    private ArrayList<Livro> emprestimos;
    private ArrayList<Livro> reservas;
    private ArrayList<Livro> historico;

    public Usuario(String codigo,String nome, int diasEmprestimo){
        this.emprestimos = new ArrayList<Livro>();
        this.reservas = new ArrayList<Livro>();
        this.historico= new ArrayList<Livro>();
    	
        this.codigo = codigo;
        this.nome = nome;
        this.diasEmprestimo = diasEmprestimo;
    }
    
    @Override
    public String toString(){
        String s = "\tNome: "+getNome()+"\n";
		s += "\tCodigo: " +getCodigo() + "\n";
		s += "\n\t-------------- RESERVAS -----------------\n";
		for (Livro liv: this.reservas) {
			s += liv + "\n";
		}
		s += "\n\t-------------- EMPRESTIMOS -----------------\n";
		for (Livro liv: this.emprestimos) {
			s += liv + "\n";
		}
		s += "\n\t-------------- HISTORICO DE OPERACOES (RESERVA / EMPRESTIMO PASSADOS) -----------------\n";
		for (Livro liv: this.historico) {
			s += liv + "\n";
		}
    	return s;
    }   

    public String getCodigo(){
        return this.codigo;
    }

    public String getNome(){
        return this.nome;
    }
    
    public int getDiasEmprestimo() {
    	return this.diasEmprestimo;
    }
    
    public int getQtdEmprestimos() {
    	return this.emprestimos.size();
    }
    
    public int getQtdReservas() {
    	return this.reservas.size();
    }
    
    public String getHistorico() {
    	String s = "";
    	for (Livro l: this.historico) {
    		s += l.getHistorico(this) + "\n";
    	}
    	return s;
    }
    
    public void devolver(Livro l) throws Exception {
    	l.devolver(this);
    	this.emprestimos.remove(l);
    	this.historico.add(l);
    }
    
    public void desreservar(Livro l) throws Exception {
    	l.desreservar(this);
    	this.reservas.remove(l);
    	this.historico.add(l);
    }
    
    public boolean temEmprestimoAtrasado() throws Exception {
    	boolean res = false;
    	for (Livro l: this.emprestimos) {
    		res |= l.isEmprestimoAtrasado(this);
    	}
    	return res;
    }
        
    public void update(Livro livro) { 
    	// esta vazio pois usuario nao pode observar reservas de livros
    }
    
    public int getQtdNotificacao() { 
    	return this.qtdNotificado;
	}
        
    public final void reservar(Livro l) throws Exception {
    	if (getQtdReservas() >= MAX_RESERVA) {
    		throw new Exception("O usuario abaixo ultrapassou o limite maximo de reservas (" + MAX_RESERVA +
    			").\n" + this);
    	}
    	l.reservar(this, false);
		this.reservas.add(l);
    }
    
    public abstract void pegarEmprestado(Livro l) throws Exception;    
    
    protected void addEmprestado(Livro l) {
    	this.emprestimos.add(l);
    	this.reservas.remove(l);
    }    
    
}