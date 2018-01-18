package engsoft.usuario;

public class Aluno extends Usuario {
    public static final int DIAS_EMPRESTIMO = 5;    
    public static final int MAX_EMPRESTIMO = 4;
	
	public Aluno(String codigo, String nome ){
        super(codigo, nome);
        setEmprestimoStrategy(new EmprestimoComum(DIAS_EMPRESTIMO, MAX_EMPRESTIMO));
    }

    @Override
    public String toString(){
        return "\t--------------  ALUNO  --------------\n" + super.toString();
    }

}

