public abstract class  Usuario{
    private String codigo;
    private String nome;

    public Usuario(String codigo,String nome){
        this.codigo = codigo;
        this.nome = nome;
    }

    //Nao foi exigido ter login e senha. Pq Botamos?
    public void login(){

    }

    public abstract void emprestar(String codigoLivro);
}