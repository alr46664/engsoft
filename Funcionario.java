public class Funcionario extends Usuario{
    public Funcionario(String codigo, String nome ){
        super(codigo,nome);
    }
     
    @Override
    public void emprestar(String codigoLivro){
        
    }

    @Override
    public String toString(){
        return "Funcionario:"+super.toString();
    }
}