package engsoft.biblioteca;

import java.util.ArrayList;

import engsoft.usuario.Usuario;

public class Exemplar {
	private Livro livro;	
    private String codExemplar;   
    private Status state;
    private ArrayList<Status> historico;

    public Exemplar(String codExemplar, Livro livro) {
    	this.historico = new ArrayList<Status>();
    	this.codExemplar = codExemplar;
    	this.livro = livro;
    	this.state = new StatusDisponivel(this, null, null, null);    	    	
    }
    
    @Override
    public String toString(){
        String s = "Codigo do Exemplar: " + codExemplar + "\n";
        s += state;
        if (this.historico.size() > 0) {
	        s += "\t\t==>  Historico do Exemplar:  <==\n\t\t";
	        for (Status status: this.historico) {
	        	s += status + "\n\t";
	        };
        }
        s += "----------------------------\n";
    	return s;
    }     

    public String getCodExemplar() {
        return codExemplar;
    }
    
    public Livro getLivro() {
    	return livro;
    }
    
    public String getHistorico(Usuario usuario) {
    	boolean hasOneUser = false;
    	String s = "Historico do Exemplar: " + codExemplar  + "\n\t\t";
        for (Status status: historico) {
        	if (status.isEmprestado() == usuario || status.isReservado() == usuario) {
        		s += status + "\n\t\t";
        		hasOneUser = true;
        	}        	
        }
        return (hasOneUser ? s : "");
    }
    
    public void setState(Status s) {
    	this.historico.add(this.state);
    	this.state = s;
    }
    
    public Usuario isReservado() {
    	return this.state.isReservado();
    }
    
    public Usuario isEmprestado() {
    	return this.state.isEmprestado();
    }
    
    public void pegarEmprestado(Usuario usuario, int dias, boolean force) throws Exception {
    	this.state.pegarEmprestado(usuario, dias, force);
    }
    
    public void devolver() throws Exception {
    	this.state.devolver();
    }
    
    public void reservar(Usuario usuario, boolean force) throws Exception {
    	this.state.reservar(usuario, force);
    }
    
    public void desreservar(Usuario usuario) throws Exception {
    	this.state.desreservar(usuario);
    }
    
    public boolean isEmprestimoAtrasado() throws Exception {
    	return this.state.isEmprestimoAtrasado();
    }
    
}