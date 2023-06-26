import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/** Classe concreta mutabile rappresentante un Cartellone Mensile Cinematografico, caratterizzato da un nome del Cinema (non vuoto),
 * dalla durata del mese in giorni (28, 29, 30 o 31) e un elenco di Proiezioni Cinematografiche.
 */
public class Cartellone {

    // AF:  Cartellone Mensile di un cinema di nome = nome, per un mese di durataMese giorni,
    //      contenenti le proiezioni descritte dalle proiezioni contenute in proiezioni
    //      dove in un giorno del mese è presente una proiezione se la chiave (giorno del mese) ha associato un valore co
    //       un titolo di una proiezione
    // RI: nome ! null, nome non è la stringa vuota, 28 <= durataMese <= 31,
    //      proiezioni != null, proiezioni non contiene null, proiezioni non contiene
    //      proiezioni che si sovrappongono
    //      dateOccupate != null, dateOccupate non contiene chiavi o valori null
    //      dateOccupate contiene per ogni data prevista dalle programmazioni associate alle proiezioni in proiezioni,
    //      una coppia chiave valore (giornoOccupato, nome della Proiezione)

    /** Nome del Cinema */
    private String nome;

    /** Durata del mese in giorni */
    private int durataMese;

    /** Elenco delle Proiezioni */
    private List<Proiezione> proiezioni = new LinkedList<>();

    /** Struttura ausiliaria che memorizza per ogni intero associato a un giorno del mese lo spettacolo previsto, se presente */
    Map<Integer, String> dateOccupate = new HashMap<>();

    // Constructors

    /**
     * Costruisce un Cartellone Mensile vuoto associato a un Cinema di nome specificato, riferito a un mese
     * di durata specificata in numero di giorni.
     * 
     * @param nome nome del Cinema associato al Cartellone
     * @param durataMese durata del mese a cui fa riferimento il Cartellone, in giorni
     * @throws IllegalArgumentException se nome è la stringa vuota
     * @throws IllegalArgumentException se durataMese è minore di 28 o maggiore di 31
     * @throws NullPointerException se nome è null
     */
    public Cartellone(String nome, int durataMese) throws IllegalArgumentException {

        if (Objects.requireNonNull(nome, "Il nome di un Cinema non può essere null.").length() == 0) {
            throw new IllegalArgumentException("Il nome di un Cinema non può essere vuoto.");
        }

        if ((durataMese < 28) || (durataMese > 31)) {
            throw new IllegalArgumentException("La durata di un mese non può essere < 28 o > 31- Found: " + durataMese);
        }

        this.nome = nome;
        this.durataMese = durataMese;
    }

    // Methods

    /**
     * Ritorna {@code True} se ci sono sovrapposizioni nel Cartellone con la programmazione specificata,
     * {@code False} altrimenti.
     * 
     * @param programmazione la programmazione con cui ricercare sovrapposizioni
     * @return {@code True} se non ci sono sovrapposizioni nel Cartellone con la programmazione specificata,
     * {@code False} altrimenti
     */
    private boolean checkSovrapposizioni(Programmazione programmazione) {
        
        for (Integer i : programmazione) {
            if (dateOccupate.containsKey(i)) return true;
        }

        return false;
    }

    /**
     * Aggiunge un Proiezione a questo Cartellone, con titolo specificato e Programmazione specificata,
     * se questa non comporta sovrapposizioni con alcuna proiezione, e ritorna {@code True} se l'aggiunta è
     * andata a buon fine, altrimenti non modifica il Cartellone e ritorna {@code False}.
     * 
     * @param titolo titolo della proiezione da aggiungere
     * @param programmazione programmazione della proiezione da aggiungere
     * @return {@code True} se l'aggiunta è andata a buon fine, {@code False} altrimenti
     */
    public boolean aggiungiProiezione(String titolo, Programmazione programmazione) {
        if (checkSovrapposizioni(programmazione))
            return false;
        
        proiezioni.add(new Proiezione(titolo, programmazione));

        Iterator<Integer> it = programmazione.iterator();           
        while (it.hasNext()) {
            int giorno = it.next();
            if (giorno <= durataMese) dateOccupate.put(giorno, titolo);
        }

        return true;
    }

    /**
     * Ritorna il nome del Cinema associato al Cartellone.
     * 
     * @return il nome del Cinema associato al Cartellone
     */
    public String nome() {
        return nome;
    }

    /**
     * Ritorna la durata del mese a cui fa riferimento del Cartellone, in giorni.
     * 
     * @return la durata del mese a cui fa riferimento del Cartellone, in giorni
     */
    public int durataMese() {
        return durataMese;
    }

    /**
     * Ritorna un iteratore sulle Proiezioni presenti nel Cartellone, nell'ordine in cui sono state aggiunte.
     * 
     * @return un iteratore sulle Proiezioni presenti nel Cartellone, nell'ordine in cui sono state aggiunte
     */
    public Iterator<Proiezione> proiezioni() {
        return Collections.unmodifiableList(proiezioni).iterator();
    }


    /**
     * Ritorna un iteratore sui giorni del mese, corredato con l'eventuale proiezione prevista per il particolare giorno.
     * 
     * @return un iteratore sui giorni del mese, corredato con l'eventuale proiezione prevista per il particolare giorno
     */
    public Iterator<String> date() {
        return new Iterator<String>() {

            int i = 1;

            @Override
            public boolean hasNext() {
                return i <= durataMese();
            }

            @Override
            public String next() {
                if (!hasNext()) throw new NoSuchElementException();

                StringBuilder sb = new StringBuilder(i + " - ");
                if (dateOccupate.containsKey(i)) sb.append(dateOccupate.get(i));
                String result = sb.toString();
                i++;
                return result;
            }
            
        };
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("Cinema: " + nome + ", giorni: " + durataMese + ", proiezioni: " + proiezioni.size());
        return sb.toString();
    }


    //#########################//

    /** Classe concreta immutabile rappresentante una Proiezione Cinematografica, caratterizzata da un titolo (non vuoto),
     * e una Programmazione, che descrive le date in cui si verifica.
     */
    public class Proiezione {

        // AF: Proiezione di titolo = titolo, prevista per i giorni descritti dalla Programmazione = programmazione
        // RI: titolo != null, titolo non è la stringa vuota, programmazione != null

        /** Titolo della Proiezione */
        private String titolo;

        /** Programmazione della Proiezione */
        private Programmazione programmazione;

        // Constructors

        /**
         * Costruisce una Proiezione associata a un film di titolo specificato, prevista per le date specificate dalla
         * Programmazione specificata.
         * 
         * @param titolo titolo della Proezione
         * @param programmazione programmazione della Proiezione
         * @throws IllegalArgumentException se titolo è la stringa vuota
         * @throws NullPointerException se titolo è null
         * @throws NullPointerException se programmazione è null
         */
        private Proiezione(String titolo, Programmazione programmazione) throws IllegalArgumentException {
            
            if (Objects.requireNonNull(titolo, "Il titolo di una Proezione non può essere null.").length() == 0) {
                throw new IllegalArgumentException("Il titolo di una Proezione non può essere vuoto.");
            }
    
            this.titolo = titolo;
            this.programmazione = Objects.requireNonNull(programmazione, "Una Programmazione non può essere null.");
        }

        // Methods

        /**
         * Ritorna il titolo di questa Proiezione.
         * 
         * @return il titolo di questa Proiezione
         */
        public String titolo() {
            return titolo;
        }

        /**
         * Ritorna la Programmazione relativa a questa Proiezione.
         * 
         * @return la Programmazione relativa a questa Proiezione
         */
        public Programmazione programmazione() {
            return programmazione;
        }

        @Override
        public String toString() {

            StringBuilder sb = new StringBuilder("Cinema: " + Cartellone.this.nome() + ", titolo: \"" + titolo + "\", date: ");

            Iterator<Integer> it = programmazione.iterator();

            while (it.hasNext()) {
                int giorno = it.next();
                if (giorno <= Cartellone.this.durataMese()) sb.append(giorno);
                if (it.hasNext()) sb.append(", ");
            }

            return sb.toString();
        }
    }
}