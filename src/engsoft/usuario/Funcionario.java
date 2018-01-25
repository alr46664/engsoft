package engsoft.usuario;

/**
 * Classe do Usuario de tipo Funcionario
 * @author Andre Madureira, Felipe Ribeiro, Dhene Arlis
 *
 */
public class Funcionario extends Usuario {
	public static final int DIAS_EMPRESTIMO = 2;
	public static final int MAX_EMPRESTIMO = 3;
	
    public Funcionario(String codigo, String nome ){
    	super(codigo,nome);
    	setEmprestimoStrategy(new EmprestimoComum(DIAS_EMPRESTIMO, MAX_EMPRESTIMO));
    }     

    @Override
    public String toString(){
        return "\t--------------  FUNCIONARIO  -------------- \n" + super.toString();
    }

}