package engsoft.biblioteca;

import java.util.Calendar;
import java.util.Date;

import engsoft.usuario.Usuario;

public class Exemplar {
	private Livro livro;	
    private String codExemplar;   
    private Date dataEmprestimo;
    private Date dataDevolucaoEsperada;
    private Usuario usuarioReservadoEmprestado;
	private boolean reservado;

    public Exemplar(String codExemplar, Livro livro) {
    	this.codExemplar = codExemplar;
    	this.livro = livro;
    	this.reservado = false;
    	
    	this.dataDevolucaoEsperada = this.dataEmprestimo = null;
    	this.usuarioReservadoEmprestado = null;    	
    }
    
    @Override
    public String toString(){
        String s = "Exemplar: " + codExemplar;
        // precisamos alterar isso para o padrao state
        if (dataEmprestimo != null) {
        	s += "\n\tStatus: Emprestado" + 
        		"\n\tData de Emprestimo: " + dataEmprestimo +
        		"\n\tData Esperada Para Devolucao: " + dataDevolucaoEsperada;
        } else if (reservado) {
        	
        } else {
        	s += "\n\tStatus: Disponivel";
        }
        s += "\n" + livro;
    	return s;
    }  
    
    public Usuario getUsuarioReservadoEmprestado() {
    	return this.usuarioReservadoEmprestado;
    }
    
    public boolean getEmprestado() {
    	return this.dataEmprestimo != null;
    }
    
    public boolean getReservado() {
    	return this.reservado;
    }
    
    public void reservar(Usuario u) throws Exception {
    	if (dataEmprestimo != null || getReservado()) {
			throw new Exception("Exemplar do livro ja emprestado ou reservado a outra pessoa.\n" + this);
		}		
		this.reservado = true;
		this.usuarioReservadoEmprestado = u;
		this.dataEmprestimo = this.dataDevolucaoEsperada = null;
    }
    
    public void desreservar(Usuario u) throws Exception {
    	if (getReservado() && this.usuarioReservadoEmprestado == u) {
    		this.reservado = false;
    		this.usuarioReservadoEmprestado = null;					
    		return;
		}	
    	throw new Exception("Exemplar do livro ja emprestado ou reservado a outra pessoa.\n" + this);				
    }
    
    public void pegarEmprestado(Usuario u, int dias) throws Exception {    	
		if (dataEmprestimo != null || (this.usuarioReservadoEmprestado != null && this.usuarioReservadoEmprestado != u)) {
			throw new Exception("Exemplar do livro ja emprestado ou reservado a outra pessoa.\n" + this);
		}		
		this.reservado = false;
		this.usuarioReservadoEmprestado = u;
		// calculo dos dias de aluguel
		Calendar calendario = Calendar.getInstance();
		this.dataEmprestimo = new Date();		
        calendario.setTime(this.dataEmprestimo);
        calendario.add(Calendar.DATE, dias);        
        this.dataDevolucaoEsperada = calendario.getTime();    	
    }
    
    public void devolver(Usuario u) throws Exception {
    	if (getEmprestado() && this.usuarioReservadoEmprestado != u) {
    		throw new Exception("O usuario que deseja devolver o livro nao foi quem realizou o emprestimo.\n" + 
        			this);
		}
    	this.usuarioReservadoEmprestado.devolver(this);
    	this.dataEmprestimo = this.dataDevolucaoEsperada = null;       	 
    	this.usuarioReservadoEmprestado = null;    	
    }

    public String getCodExemplar() {
        return codExemplar;
    }
    
    public Livro getLivro() {
    	return livro;
    }
}