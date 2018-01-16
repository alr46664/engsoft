package engsoft.usuario;
import engsoft.biblioteca.Livro;
import engsoft.observer.Observer;

public class Aluno extends Usuario {
    public static final int DIAS_EMPRESTIMO = 5;
	
	public Aluno(String codigo, String nome ){
        super(codigo, nome, DIAS_EMPRESTIMO);
    }

    @Override
    public String toString(){
        return "Aluno:"+super.toString();
    }
    
}

