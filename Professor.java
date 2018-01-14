import java.util.ArrayList;

public class Professor extends Usuario implements Subject {
    private ArrayList observers;

    public Professor(String codigo, String nome ){
        super(codigo,nome);
    }

    public void registerObserver(Observer o){
        observers.add(o);
    }
    
    public void removeObserver(Observer o){
        int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
    }

    public void notifyObserver(){
        for(int i=0;i< observers.size();i++){
            Observer observer = (Observer)observers.get(i);
            observer.update(codLivro);
        }
    }
}