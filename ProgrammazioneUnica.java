import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/** Classe concreta immutabile rappresentante una Programmazione prevista per una giornata unica,
 * coincidente con il suo giorno iniziale.
 * 
 * La classe implementa l'interfaccia {@code} Iterable}, permettendo di iterare sul singolo
 * giorno previsto dalla Programmazione.
 */
public class ProgrammazioneUnica extends AbstractProgrammazione {

    public ProgrammazioneUnica(int giornoIniziale) {
        super(giornoIniziale);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>(){

            Integer next = giornoIniziale();

            @Override
            public boolean hasNext() {
                return !Objects.isNull(next);
            }

            @Override
            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                Integer result = next;
                next = null;
                return result;
            }
            
        };
    }
    
}
