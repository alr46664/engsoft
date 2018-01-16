package engsoft.usuario;
import engsoft.biblioteca.Livro;
import engsoft.observer.Observer;

public class Professor extends Usuario implements Observer {
	public static final int DIAS_EMPRESTIMO = 7;
	
	public Professor(String codigo, String nome){
        super(codigo,nome, DIAS_EMPRESTIMO);
    }

    @Override
    public String toString(){
        return "Professor: " + super.toString();
    }


	@Override
	public void update(Livro livro) {
		System.out.println("O livro abaixo foi reservado por pelo menos 2 usuários.");
		System.out.println(livro);
	}

    
}