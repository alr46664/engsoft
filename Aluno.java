public class Aluno extends Usuario{
    public Aluno(String codigo, String nome ){
        super(codigo,nome);
    }

    @Override
    public String toString(){
        return "Aluno:"+super.toString();
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

