package engsoft.biblioteca;

import java.util.ArrayList;

import engsoft.usuario.Usuario;

public class Exemplar {
	private Livro livro;	
    private String codExemplar;   
    private Status state;
    private ArrayList<StatusEmprestado> historico;

    public Exemplar(String codExemplar, Livro livro) {
    	this.historico = new ArrayList<StatusEmprestado>();    	
    	this.codExemplar = codExemplar;
    	this.livro = livro;
    	this.state = new StatusDisponivel(this);    	    	
    }
    
    @Override    
    public int hashCode() {
        return Integer.parseInt(this.getCodExemplar());
    }

    @Override
    public boolean equals(Object o) {
    	Exemplar ex;
    	if (Exemplar.class.isInstance(o)) {
    		ex = (Exemplar) o;
    	} else {
    		return false;
    	}
    	return this.getCodExemplar() != null && this.getCodExemplar().equals(ex.getCodExemplar());
    }
    
    @Override
    public String toString(){
        String s = "Codigo do Exemplar: " + codExemplar + "\n";
        s += state;        
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
        for (StatusEmprestado status: this.historico) {        	
    		s += status + "\n\t\t";
    		s += "----------------------------\n";
    		hasOneUser = true;        	
        }
        return (hasOneUser ? s : "");
    }    
    
    public void setState(Status s) {
    	this.state = s;
    }
    
    public Usuario isEmprestado() {
    	return this.state.isEmprestado();
    }
    
    public void pegarEmprestado(Usuario usuario, int dias) throws Exception {
    	this.state.pegarEmprestado(usuario, dias);
    	this.historico.add((StatusEmprestado) this.state);
    }
    
    public void devolver() throws Exception {
    	this.state.devolver();
    }       
    
    public boolean isEmprestimoAtrasado() throws Exception {
    	return this.state.isEmprestimoAtrasado();
    }
    
}