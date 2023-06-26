import java.util.Iterator;
import java.util.NoSuchElementException;

/** Classe concreta immutabile rappresentante una Programmazione con Replica, intesa come una Programmazione
 * definita a partire dal giorno iniziale, prevista per un numero di repliche in giorni consecutivi, incluso il giorno iniziale.
 * 
 * La classe implementa l'interfaccia {@code} Iterable}, permettendo di iterare sui giorni previsti dalla Programmazione
 * in ordine crescente.
 */
public class ProgrammazioneReplica extends AbstractProgrammazione {
    
    /** Numero di repliche consecutive */
    int numeroRepliche;

    /**
     * Costruisce una Programmazione con Repliche, avente inizio il giorno iniziale specificato,
     * programmata complessivamente per il numero di repliche specificato, compreso il giorno iniziale.
     * 
     * @param giornoIniziale giorno iniziale della programmazione
     * @param numeroRepliche numero di repliche, compreso il giorno
     * @throws IllegalArgumentException se numeroRepliche è minore di 1 o maggiore di (31 - giornoIniziale)
     */
    ProgrammazioneReplica(int giornoIniziale, int numeroRepliche) throws IllegalArgumentException {
        super(giornoIniziale);
        
        if ((numeroRepliche < 1) || (numeroRepliche > 31 - giornoIniziale))
            throw new IllegalArgumentException("Il numero di repliche non può essere minore di 1, o eccedere nel mese successivo.");
    
        this.numeroRepliche = numeroRepliche;
    }

    /**
     * Ritorna il numero di repliche previste per questa Programmazione.
     * 
     * @return il numero di repliche previste per questa Programmazione
     */
    public int numeroRepliche() {
        return numeroRepliche;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            int next = giornoIniziale();

            @Override
            public boolean hasNext() {
                if (next > (giornoIniziale() + numeroRepliche() - 1)) return false;
                return true;
            }

            @Override
            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                int result = next;
                next += 1;
                return result;
            }

        };
    }

}