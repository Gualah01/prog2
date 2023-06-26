import java.util.Iterator;

/** Interfaccia che descrive il comportamento di una Programmazione, intesa come un insieme
 * non vuoto e strettamente crescente di numeri interi compresi tra 1 e 31.
 * 
 * L'interfaccia estende l'interfaccia {@code Iterable}, permettendo di iterare sui giorni previsti
 * dalla Programmazione.
 * */
public interface Programmazione extends Iterable<Integer> {

    /** Ritorna un iteratore sulle date previste dalla Programmazione in ordine crescente.
     * 
     * @return un iteratore sulle date previste dalla Programmazione in ordine crescente
     */
    Iterator<Integer> iterator();

    /**
     * Ritorna il giorno iniziale della Programmazione, ovvero il primo giorno del mese
     * da cui parte la sequenza di giorni.
     * 
     * @return il giorno iniziale della Programmazione
     */
    int giornoIniziale();
    
}
