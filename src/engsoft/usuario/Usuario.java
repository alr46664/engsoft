package engsoft.usuario;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import engsoft.biblioteca.Exemplar;
import engsoft.biblioteca.Livro;
import engsoft.biblioteca.Reserva;

/**
 * Classe que gerencia os usuarios da biblioteca
 * @author Andre Madureira, Felipe Ribeiro, Dhene Arlis
 *
 */
public abstract class  Usuario {
    // numero maximo de reservas que o usuario pode solicitar
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
    public int hashCode() {
        return Integer.parseInt(this.getCodigo());
    }

    @Override
    public boolean equals(Object o) {
    	Usuario u;
    	if (Usuario.class.isInstance(o)) {
    		u = (Usuario) o;
    	} else {
    		return false;
    	}
    	return this.getCodigo() != null && this.getCodigo().equals(u.getCodigo());
    }
    
    @Override
    public String toString(){
        String s = "\tNome: "+getNome()+"\n";
		s += "\tCodigo: " +getCodigo() + "\n";
		s += "\n\t-------------- RESERVAS -----------------\n";
		for (Livro liv: this.reservas) {			
			s += "Livro: " + liv.getTitulo() + "\n";
			s += liv.getReserva(this);	
			s += "----------------------------\n";
		}
		s += "\n\t-------------- EMPRESTIMOS -----------------\n";
		for (Livro liv: this.emprestimos) {
			s += liv.getExemplarEmprestado(this) + "\n";
			s += "----------------------------\n";
		}
		s += "\n\t-------------- HISTORICO DE OPERACOES (EMPRESTIMOS PASSADOS) -----------------\n";
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
    
    /**
     * Retorna o historico do usuario com relacao aos livros que ele ja pegou emprestado
     * @return historico do usuario
     */
    public final String getHistorico() {
    	String s = "";
    	for (Livro l: this.historico) {
    		s += l.getHistorico(this) + "\n";
    	}
    	return s;
    }
    
    /**
     * Verifica se o usuario possui o livro sob forma de emprestimo
     * @param livro livro que o usuario pode estar de posse de
     * @return true se o usuario esta de posse do exemplar do livro, false do contrario
     */
    public final boolean isEmprestado(Livro livro){
        return this.emprestimos.contains(livro);
    }
    
    /**
     * Verifica se o usuario reservou o livro 
     * @param livro livro que o usuario pode ter reservado
     * @return true se o usuario reservou livro, false do contrario
     */
    public final boolean isReservado(Livro livro){
    	return this.reservas.contains(livro);
    }
    
    /**
     * Devolve exemplar de livro, caso o usuario tenha realizado o emprestimo
     * @param l livro a ser devolvido
     * @return Exemplar do livro que foi devolvido
     * @throws Exception caso o procedimento nao seja bem sucedido (vide especificacao do trabalho para maiores detalhes), um Exception sera gerado
     */
    public final Exemplar devolver(Livro l) throws Exception {    	
    	Exemplar e = l.devolver(this);    	
    	this.emprestimos.remove(l);
    	this.historico.add(l);
    	return e;
    }
    
    /**
     * Remove a reserva do usuario 
     * @param l livro que o usuario reservou
     */
    public final void desreservar(Livro l) {
    	l.desreservar(this);
    	this.reservas.remove(l);  	
    }
    
    /**
     * Verifica se o usuario tem pelo menos um emprestimo atrasado
     * @return true se o usuario possui pelo menos um emprestimo atrasado livro, false do contrario
     * @throws Exception caso o procedimento nao seja bem sucedido (vide especificacao do trabalho para maiores detalhes), um Exception sera gerado
     */
    public final boolean temEmprestimoAtrasado() throws Exception {
    	boolean res = false;
    	for (Livro l: this.emprestimos) {
    		res |= l.isEmprestimoAtrasado(this);
    	}
    	return res;
    }
     
    /**
     * Reserva o livro para o usuario
     * @param l livro a ser reservado
     * @return Reserva do livro 
     * @throws Exception caso o procedimento nao seja bem sucedido (vide especificacao do trabalho para maiores detalhes), um Exception sera gerado
     */
    public final Reserva reservar(Livro l) throws Exception {
    	if (getQtdReservas() >= MAX_RESERVA) {
    		throw new Exception("Usuario: " + this.getNome() + "\n" +
    				"\nO usuario ultrapassou o limite maximo de reservas (" + MAX_RESERVA +
    			").\n" + this);
    	}
        if (isReservado(l) || isEmprestado(l)) {
            throw new Exception("Usuario: " + this.getNome() + "\n" +
            		"\nO usuario ja reservou ou pegou emprestado o livro.\n");
    	}
    	Reserva res = l.reservar(this);
        this.reservas.add(l);
        return res;
    }
    
    /**
     * Realiza emprestimo do livro para o usuario
     * @param l livro a ser emprestado
     * @return Exemplar do livro emprestado 
     * @throws Exception caso o procedimento nao seja bem sucedido (vide especificacao do trabalho para maiores detalhes), um Exception sera gerado
     */
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
    
    /**
     * Marca o usuario como usuario prioritario para emprestimos ou nao, usando padrao de projeto Strategy
     * @param e Tipo de strategio de emprestimo
     */
    protected final void setEmprestimoStrategy(Emprestimo e) {
    	this.emprestimoStrategy = e;
    }
    
}