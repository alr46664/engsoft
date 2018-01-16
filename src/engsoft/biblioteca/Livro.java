package engsoft.biblioteca;
import java.util.ArrayList;

import engsoft.observer.Observer;
import engsoft.observer.Subject;
import engsoft.usuario.Usuario;

public class Livro implements Subject {
    private ArrayList<Observer> observers;
    private ArrayList<Usuario> reservas;
    private ArrayList<Exemplar> exemplares; 
	
	private String codLivro;
    private String titulo;
    private String editora;
    private String autores;
    private String edicao;
    private String anoEdicao;    

    public Livro(String cod, String titulo, String editora, String autores, String edicao, String anoEdicao){
        this.observers = new ArrayList<Observer>();
        this.reservas = new ArrayList<Usuario>();
        this.exemplares = new ArrayList<Exemplar>();
        
        this.codLivro  = cod;
        this.titulo    = titulo;
        this.editora   = editora;
        this.autores   = autores;
        this.edicao    = edicao;
        this.anoEdicao = anoEdicao;        
    }

	public ArrayList<Exemplar> getExemplares() {
		return exemplares;
	}

	public String getCodLivro() {
		return codLivro;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getEditora() {
		return editora;
	}

	public String getAutores() {
		return autores;
	}

	public String getEdicao() {
		return edicao;
	}

	public String getAnoEdicao() {
		return anoEdicao;
	}

	@Override
    public String toString(){
        return "Livro:"+
        	"\n\tTítulo: "+ titulo+
            "\n\tCodigo: " + codLivro+
            "\n\tEditora: " + editora+
            "\n\tAutor(es): " + autores+
            "\n\tEdição: " + edicao+
            "\n\tAno de Edição: " + anoEdicao;
            
    }
    
	@Override
	public void registerObserver(Observer o) {
		this.observers.add(o);		
	}

	@Override
	public void removeObserver(Observer o) {
		this.observers.remove(o);		
	}

	@Override
	public void notifyObservers() {
		for (Observer o: this.observers) {
			o.update(this);
		}
	}
    
    public void addExemplar(String codExemplar) {    	
    	this.exemplares.add(new Exemplar(codExemplar, this));    	
    }
    
    public void removeExemplar(String codExemplar) {
    	for(Exemplar e: this.exemplares) {
    		if (e.getCodExemplar().equals(codExemplar)) {
    			this.exemplares.remove(e);
    		}
    	}    	    
    }
    
    public void pegarEmprestado(Usuario u) throws Exception {
    	for (Exemplar e: this.exemplares) {
    		if ( ! e.getEmprestado() ) {
    			e.pegarEmprestado(u, u.getDiasEmprestimo());
    			return;
    		}
    	}
    	throw new Exception("Nenhum exemplar do livro disponivel para emprestimo.\n" + this);
    }
    
    public void devolver(Usuario u) throws Exception {
    	for (Exemplar exemplar: this.exemplares) {
    		if (exemplar.getEmprestado()) {
    			try {
    				exemplar.devolver(u);
    				return;
    			} catch (Exception e) { }    		
    		}
    	}
    	throw new Exception("Nao foi possivel encontrar o emprestimo do livro feito pelo usuario abaixo.\n" +
    		u + "\n" +
    		this);
    	
    }
    
    public void reservar(Usuario usuario) throws Exception{
    	if (this.reservas.size() > this.exemplares.size()) {
    		throw new Exception("Numero de reservas ultrapassa o numero de exemplares do livro\n" + this);
    	}
    	for(Exemplar e: this.exemplares) {
    		Usuario usuarioExemplar = e.getUsuarioReservadoEmprestado();
    		if (!e.getEmprestado() && !e.getReservado() && (usuarioExemplar != null || usuarioExemplar == usuario)) {
    			e.reservar(usuario);
    			this.reservas.add(usuario);
    	    	if (this.reservas.size() >= 2) {
    	    		this.notifyObservers();
    	    	}
    	    	return;
    		}
    	}
    	throw new Exception("Todos os exemplares do livro estao emprestados, ou reservados a outras pessoas.\n" + this);
    }

    public void desreservar(Usuario usuario) throws Exception{
    	for(Exemplar e: this.exemplares) {
    		if (e.getReservado() && e.getUsuarioReservadoEmprestado() == usuario) {
    			e.desreservar(usuario);
    			this.reservas.remove(usuario);
    			return;
    		}
    	}
    	throw new Exception("Nao ha reservas para o livro no nome desse usuario.\n" + this + "\n" + usuario);    	
    }    

}
