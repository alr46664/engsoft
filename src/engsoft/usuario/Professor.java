package engsoft.usuario;
import engsoft.biblioteca.Livro;
import engsoft.observer.Observer;

/**
 * Classe do Usuario de tipo Professor
 * @author Andre Madureira, Felipe Ribeiro, Dhene Arlis
 *
 */
public class Professor extends Usuario implements Observer {
	
	public static final int DIAS_EMPRESTIMO = 7;
	
	protected int qtdNotificado;
	
	public Professor(String codigo, String nome){
        super(codigo,nome);
        setEmprestimoStrategy(new EmprestimoPrioritario(DIAS_EMPRESTIMO));
    }

    @Override
    public String toString(){
        return "\t--------------  PROFESSOR  --------------\n" + super.toString();
    }

	@Override
	public void update(Livro livro) {
		System.out.println(livro);
		System.out.println("\nALERTA: Caro professor \"" + this.getNome() + 
				"\", o livro (descrito acima) foi reservado por pelo menos " +
				Livro.getReservaNotify() + " usuários.");		
		this.qtdNotificado++;
	}	
	
	@Override
	public int getQtdNotificacao() { 
    	return this.qtdNotificado;
	}
    
}