import java.util.Iterator;
import java.util.NoSuchElementException;

/** Classe concreta immutabile rappresentante una Programmazione Periodica, intesa come una Programmazione
 * definita a partire dal giorno iniziale, seguita da ulteriori date, programmate a intervalli equidsitanti.
 * 
 * La classe implementa l'interfaccia {@code} Iterable}, permettendo di iterare sui giorni previsti dalla Programmazione
 * in ordine crescente.
 */
public class ProgrammazionePeriodica extends AbstractProgrammazione {

    // AF: Programmazione periodica che parte dal giorno = giornoIniziale, e prevede una
    // data ogni "periodo" giorni, ovvero periodo è il numero di giorni tra una data e l'altra
    // RI: 1 <= periodo <= 31 

    /** Numero di giorni tra una data e l'altra */
    private int periodo;

    /**
     * Costruisce una Programmazione Periodica, definita a partire dal giorno iniziale specificato,
     * con date intervallate dal periodo di giorni specificato.
     * 
     * @param periodo numero di giorni tra una data e l'altra
     * @throws IllegalArgumentException se giornoIniziale è minore di 1, o maggiore di 31
     * @throws IllegalArgumentException se periodo è minore di 1, o maggiore di (31 - giornoIniziale)
     */
    public ProgrammazionePeriodica(int giornoIniziale, int periodo) throws IllegalArgumentException {

        super(giornoIniziale);

        if ((periodo < 1) || (periodo > 31 - giornoIniziale))
            throw new IllegalArgumentException("Il periodo di una Programmazione Periodica non può essere minore di 1, o eccedere nel mese successivo.");
  
        this.periodo = periodo;

    }

    /**
     * Ritorna il periodo di giorni previsto tra una data e l'altra per questa Programmazione.
     * 
     * @return il periodo di giorni previsto tra una data e l'altra per questa Programmazione
     */
    public int periodo() {
        return periodo;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            int next = giornoIniziale();

            @Override
            public boolean hasNext() {
                return next <= 31;
            }

            @Override
            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                int result = next;
                next += periodo();
                return result;
            }

        };
    }
    
}
