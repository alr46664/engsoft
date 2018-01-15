import java.util.ArrayList;

public class Professor extends Usuario{
    public Professor(String codigo, String nome ){
        super(codigo,nome);
    }

    @Override
    public String toString(){
        return "Professor:"+super.toString();
    }

    @Override
    public void emprestar(Livro livro){
        if(sucesso){

            System.out.println("Sucesso ao realizar o emprestimo");   
            System.out.println(livro);
            System.out.println(this);
        }    
        else{
            System.out.println("Falha ao realizar o emprestimo");   
            System.out.println(livro);
            System.out.println(this);
        }
    }

    
}