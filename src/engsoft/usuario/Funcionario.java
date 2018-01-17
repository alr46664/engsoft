package engsoft.usuario;
import engsoft.biblioteca.Exemplar;
import engsoft.biblioteca.Livro;

public class Funcionario extends Usuario {
	public static final int DIAS_EMPRESTIMO = 2;
	public static final int MAX_EMPRESTIMO = 3;
	
    public Funcionario(String codigo, String nome ){
        super(codigo,nome, DIAS_EMPRESTIMO);
    }     

    @Override
    public String toString(){
        return "Funcionario: \n" + super.toString();
    }

	@Override
	public Exemplar pegarEmprestado(Livro l) throws Exception {		
		if (temEmprestimoAtrasado() || getQtdEmprestimos() >= MAX_EMPRESTIMO) {
			throw new Exception("Nao foi possivel pegar o livro emprestado. O funcionario abaixo tem emprestimos atrasados ou ultrapassou sua cota maxima de emprestimos.\n" +
				this);
		}
		Exemplar e = l.pegarEmprestado(this, getDiasEmprestimo(), false);
		addEmprestado(l);
		return e;
	}

}