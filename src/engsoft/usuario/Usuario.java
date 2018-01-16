package engsoft.usuario;
import java.util.ArrayList;

import engsoft.biblioteca.Exemplar;
import engsoft.biblioteca.Livro;

public abstract class  Usuario {
    private String codigo;
    private String nome;  
    private int diasEmprestimo;
    private ArrayList<Exemplar> emprestimos;

    public Usuario(String codigo,String nome, int diasEmprestimo){
        this.emprestimos = new ArrayList<Exemplar>();
    	this.codigo = codigo;
        this.nome = nome;
        this.diasEmprestimo = diasEmprestimo;
    }
    
    @Override
    public String toString(){
        return "\n\tNome: "+getNome()+"\n\tCodigo: " +getCodigo();
    }   

    public String getCodigo(){
        return this.codigo;
    }

    public String getNome(){
        return this.nome;
    }
    
    public int getDiasEmprestimo() {
    	return this.diasEmprestimo;
    }
    
    public void devolver(Exemplar e) {
    	this.emprestimos.remove(e);
    }
         
}