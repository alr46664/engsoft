package engsoft.biblioteca;

import java.util.ArrayList;
import java.util.Date;

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
    	this.state = new StatusDisponivel(this, null, 0);    	    	
    }
    
    @Override
    public String toString(){
        String s = "Codigo do Exemplar: " + codExemplar + "\n";
        s += state;        
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
        	if (status.isEmprestado() == usuario || usuario == null) {
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
    
    public Usuario isEmprestado() {
    	return this.state.isEmprestado();
    }
    
    public void pegarEmprestado(Usuario usuario, int dias) throws Exception {
    	this.state.pegarEmprestado(usuario, dias);
    }
    
    public void devolver() throws Exception {
    	this.state.devolver();
    }       
    
    public boolean isEmprestimoAtrasado() throws Exception {
    	return this.state.isEmprestimoAtrasado();
    }
    
}