package engsoft.observer;

/**
 * Interface que informa quem pode ser observado
 * @author Andre Madureira, Felipe Ribeiro, Dhene Arlis
 *
 */
public interface Subject {
	
	/**
	 * Registra um observador
	 * @param o observador
	 */
    public void registerObserver(Observer o);
    
    /**
     * Remove um observador
	 * @param o observador
     */
    public void removeObserver(Observer o);
    
    /**
     * Notifica os observadores 
     */
    public void notifyObservers();
}