package engsoft.biblioteca;
import java.util.ArrayList;

import engsoft.observer.Observer;
import engsoft.observer.Subject;
import engsoft.usuario.Usuario;

public class Livro implements Subject {
    private static int RESERVA_NOTIFY = 3;
    
    private ArrayList<Observer> observers;    
    private ArrayList<Exemplar> exemplares; 
	
	private String codLivro;
    private String titulo;
    private String editora;
    private String autores;
    private String edicao;
    private String anoEdicao;    

    public Livro(String cod, String titulo, String editora, String autores, String edicao, String anoEdicao){
        this.observers = new ArrayList<Observer>();
        this.exemplares = new ArrayList<Exemplar>();
        
        this.codLivro  = cod;
        this.titulo    = titulo;
        this.editora   = editora;
        this.autores   = autores;
        this.edicao    = edicao;
        this.anoEdicao = anoEdicao;        
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
            int reservas = 0;
            for(Exemplar e: this.exemplares){
                if (e.isReservado() != null){
                    reservas++;
                }
            }
            return reservas;
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
        String s = "\t----------  Livro:  ----------"+
            	"\n\tTítulo: "+ titulo+
                "\n\tCodigo: " + codLivro+
                "\n\tEditora: " + editora+
                "\n\tAutor(es): " + autores+
                "\n\tEdição: " + edicao+
                "\n\tAno de Edição: " + anoEdicao +
                "\n\n\t----------  Lista de Exemplares  ----------\n";
        for (Exemplar exemplar: this.exemplares) {
        	s += exemplar;
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
    		if (e.getCodExemplar().equals(codExemplar)) {
    			this.exemplares.remove(e);
    		}
    	}    	    
    }
    
    public Exemplar pegarEmprestado(Usuario u, int dias, boolean force) throws Exception {
    	for (Exemplar exemplar: this.exemplares) {
    		try {
    			exemplar.pegarEmprestado(u, dias, force);
    			return exemplar;	
    		} catch (Exception e) {
    		}    		
    	}
    	throw new Exception("Nenhum exemplar do livro disponivel para emprestimo.\n" + this);
    }
    
    public Exemplar devolver(Usuario u) throws Exception {
    	for (Exemplar exemplar: this.exemplares) {
    		if (exemplar.isEmprestado() != u) { 
    			continue;
    		}
    		try {
				exemplar.devolver();
				return exemplar;
			} catch (Exception e) { 
				throw new Exception(exemplar + "\nNao foi possivel devolver o exemplar.\n");
			}    		
    	}
    	throw new Exception("Nome do Usuario:" + u.getNome() + "\n" +
                "Livro:" + this.getTitulo() +  "\n" +
                "\n\nNao foi possivel encontrar o emprestimo do livro feito pelo usuario.\n");    	
    }
    
    public Exemplar reservar(Usuario usuario, boolean force) throws Exception{    	
    	for(Exemplar exemplar: this.exemplares) {
    		try {
    			exemplar.reservar(usuario, force);    			
                        if (this.getQtdReservas() >= Livro.RESERVA_NOTIFY){
                            this.notifyObservers();
                        }
    			return exemplar;
    		} catch (Exception e) {
    			
    		}    		
    	}
    	throw new Exception("Livro:" + this.getTitulo() +  "\n" +
                "Todos os exemplares do livro estao emprestados, ou reservados a outras pessoas.\n");
    }

    public Exemplar desreservar(Usuario usuario) throws Exception{
    	for(Exemplar exemplar: this.exemplares) {
    		if (exemplar.isReservado() != usuario) {
    			continue;
    		}
    		try {    			
    			exemplar.desreservar(usuario);
    			return exemplar;
    		} catch (Exception e) {
    			
    		}    		
    	}
    	throw new Exception(
                "Nome do Usuario:" + usuario.getNome() + "\n" +
                "Livro:" + this.getTitulo() +  "\n" +
                "Nao ha reservas para o livro no nome desse usuario.\n");    	
    }
    
    public String getHistorico(Usuario usuario) {
    	String s = "";
    	for (Exemplar exemplar: this.exemplares) {
    		s += exemplar.getHistorico(usuario);
    	}
    	return s;
    }
    
    public boolean isReservado(Usuario usuario) {
    	for(Exemplar exemplar: this.exemplares) {
    		if (exemplar.isReservado() == usuario) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean isEmprestado(Usuario usuario) {
    	for(Exemplar exemplar: this.exemplares) {
    		if (exemplar.isEmprestado() == usuario) {
    			return true;
    		}
    	}
    	return false;
    }
        
	public boolean isEmprestimoAtrasado(Usuario usuario) throws Exception {		
		for(Exemplar exemplar: this.exemplares) {
    		if (exemplar.isEmprestado() == usuario && exemplar.isEmprestimoAtrasado()) {
    			return true;
    		}
    	}	
		return false;
	}

}
