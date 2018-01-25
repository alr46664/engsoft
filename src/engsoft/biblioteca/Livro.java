package engsoft.biblioteca;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import engsoft.observer.Observer;
import engsoft.observer.Subject;
import engsoft.usuario.Usuario;

/**
 * Classe que gerencia um Livro
 * @author Andre Madureira, Felipe Ribeiro, Dhene Arlis
 *
 */
public class Livro implements Subject {
	// variavel que indica quantas reservas devem ser feitas antes de emitir 
	// uma notificacao para os Observers
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
    
    /**
     * Retorna quantas reservas de um Livro devem ser feitas antes de emitir uma notificacao para os Observers
     * @return numero de reservas minimo para emitir notificacao aos Observers
     */
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
    
    /**
     * Verifica se atingimos o numero minimo de reservas para emitir notificacao aos Observers
     */
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
    
	/**
	 * Adiciona um novo Exemplar do Livro
	 * @param codExemplar codigo do exemplar do Livro
	 */
    public void addExemplar(String codExemplar) {    	
    	this.exemplares.add(new Exemplar(codExemplar, this));    	
    }
    
    /**
     * Remove um Exemplar do Livro
	 * @param codExemplar codigo do exemplar do Livro
     */
    public void removeExemplar(String codExemplar) {
    	for(Exemplar e: this.exemplares) {
    		if (e.getCodExemplar() != null && e.getCodExemplar().equals(codExemplar)) {
    			this.exemplares.remove(e);
    		}
    	}    	    
    }
    
    /**
     * Realiza o emprestimo de um Exemplar desse Livro para o Usuario, caso isso seja possivel (vide especificacao do trabalho)
     * @param u usuario que deseja pegar um Exemplar do Livro emprestado
     * @param dias numero de dias maximo que o usuario pode permanecer com o Livro emprestado
     * @return Exemplar que foi emprestado com sucesso para o usuario
     * @throws Exception caso o procedimento nao seja bem sucedido (vide especificacao do trabalho para maiores detalhes), um Exception sera gerado
     */
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
    
    /**
     * Retorna a Reserva feita pelo usuario com relacao ao Livro
     * @param usuario usuario que realizou a reserva do livro
     * @return Reserva feita pelo usuario
     */
    public Reserva getReserva(Usuario usuario) {    	
    	for (Reserva res: this.reservas) {
    		if (res.getUsuario() != null && res.getUsuario().equals(usuario)) {
    			return res;
    		}
    	}
    	return null;
    }
    
    /**
     * Retorna o Exemplar que foi emrpestado para o usuario
     * @param u usuario que fez o emprestimo
     * @return Exemplar que o usuario pegou emprestado
     */
    public Exemplar getExemplarEmprestado(Usuario u) {
    	for (Exemplar exemplar: this.exemplares) {
    		if (exemplar.isEmprestado() != null && exemplar.isEmprestado().equals(u)) {
    			return exemplar;
    		}
    	}
    	return null;
    }
    
    /**
     * Devolve o Exemplar que foi emrpestado para o usuario
     * @param u usuario que fez o emprestimo
     * @return Exemplar que o usuario devoleu
     * @throws Exception caso o procedimento nao seja bem sucedido (vide especificacao do trabalho para maiores detalhes), um Exception sera gerado
     */
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
    
    /**
     * Reserva o Livro para o usuario
     * @param usuario usuario que deseja reservar o Livro
     * @return Reserva do usuario
     */
    public Reserva reservar(Usuario usuario){    	    
		Reserva res = new Reserva(usuario);
    	this.reservas.add(res);    			
        this.checkNotifyObservers();
        return res;
    }
    
    /**
     * Remove a reserva do Livro feita pelo usuario
     * @param usuario usuario realizou a reserva do Livro
     */
    public void desreservar(Usuario usuario){    	
    	this.reservas.remove(new Reserva(usuario));
    	this.checkNotifyObservers();
    }
    
    /**
     * Pega o historico do usuario com relacao a todos os exemplares do Livro
     * @param usuario usuario que se deseja pegar o historico
     * @return Historico do usuario com relacao aos exemplares desse Livro
     */
    public String getHistorico(Usuario usuario) {
    	String s = "";
    	for (Exemplar exemplar: this.exemplares) {
    		s += exemplar.getHistorico(usuario);
    	}
    	return s;
    }
    
    /**
     * Verifica se o usuario realizou uma reserva do Livro
     * @param usuario usuario
     * @return true se houver uma reserva feita por este usuario, false do contrario
     */
    public boolean isReservado(Usuario usuario) {    	
    	return this.reservas.contains(new Reserva(usuario));
    }
    
    /**
     * Verifica se o usuario realizou um emprestimo do Livro
     * @param usuario usuario
     * @return true se houver um emprestimo feita por este usuario, false do contrario
     */
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
        
    /**
     * Verifica se o usuario possui emprestimos atrasados do Livro
     * @param usuario usuario
     * @return true se houver pelo menos um emprestimo atrasado feito por este usuario, false do contrario
     * @throws Exception caso um erro no procedimento ocorra, interrompa o processo gerando um Exception
     */
	public boolean isEmprestimoAtrasado(Usuario usuario) throws Exception {		
		for(Exemplar exemplar: this.exemplares) {
    		if (exemplar.isEmprestado() != null && exemplar.isEmprestado().equals(usuario) && exemplar.isEmprestimoAtrasado()) {
    			return true;
    		}
    	}	
		return false;
	}

}
