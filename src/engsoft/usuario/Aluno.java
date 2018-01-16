package engsoft.usuario;
import engsoft.biblioteca.Livro;

public class Aluno extends Usuario {
    public static final int DIAS_EMPRESTIMO = 5;
    public static final int MAX_EMPRESTIMO = 4;
	
	public Aluno(String codigo, String nome ){
        super(codigo, nome, DIAS_EMPRESTIMO);
    }

    @Override
    public String toString(){
        return "Aluno: \n" + super.toString();
    }

    @Override
	public void pegarEmprestado(Livro l) throws Exception {		
		if (temEmprestimoAtrasado() || getQtdEmprestimos() >= MAX_EMPRESTIMO) {
			throw new Exception("Nao foi possivel pegar o livro emprestado. O aluno abaixo tem emprestimos atrasados ou ultrapassou sua cota maxima de emprestimos.\n" +
				this);
		}
		l.pegarEmprestado(this, getDiasEmprestimo(), false);
		addEmprestado(l);
	}

}

