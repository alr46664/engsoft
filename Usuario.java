public abstract class  Usuario implements Observer{
    private String codigo;
    private String nome;
    private ArrayList observers;

    public Usuario(String codigo,String nome){
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getCodigo(){
        return this.codigo;
    }

    public String getNome(){
        return this.nome;
    }

    public abstract void emprestar(Livro livro);

    @Override
    public String toString(){
        return "\n\tNome: "+getNome()+"\n\tCodigo: " +getCodigo();
    }

    @Override
    public void registerObserver(Observer o){
        observers.add(o);
    }
    
    @Override
    public void removeObserver(Observer o){
        int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
    }

    @Override
    public void notifyObserver(){
        for(int i=0;i< observers.size();i++){
            Observer observer = (Observer)observers.get(i);
            observer.update(codLivro);
        }
    }
}