import java.util.Iterator;

/** Classe astratta rappresentante una Programmazione caratterizata dal suo giorno iniziale,
 * ovvero il giorno da cui parte la sequenza di giorni programmati.
*/
public abstract class AbstractProgrammazione implements Programmazione {

    // AF: Programmazione che ha inizio il giorno del mese = giornoIniziale
    // RI: giornoIniziale >= 1 && giornoIniziale <= 31

    /** Giorno di inizio della Programmazione */
    private int giornoIniziale;

    // Constructors

    /**
     * Costruisce una Programmazione, definita a partire dal giorno iniziale specificato.
     * 
     * @throws IllegalArgumentException se giornoIniziale è minore di 1, o maggiore di 31
     */
    AbstractProgrammazione(int giornoIniziale) {
        if ((giornoIniziale < 1) || (giornoIniziale > 31))
            throw new IllegalArgumentException("Il giorno iniziale di una Programmazione non può essere < 1 o > 31 - Found: " + giornoIniziale);

        this.giornoIniziale = giornoIniziale;
    }

    // Methods

    @Override
    public abstract Iterator<Integer> iterator();

    @Override
    public int giornoIniziale() {
        return giornoIniziale;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        Iterator<Integer> it = this.iterator();

        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) sb.append(", ");
        }

        return sb.toString();
    }
    
}
