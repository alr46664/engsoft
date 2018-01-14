import java.sql.Date;
import java.util.Calendar;

public class Emprestimo{

    private String dataEmprestimo;
    private String dataDevolucaoEsperada;
    private String dataDevolucaoFeita;

    public Emprestimo(String codExemplar, String codigo, String nome){}

    public static Date calcularData(int dias,Date data){
    //vou pegar a data do sistema para somar com os dias de acordo com o tipo de usuario    
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(date);
        calendario.add(Calendar, dias);
        
        return calendario.getTime();
        // obs: Nao sei se fica melhor, a gente informar aqui por um "if" os dias para o tipo de Usuario
        // ou se fica melhor informar na classe livro, pelo metodo reservar
    }

    public void devolver(){
        
    }

}