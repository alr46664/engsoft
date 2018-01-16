package engsoft.usuario;
import engsoft.biblioteca.Livro;
import engsoft.observer.Observer;

public class Funcionario extends Usuario {
	public static final int DIAS_EMPRESTIMO = 2;
	
    public Funcionario(String codigo, String nome ){
        super(codigo,nome, DIAS_EMPRESTIMO);
    }     

    @Override
    public String toString(){
        return "Funcionario:"+super.toString();
    }



}