package engsoft.biblioteca;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import engsoft.observer.Observer;
import engsoft.observer.Subject;
import engsoft.usuario.Usuario;

public class Livro implements Subject {
    private static int RESERVA_NOTIFY = 3;
    
    private ArrayList<Observer> observers;    
    private ArrayList<Exemplar> exemplares;
    private Set<Reserva> reservas;
	
	private String codLivro;
    private String titulo;
    private String editora;
    private String autores;
    private String edicao;
    private String anoEdicao;    

    public static int getReservaNotify() {
    	return Livro.RESERVA_NOTIFY;    	
    }
    
    public Livro(String cod, String titulo, String editora, String autores, String edicao, String anoEdicao){
        this.observers = new ArrayList<Observer>();
        this.exemplares = new ArrayList<Exemplar>();
        this.reservas = new HashSet<Reserva>();
        
        this.codLivro  = cod;
        this.titulo    = titulo;
        this.editora   = editora;
        this.autores   = autores;
        this.edicao    = edicao;
        this.anoEdicao = anoEdicao;        
    }        
    
    private void checkNotifyObservers() {
    	if (this.getQtdReservas() >= Livro.RESERVA_NOTIFY){
            this.notifyObservers();
        } 
	}
	
	@Override
	public int hashCode() {
		return Integer.parseInt(this.getCodLivro());
	}

    @Override
    public boolean equals(Object o) {
    	Livro l;
    	if (Livro.class.isInstance(o)) {
    		l = (Livro) o;
    	} else {
    		return false;
    	}
    	return this.getCodLivro().equals(l.getCodLivro());
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
        
    public int getQtdReservas(){        
        return this.reservas.size();
    }
        
    public int getQtdEmprestimo(){
        int emprestimos = 0;
        for(Exemplar e: this.exemplares){
            if (e.isEmprestado() != null){
                emprestimos++;
            }
        }
        return emprestimos;
    }    
    
	@Override
    public String toString(){
        String s = "\t----------  LIVRO:  ----------"+
            	"\n\tTítulo: "+ titulo+
                "\n\tCodigo: " + codLivro+
                "\n\tEditora: " + editora+
                "\n\tAutor(es): " + autores+
                "\n\tEdição: " + edicao+
                "\n\tAno de Edição: " + anoEdicao +
                "\n\n\t----------  EXEMPLARES  ----------\n";
        for (Exemplar exemplar: this.exemplares) {
        	s += exemplar;
        	s += "----------------------------\n";
        }
        s += "\n\t----------  RESERVAS  ----------\n";
        for (Reserva res: this.reservas) {
        	s += res;
        	s += "----------------------------\n";
        }
		return s;
            
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
    		if (e.getCodExemplar() != null && e.getCodExemplar().equals(codExemplar)) {
    			this.exemplares.remove(e);
    		}
    	}    	    
    }
    
    public Exemplar pegarEmprestado(Usuario u, int dias) throws Exception {
    	for (Exemplar exemplar: this.exemplares) {
    		try {
    			exemplar.pegarEmprestado(u, dias);
    			this.desreservar(u);
    			return exemplar;	
    		} catch (Exception e) {
    		}    		
    	}
    	throw new Exception("Nenhum exemplar do livro disponivel para emprestimo.\n" + this);
    }
    
    public Reserva getReserva(Usuario usuario) {    	
    	for (Reserva res: this.reservas) {
    		if (res.getUsuario() != null && res.getUsuario().equals(usuario)) {
    			return res;
    		}
    	}
    	return null;
    }
    
    public Exemplar getExemplarEmprestado(Usuario u) {
    	for (Exemplar exemplar: this.exemplares) {
    		if (exemplar.isEmprestado() != null && exemplar.isEmprestado().equals(u)) {
    			return exemplar;
    		}
    	}
    	return null;
    }
    
    public Exemplar devolver(Usuario u) throws Exception {
    	for (Exemplar exemplar: this.exemplares) {
    		if (exemplar.isEmprestado() == null || !exemplar.isEmprestado().equals(u)) { 
    			continue;
    		}    		
    		try {
				exemplar.devolver();
				return exemplar;
			} catch (Exception e) { 
				throw new Exception(exemplar + "\nNao foi possivel devolver o exemplar.\n");
			}    		
    	}    	
    	throw new Exception("Nome do Usuario: " + u.getNome() + "\n" +
                "Livro: " + this.getTitulo() +  "\n" +
                "\n\nNao foi possivel encontrar o emprestimo do livro feito pelo usuario.\n");    	
    }
    
    public Reserva reservar(Usuario usuario){    	    
		Reserva res = new Reserva(usuario);
    	this.reservas.add(res);    			
        this.checkNotifyObservers();
        return res;
    }

    public void desreservar(Usuario usuario){    	
    	this.reservas.remove(new Reserva(usuario));
    	this.checkNotifyObservers();
    }
    
    public String getHistorico(Usuario usuario) {
    	String s = "";
    	for (Exemplar exemplar: this.exemplares) {
    		s += exemplar.getHistorico(usuario);
    	}
    	return s;
    }
    
    public boolean isReservado(Usuario usuario) {    	
    	return this.reservas.contains(new Reserva(usuario));
    }
    
    public boolean isEmprestado(Usuario usuario) {
    	if (usuario == null) {
    		return false;
    	}
    	for(Exemplar exemplar: this.exemplares) {
    		if (exemplar.isEmprestado() != null && exemplar.isEmprestado().equals(usuario)) {
    			return true;
    		}
    	}
    	return false;
    }
        
	public boolean isEmprestimoAtrasado(Usuario usuario) throws Exception {		
		for(Exemplar exemplar: this.exemplares) {
    		if (exemplar.isEmprestado() != null && exemplar.isEmprestado().equals(usuario) && exemplar.isEmprestimoAtrasado()) {
    			return true;
    		}
    	}	
		return false;
	}

}
