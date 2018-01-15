public class Livro implements Observer{
    private String codLivro;
    private String titulo;
    private String editora;
    private String autores;
    private String edicao;
    private String anoEdicao;
    private Subject professor;
    private int contaLivroObserver = 0;

    public Livro(Subject professor){
        this.professor = professor;
        professor.registerObserver(this);
    }

    public void reservar(){

    }

    public void desreservar(){
        
    }

    public void update(String codLivro){
        this.codLivro = codLivro;
        this.contaLivroObserver++;
        if (this.contaLivroObserver >= 2){
            display();
        }
    }

    public void display(){
        System.out.println("Livro observado foi registrado pela segunda vez simultanea.");
    }

    @Override
    public String toString(){
        return "Livro:\n\tTitulo: "+this.titulo+
               "\n\tCodigo: " +this.codLivro+
               "\n\tEditora: " +this.editora+
               "\n\tAutor(es): " +autores+
               "\n\tEdicao: " +edicao+
               "\n\tAno: " +anoEdicao;
            
    }

}