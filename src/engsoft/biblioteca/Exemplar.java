package engsoft.biblioteca;

import java.util.ArrayList;

import engsoft.usuario.Usuario;

/**
 * Classe que gerencia o Exemplar de um Livro
 * @author Andre Madureira, Felipe Ribeiro, Dhene Arlis
 *
 */
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
    
    /**
     * Retorna o historico do usuario com relacao ao Exemplar em questao
     * @param usuario usuario que desejamos obter o historico. Caso usuario == null, entao obteremos o historico de todos os usuarios.
     * @return String com o historico
     */
    public String getHistorico(Usuario usuario) {
    	boolean hasOneUser = false;
    	String s = "Historico do Exemplar: " + codExemplar  + "\n\t\t";
        for (StatusEmprestado status: this.historico) {        	
    		if (usuario == null || usuario.equals(status.isEmprestado())) {
    			s += status + "\n\t\t";
        		s += "----------------------------\n";
        		hasOneUser = true;
    		}        	        	
        }
        return (hasOneUser ? s : "");
    }    
    
    /**
     * Altera o estado do Exemplar (Disponivel para Emprestado e vice-versa)
     * @param s novo estado do exemplar
     */
    public void setState(Status s) {
    	this.state = s;
    }
    
    /**
     * Retorna o usuario que pegou o exemplar emprestado
     * @return usuario que pegou o exemplar emprestado. Caso o exemplar nao esteja emprestado, o metodo retorna null.
     */
    public Usuario isEmprestado() {
    	return this.state.isEmprestado();
    }
    
    /**
     * Realiza o emprestimo do exemplar para o usuario, marcando a data de devolucao prevista de acordo com o parametro dias.
     * @param usuario usuario que deseja pegar o exemplar emprestado
     * @param dias numero de dias que o usuario pode ficar com o exemplar emprestado
     * @throws Exception caso o procedimento nao seja bem sucedido (vide especificacao do trabalho para maiores detalhes), um Exception sera gerado
     */
    public void pegarEmprestado(Usuario usuario, int dias) throws Exception {
    	this.state.pegarEmprestado(usuario, dias);
    	this.historico.add((StatusEmprestado) this.state);
    }
    
    /**
     * Devolve o exemplar
     * @throws Exception caso o procedimento nao seja bem sucedido (vide especificacao do trabalho para maiores detalhes), um Exception sera gerado
     */
    public void devolver() throws Exception {
    	this.state.devolver();
    }       
    
    /**
     * Verifica se o emprestimo do exemplar esta atrasado
     * @return true caso emprestimo atrasado, false do contrario
     * @throws Exception caso o exemplar nao esteja emprestado, um Exception sera gerado
     */
    public boolean isEmprestimoAtrasado() throws Exception {
    	return this.state.isEmprestimoAtrasado();
    }
    
}